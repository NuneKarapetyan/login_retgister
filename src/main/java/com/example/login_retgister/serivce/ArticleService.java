package com.example.login_retgister.serivce;

import com.example.login_retgister.models.Article;
import com.example.login_retgister.models.Interest;
import com.example.login_retgister.models.User;

import java.util.List;

public interface ArticleService {

    Article save(Article article, User author);

    List<Article> articlesByAuthor(User user);
}
