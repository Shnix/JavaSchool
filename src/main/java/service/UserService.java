package service;

import auth.JwtTokenProvider;
import dao.UserDao;
import dto.LoginDto;
import entity.User;
import enums.Role;
import exception.SecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class UserService {

    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    private JwtTokenProvider jwtTokenProvider;

    private AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public LoginDto signIn(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userDao.findByUsername(username);
            return LoginDto.builder()
                    .id(String.valueOf(user.getId()))
                    .role(user.getRole().toString())
                    .token(jwtTokenProvider.createToken(username, new ArrayList<Role>(Arrays.asList(userDao.findByUsername(username).getRole()))))
                    .build();
        } catch (AuthenticationException e) {
            throw new SecurityException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid username/password supplied");
        }
    }

    public String signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
        return jwtTokenProvider.createToken(user.getUsername(), new ArrayList<Role>(Arrays.asList(user.getRole())));

    }
}
