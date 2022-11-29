package com.example.csis3275project.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HelpController {

    @GetMapping("/howitworks")
    public String howItWorks(){
        return "hiwLogged";
    }
}
