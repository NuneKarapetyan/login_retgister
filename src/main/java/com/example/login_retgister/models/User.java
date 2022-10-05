package com.example.login_retgister.models;

import com.example.login_retgister.models.enums.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private int age;

    @Column
    private boolean nonLocked;

    @OneToMany(mappedBy = "author")
    private Set<Article> articles;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_interests",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "interest_id")}
    )
    private List<Interest> interests;


    @Column(columnDefinition = "VARCHAR(256)")
    private String activationToken;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;


    @Column
    private String image;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime activationDate;

    @Column
    private LocalDateTime updated;


    @PrePersist
    public void onCreate() {
        this.nonLocked = true;
        this.created = LocalDateTime.now();
        this.updated = created;
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = LocalDateTime.now();
    }


}
