package com.example.csis3275project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private RegisterRestController registerController;

    @GetMapping("/login")
    public String login(){
        return "loginPage";
    }

    @GetMapping("/registration")
    public String register(){ return "signUpPage";}

}
