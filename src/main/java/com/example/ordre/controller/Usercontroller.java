package com.example.ordre.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.ordre.dto.LoginResponse;
import com.example.ordre.filter.LoginForm;
import com.example.ordre.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api")
@Slf4j
public class Usercontroller {
    @Autowired
    private AuthenticationManager authenticationManager ;





    @PostMapping("/login")
    
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginForm request){
        String username=request.getUsername();
        String password =request.getPassword();
        log.info("Username is :{}",username);
        log.info("Password is :{}",password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authentication =authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm =Algorithm.HMAC256("secret".getBytes());
        
        String access_token= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ 864_000_000 )) //10 days
                .sign(algorithm);

        String refrech_token=JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ 30*60*1000))
                .sign(algorithm);

        LoginResponse loginResponse =new LoginResponse(access_token,refrech_token,user.getUsername(),user.getPassword());


        return new ResponseEntity<>(loginResponse,HttpStatus.OK) ;
    }


}




