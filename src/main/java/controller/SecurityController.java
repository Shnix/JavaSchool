package controller;

import dto.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/security")
public class SecurityController {

    @PutMapping("/")
    public void add(@RequestBody UserDto userDto) {
        System.out.println("this");
        System.out.println(userDto);
    }
}
