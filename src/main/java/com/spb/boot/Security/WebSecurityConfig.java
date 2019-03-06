package com.spb.boot.Security;

import com.spb.boot.Utils.MD5Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService service(){
        return new SecurityService();
    }

    /*用户加密配置*/
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Util.encode((String) charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String obj) {
                return obj.equals(MD5Util.encode((String) charSequence));
            }
        });
    }

    /*配置静态资源访问，定义保护，无需保护的Url*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .accessDecisionManager(accessDecisionManager())
                .antMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/")
                .defaultSuccessUrl("/")
                .successHandler(new LoginSuccessHandle())
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
        http.csrf()
                .disable();
    }

    //解决静态资源被拦截的问题
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/Javascript/**","/Images/**","/Style/**","/Directive/**");
    }

    /*权限投票器*/
    @Bean
    public SecurityAuthorityVoter authorityVoter(){
        SecurityAuthorityVoter authorityVoter = new SecurityAuthorityVoter();
        return authorityVoter;
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
        decisionVoters.add(new AuthenticatedVoter());
        decisionVoters.add(new RoleVoter());
        decisionVoters.add(authorityVoter());
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
//        webExpressionVoter.setExpressionHandler(expressionHandler);
        decisionVoters.add(webExpressionVoter);
        AffirmativeBased accessDecisionManager = new AffirmativeBased(decisionVoters);
        return accessDecisionManager;
    };
}
