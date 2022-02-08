package com.jmel.cryonotes.controllers;

import com.jmel.cryonotes.models.User;
import com.jmel.cryonotes.models.data.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class SignInUpController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String viewStartPage() {
        return "/login/index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/login/signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "/login/signup_form";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "/login/index";
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation() {
        return "redirect:/register";
    }


    @GetMapping("/home")
    public String listUsers(Model model) {
        return "/home";
    }
}
