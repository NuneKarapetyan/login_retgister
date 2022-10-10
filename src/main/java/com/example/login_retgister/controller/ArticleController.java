package com.example.login_retgister.controller;

import com.example.login_retgister.models.Article;
import com.example.login_retgister.security.CurrentUser;
import com.example.login_retgister.serivce.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
@Log4j2
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public String addArticle(@ModelAttribute Article article,
                             @AuthenticationPrincipal CurrentUser currentUser){
//        ()SecurityContextHolder.getContext().getAuthentication()
        Article savedArticle = articleService.save(article, currentUser.getUser());
        log.info("Saving new article {}", savedArticle);
        return "redirect:/user/home";
    }
}
