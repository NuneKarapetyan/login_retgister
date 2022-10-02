package com.example.login_retgister.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(columnDefinition = "VARCHAR(256)")
    private String activationToken;

    @Column
    private LocalDateTime created;


    @Column
    private LocalDateTime activationDate;



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


}
