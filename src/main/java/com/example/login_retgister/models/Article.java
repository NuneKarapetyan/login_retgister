package com.example.login_retgister.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column
    private String content;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="author_id", nullable=false)
    private User author;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="interest_id", nullable=false)
    private Interest interest;

    @OneToMany(mappedBy = "article")
    private Set<Comment> comments;

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
        Article article = (Article) o;
        return id == article.id && Objects.equals(title, article.title) && Objects.equals(content, article.content) && Objects.equals(author, article.author) && Objects.equals(interest, article.interest) && Objects.equals(created, article.created) && Objects.equals(updated, article.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, author, interest, created, updated);
    }
}
