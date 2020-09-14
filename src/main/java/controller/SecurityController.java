package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.LoginDto;
import dto.UserDto;
import entity.User;
import enums.Role;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/security")
public class SecurityController {

    private UserService userService;

    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signin")
    public LoginDto login(@RequestBody UserDto userDto) {
        return userService.signIn(userDto.getLogin(), userDto.getPassword());
    }


}
