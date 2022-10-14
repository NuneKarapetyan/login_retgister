package com.example.login_retgister.controller;


import com.example.login_retgister.models.Article;
import com.example.login_retgister.models.Comment;
import com.example.login_retgister.models.User;
import com.example.login_retgister.models.enums.Role;
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
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final InterestService interestService;
    private final ArticleService articleService;

    @Value("${user.image.path}")
    private String userImagesFolder;


    @GetMapping("/home")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userHome(ModelMap modelMap,
                           @AuthenticationPrincipal UserDetails userDetails) {
        modelMap.addAttribute("allUsers", userRepository.findAllByRole(Role.USER));
        return "admin-page";
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
