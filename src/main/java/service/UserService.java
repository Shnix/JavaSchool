package service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsService {
    public UserDetails loadUserByUsername(String username) {

    }
}
