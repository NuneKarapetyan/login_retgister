package com.example.login_retgister.controller;


import com.example.login_retgister.models.Article;
import com.example.login_retgister.models.Comment;
import com.example.login_retgister.models.User;
import com.example.login_retgister.repositories.UserRepository;
import com.example.login_retgister.security.CurrentUser;
import com.example.login_retgister.serivce.ArticleService;
import com.example.login_retgister.serivce.InterestService;
import com.example.login_retgister.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final InterestService interestService;
    private final ArticleService articleService;

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
    @PreAuthorize("hasAuthority('USER')")
    public String userHome(ModelMap modelMap,
                           @AuthenticationPrincipal UserDetails userDetails) {
        CurrentUser currentUser = (CurrentUser) userDetails;
        if (currentUser != null) {
            modelMap.addAttribute("currentUser", userRepository.findByEmail(currentUser.getUser().getEmail()).get());
            modelMap.addAttribute("article", new Article());
            modelMap.addAttribute("interests", interestService.allInterests());
            modelMap.addAttribute("myArticles", articleService.articlesByAuthor(currentUser.getUser()));
            modelMap.addAttribute("comment", new Comment());
            return "user-page";
        } else {
            return "redirect:/login?errorMsg=Invalid credentials";
        }
    }


    @PostMapping("/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String changeUserStatus(@RequestParam(name = "status")boolean status,
                                   @RequestParam(name = "userId")int useId){
        userService.changeUserStatus(status, useId);
        return "redirect:/admin/home";

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
