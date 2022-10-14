package com.example.login_retgister.repositories;

import com.example.login_retgister.models.User;
import com.example.login_retgister.models.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    Optional<User> findByActivationToken(String token);

    List<User> findAllByRole(Role role);

    @Modifying
    @Transactional
    void deleteAllByCreatedBeforeAndActivationTokenIsNotNull(LocalDateTime created);
    User findByEmailAndActivationTokenOrAgeGreaterThan(String emai, UUID toke, int age);



    @Query(value = "select u from User u where u.email=:emo and u.password=:passo")
    Optional<User> findByEmailAndPassword(@Param("emo") String email,
                                          @Param("passo") String password);

    Optional<User> findByEmail(String email);
}
