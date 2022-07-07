package com.example.ordre.token;

import com.example.ordre.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String token ;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt ;
    private LocalDateTime confirmedAt;


    @ManyToOne
    @JoinColumn(
            nullable = false,
            name ="app_user_id"
    )
    private User user ;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt , User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
