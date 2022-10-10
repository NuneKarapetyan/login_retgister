package com.example.login_retgister.serivce.impl;

import com.example.login_retgister.models.Article;
import com.example.login_retgister.models.Interest;
import com.example.login_retgister.models.User;
import com.example.login_retgister.repositories.ArticleRepository;
import com.example.login_retgister.repositories.InterestRepository;
import com.example.login_retgister.serivce.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {


    private final ArticleRepository articleRepository;


    @Override
    public Article save(final Article article, final User author) {
        article.setAuthor(author);
        return articleRepository.save(article);
    }

    @Override
    public List<Article> articlesByAuthor(final User user) {
        return articleRepository.findAllByAuthor(user);
    }
}
