package com.example.login_retgister.controller;

import com.example.login_retgister.models.User;
import com.example.login_retgister.models.enums.Role;
import com.example.login_retgister.repositories.UserRepository;
import com.example.login_retgister.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final UserRepository userRepository;



    @GetMapping("/home")
    public String home(@AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        if(user.getRole()== Role.USER)
            return "redirect:/user/home";
        else return "redirect:/admin/home";
    }
    @GetMapping("/login")
    public String pageLogin(ModelMap map,
                            @RequestParam(value = "error", required = false)String error) {
        map.addAttribute("login_user", new User());
        map.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/logout")
    public String logout(){
        return "index";
    }




    @GetMapping("/verifyError")
    public String verifyError() {
        return "verifyError";
    }


}
