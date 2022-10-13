package com.example.login_retgister.serivce;

import com.example.login_retgister.models.Article;
import com.example.login_retgister.models.Comment;
import com.example.login_retgister.models.User;

import java.util.List;

public interface CommentService {

    Comment save(Comment article, User author);

}
