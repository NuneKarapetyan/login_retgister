package com.example.login_retgister.controller;


import com.example.login_retgister.models.User;
import com.example.login_retgister.repositories.UserRepository;
import com.example.login_retgister.security.CurrentUser;
import com.example.login_retgister.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Value("${user.image.path}")
    private String userImagesFolder;


    @PostMapping("/register")
    public String register(@ModelAttribute User user,
                           @RequestPart(name = "avatar") MultipartFile userAvatar,
                           RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("regInfo", userService.save(user, userAvatar));
        return "redirect:/login";
    }


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


//    @GetMapping("/image/{imageName}")
//    public ResponseEntity<Resource> getFile(@PathVariable(name = "imageName") String imageName) throws IOException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + imageName);
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        FileInputStream fileInputStream = new FileInputStream(userImagesFolder + imageName);
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(fileInputStream.available())
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(new InputStreamResource(fileInputStream));
//    }

}
