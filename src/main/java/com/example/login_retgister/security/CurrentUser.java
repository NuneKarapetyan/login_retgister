package com.example.login_retgister.security;



import com.example.login_retgister.models.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(),user.getActivationToken()==null,true,
                true,user.isNonLocked(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
