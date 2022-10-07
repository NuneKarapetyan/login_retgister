package com.example.login_retgister.serivce;

import com.example.login_retgister.models.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    String save(User user, MultipartFile userAvatar);

    String activate(String token);

    String verify(ModelMap modelMap, String token, String email);
}
