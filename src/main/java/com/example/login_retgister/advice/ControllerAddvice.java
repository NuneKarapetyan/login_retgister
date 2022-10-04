//package com.example.login_retgister.advice;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.Optional;
//
//@ControllerAdvice
//public class ControllerAddvice {
//
//
//    @ExceptionHandler(UsernameNotFoundException.class)
////    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleHttpMessageNotWritableException(UsernameNotFoundException exception){
//        return "redirect:/login?errorMsg=Invalid credentials";
//    }
//
//}
