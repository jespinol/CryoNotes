package com.jmel.cryonotes.controller;

import com.jmel.cryonotes.model.User;
import com.jmel.cryonotes.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class SignInUpController {

    @Autowired
    private UserRepository userRepository;

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @GetMapping(value={"/", "/home"})
    public String viewStartPage() {
        if (isAuthenticated()) {
            return "/home";
        }
        return "/login/index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/login/signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/login/signup_form";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return viewStartPage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(Model model) {
        model.addAttribute("emailExists", true);
        return showRegistrationForm(model);
    }

    @PostMapping("/")
    public String loginUser() {
        return "/home";
    }

    @GetMapping("/edit_profile")
    public String edit(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();
        User user = userRepository.findByEmail(email);
        model.addAttribute("user", user);
        return "edit_profile";
    }

//    @PostMapping("/save_profile")
//    public String saveChangesSimple(@Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "/edit_profile";
//        }
//        userRepository.save(user);
//        return "/home";
//    }

    @PostMapping("/save_profile")
    public String changeUserPassword(User user, @RequestParam("password") String password, @RequestParam("oldpassword") String oldPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            System.out.println("Passwords matched");
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            userRepository.save(user);
        } else {
            return "/edit_profile";
        }
        return "/home";
    }
}
