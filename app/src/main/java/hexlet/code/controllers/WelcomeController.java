package hexlet.code.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@RestController
@RestController
@RequestMapping("/welcome")
public class WelcomeController {

//    @GetMapping("/")
    @GetMapping
    public String root() {
        return "Welcome to Spring";
    }

}
