package com.example.login_retgister.repositories;

import com.example.login_retgister.models.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Integer> {


}
