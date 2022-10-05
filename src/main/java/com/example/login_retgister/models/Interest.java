package com.example.login_retgister.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "interests")
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column
    private LocalDateTime created;

//    @ManyToMany(mappedBy = "interests")
//    private List<User> users;


    @PrePersist
    public void onCreate() {
        this.created = LocalDateTime.now();
    }



}
