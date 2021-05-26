package com.example.empresasjava.configs;

import com.example.empresasjava.models.User;
import com.example.empresasjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("authorityChecker")
public class AuthorityChecker {

    @Autowired
    UserService userService;

    public boolean isAllowed(Authentication authentication) {
        /*User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new BadCredentialsException("Erro ao identificar permissão!"));
        return user.getIsAdmin();*/
        return true;
    }

}
