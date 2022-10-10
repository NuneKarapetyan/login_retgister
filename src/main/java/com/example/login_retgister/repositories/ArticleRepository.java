package com.example.login_retgister.repositories;

import com.example.login_retgister.models.Article;
import com.example.login_retgister.models.Interest;
import com.example.login_retgister.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query("select a from Article a where a.author=:author")
    List<Article> findAllByAuthor(@Param("author") User author);

}
