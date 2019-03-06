package com.spb.boot.Security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
/*
 * 
 * 配置登录后根据角色进入不同的页面（方法）
 * */
public class LoginSuccessHandle implements AuthenticationSuccessHandler {
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException,ServletException {

			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			String path = request.getContextPath() ;
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			if (roles.contains("ROLE_ADMIN")){
                response.sendRedirect(basePath+"system");
                return;
			}else if(roles.contains("ROLE_STU")){
                response.sendRedirect(basePath+"");
                return;
            }
		response.sendRedirect(basePath+"");
    }
}