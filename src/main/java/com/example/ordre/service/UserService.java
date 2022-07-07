package com.example.ordre.service;

import com.example.ordre.model.User;
import com.example.ordre.repository.UserRepo;
import com.example.ordre.token.ConfirmationToken;
import com.example.ordre.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserService  implements UserDetailsService {
    private static String USER_NOT_FOUND_MSG = "user with email %s not found ";
    private UserRepo userRepo ;
    private BCryptPasswordEncoder bCryptPasswordEncoder ;
    private ConfirmationTokenService confirmationTokenService ;



    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepo.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found !!");
        }else
        {
            return  userRepo.findByUsername(username);
        }


    }





    public String singUpUser(User user){
        boolean emailExists =userRepo
                .findByEmail(user.getEmail())
                .isPresent() ;
        if (emailExists){
            throw new IllegalStateException("email already taken");

        }
        if (userRepo.existsByUsername(user.getUsername())){
            throw new IllegalStateException("userName already taken");
        }

       String encodedPassword = bCryptPasswordEncoder
               .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepo.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        // TODO : Send confirmation  token
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token ;
    }


    public int enableAppUser(String email){
        return userRepo.enableAppUser(email);
    }


}
