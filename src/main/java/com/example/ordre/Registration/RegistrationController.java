package com.example.ordre.Registration;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/registration")
@AllArgsConstructor

public class RegistrationController {
    private RegistrationService registrationService ;



    @PostMapping

    public ResponseEntity<RegistrationRequest> register(@RequestBody RegistrationRequest request){
        System.out.println(registrationService.register(request));
        return new ResponseEntity<>(request ,HttpStatus.CREATED);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
