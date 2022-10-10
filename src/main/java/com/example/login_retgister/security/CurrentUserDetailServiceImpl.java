package com.example.login_retgister.security;




import com.example.login_retgister.models.User;
import com.example.login_retgister.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrentUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;

//    @Autowired
//    public CurrentUserDetailServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    @Autowired
//    public void setUserRepository(UserRepository userRepository){
//        this.userRepository=userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new CurrentUser(user.get());
    }
}
