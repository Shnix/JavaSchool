package controller;

import entity.Driver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class Test {

    @RequestMapping("/")
    public ResponseEntity<Driver> hey(){
         return new ResponseEntity<>(new Driver(), HttpStatus.OK);
    }
}
