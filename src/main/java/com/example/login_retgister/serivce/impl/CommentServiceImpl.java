package com.example.login_retgister.serivce.impl;


import com.example.login_retgister.models.Comment;
import com.example.login_retgister.models.User;
import com.example.login_retgister.repositories.CommentRepository;
import com.example.login_retgister.serivce.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;


    @Override
    public Comment save(final Comment comment, final User author) {
        comment.setAuthor(author);
        return commentRepository.save(comment);
    }
}
