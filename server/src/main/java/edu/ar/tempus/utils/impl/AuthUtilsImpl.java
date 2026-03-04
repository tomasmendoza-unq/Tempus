package edu.ar.tempus.utils.impl;

import edu.ar.tempus.security.user.UserDetailsImpl;
import edu.ar.tempus.utils.AuthUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthUtilsImpl implements AuthUtils {
    public Long getAlumnoId(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getId();
    }
}