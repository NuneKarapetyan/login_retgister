package com.example.login_retgister.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@ToString(exclude = {"article"})
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    @Column
    private String content;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;


    @Column
    private LocalDateTime created;


    @Column
    private LocalDateTime updated;


    @PrePersist
    public void onCreate() {
        this.created = LocalDateTime.now();
        this.updated = created;
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = LocalDateTime.now();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment article = (Comment) o;
        return id == article.id && Objects.equals(content, article.content) &&
                Objects.equals(author, article.author) && Objects.equals(created, article.created)
                && Objects.equals(updated, article.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, author, created, updated);
    }
}
