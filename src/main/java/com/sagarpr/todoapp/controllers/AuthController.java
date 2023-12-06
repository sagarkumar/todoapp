package com.sagarpr.todoapp.controllers;

import com.sagarpr.todoapp.dto.UserRegistrationDto;
import com.sagarpr.todoapp.models.UserEntity;
import com.sagarpr.todoapp.services.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AuthController {

    private UserEntityService userEntityService;

    @Autowired
    public AuthController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("userRegistrationDto",userRegistrationDto);
        return "user-register";
    }

    @PostMapping("/register")
    public String saveUserRegistration(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
                                       BindingResult bindingResult, Model model){

        boolean isExisting = this.userEntityService.isExistingUser(userRegistrationDto);
        if(isExisting){
            model.addAttribute("userRegistrationDto",userRegistrationDto);
            bindingResult.rejectValue("email","There is already user with this email");
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("userRegistrationDto",userRegistrationDto);
            return "user-register";
        }
        this.userEntityService.saveUser(userRegistrationDto);
        return "redirect:/mytodo";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
}
