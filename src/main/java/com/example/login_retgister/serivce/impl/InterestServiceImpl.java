package com.example.login_retgister.serivce.impl;

import com.example.login_retgister.models.Interest;
import com.example.login_retgister.repositories.InterestRepository;
import com.example.login_retgister.serivce.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestServiceImpl  implements InterestService {


    private final InterestRepository interestRepository;


    @Override
    public List<Interest> allInterests() {
        return interestRepository.findAll();
    }
}
