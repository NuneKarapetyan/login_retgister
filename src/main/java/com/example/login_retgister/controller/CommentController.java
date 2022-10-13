package com.example.login_retgister.controller;

import com.example.login_retgister.models.Article;
import com.example.login_retgister.models.Comment;
import com.example.login_retgister.security.CurrentUser;
import com.example.login_retgister.serivce.ArticleService;
import com.example.login_retgister.serivce.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
@Log4j2
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public String addComment(@ModelAttribute Comment comment,
                             @AuthenticationPrincipal CurrentUser currentUser,
                             @RequestParam(name = "articleId" )int articleId){
        comment.setArticle(Article.builder().id(articleId).build());
        Comment savedComment = commentService.save(comment, currentUser.getUser());

        log.info("Saving new comment {}", savedComment);
        return "redirect:/user/home";
    }
}
