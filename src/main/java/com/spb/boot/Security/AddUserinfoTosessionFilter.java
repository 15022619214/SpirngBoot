package com.spb.boot.Security;

import com.spb.boot.Model.User;
import com.spb.boot.Utils.Constants;
import com.spb.boot.Utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AddUserinfoTosessionFilter extends GenericFilterBean {

    @Autowired
    SecurityUtil securityUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute(Constants.SESSION_CURRENT_USER_KEY) == null){
            User user = securityUtil.getUserinfo();
            if (user != null) {
                user.getRoles().size();
                session.setAttribute(Constants.SESSION_CURRENT_USER_KEY, user);

            }
        }
        filterChain.doFilter(request, response);
    }
}
