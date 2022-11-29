package com.example.csis3275project.web;

import com.example.csis3275project.entities.Account;
import com.example.csis3275project.entities.AccountService;
import com.example.csis3275project.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RegisterController {

    private RegisterRestController registerRestController;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final AccountService accountService;
    private final RegistrationService registrationService;
    private String[] info = new String[3];


    @RequestMapping(value = "/process_registration", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute Account account, Model model){
        String fname;
        String lname;
        String email;
        String pwd;

        fname = account.getFirstName();
        lname = account.getLastName();
        email = account.getEmail();
        pwd = account.getPassword();

        // Go though all error checking before sending request (token)
        // 1. email formation
        // 2. password security
        // 3. existing account
        if(!emailValidator.test(email)){
            // email format is not douglas college email format
            model.addAttribute("errorMsg", "Please only use your Douglas college email");
            model.addAttribute("firstName", fname);
            model.addAttribute("lastName", lname);
            model.addAttribute("email", email);
            model.addAttribute("password", pwd);
            return "signUpPage";
        }else if(!passwordValidator.test(pwd)){
            // password is not secure
            model.addAttribute("errorMsg", "Your Password is not secure enough. \n" +
                                                                    "Please make sure your password contains: \n" +
                                                                    "- minimum eight characters\n" +
                                                                    "- at least one uppercase letter\n" +
                                                                    "- one lowercase letter\n" +
                                                                    "- one number\n" +
                                                                    "- one special character");
            model.addAttribute("firstName", fname);
            model.addAttribute("lastName", lname);
            model.addAttribute("email", email);
            model.addAttribute("password", pwd);
            return "signUpPage";
        } else if(accountService.isAccountExisted(email)){
            // account already existed
            model.addAttribute("errorMsg", "This email has been used. Please try another email address.");
            model.addAttribute("firstName", fname);
            model.addAttribute("lastName", lname);
            model.addAttribute("email", email);
            model.addAttribute("password", pwd);
            return "signUpPage";
        } else {
            RegistrationRequest accountReq = new RegistrationRequest(fname, lname, email, pwd);
            info = registrationService.register(accountReq);
        }

        model.addAttribute("confirmMsg", "Confirmation Email have sent. Please verify your email address.");
        model.addAttribute("resendMsg", "Resend the email.");
        return "signUpPage";
    }

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    public String confirmToken(@RequestParam("token") String token, Model model){
        if(!registrationService.checkToken(token)){
            model.addAttribute("errMsg", "Your account activation went wrong.");
            return "confirmPage";
        }

        model.addAttribute("accountMsg", "Your account activation is successful.");
        return "confirmPage";
    }

    @GetMapping("/resendEmail")
    public String resendEmail(Model model){
        String email = info[0];
        String link = info[1];
        String fname = info[2];

        registrationService.resendEmail(email, link, fname);

        model.addAttribute("confirmMsg","Confirmation Email have sent again. Please verify your email address.");
        model.addAttribute("resendMsg", "Resend the email.");
        return "signUpPage";
    }
}
