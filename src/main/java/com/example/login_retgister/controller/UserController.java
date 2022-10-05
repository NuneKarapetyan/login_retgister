package com.example.login_retgister.controller;


import com.example.login_retgister.models.User;
import com.example.login_retgister.repositories.UserRepository;
import com.example.login_retgister.security.CurrentUser;
import com.example.login_retgister.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;


    @PostMapping("/register")
    public String register(@ModelAttribute User user,
                           RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("regInfo", userService.save(user));
        return "redirect:/login";
    }

//    @GetMapping("/activate")
//    public String activate(@RequestParam("token") String token) {
//        return userService.activate(token);
//    }

    @GetMapping(value = "/activate")
    public String verify(ModelMap modelMap,
                         @RequestParam("token") String token,
                         @RequestParam("email") String email) {


     return userService.verify(modelMap, token, email);
    }


    @GetMapping("/home")
    public String userHome(ModelMap modelMap,
                           @AuthenticationPrincipal UserDetails userDetails) {
        CurrentUser currentUser = (CurrentUser) userDetails;
        if (currentUser != null) {
            modelMap.addAttribute("currentUser", userRepository.findByEmail(currentUser.getUser().getEmail()).get());
            return "user-page";
        } else {
            return "redirect:/login?errorMsg=Invalid credentials";
        }
    }


    @PostMapping("/logout")
    public String logout(){
        return "index";
    }
}
