package com.spb.boot.Utils;

import com.spb.boot.Model.User;
import com.spb.boot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class SecurityUtil {

    @Autowired
    private UserService userService;

    /**
     * 取得当前用户, 如果当前用户未登录则返回null.
     */
    public User getUserinfo() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        if("anonymousUser".equals(authentication.getPrincipal())){
            return null;
        }
        User user = userService.findByUsername(authentication.getName());
        return user;
    }


    /**
     * 取得Authentication, 如当前SecurityContext为空时返回null.
     */
    private static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return context.getAuthentication();
    }
}
