package com.example.csis3275project.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="api/v1/registration")
@AllArgsConstructor
public class RegisterRestController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        registrationService.register(request);
        return "registration processed";
    }

    @GetMapping(path="confirm")
    public String confirm(@RequestParam("token") String token){ return registrationService.confirmToken(token); }

}
