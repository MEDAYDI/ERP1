package com.example.ordre.repository;

import com.example.ordre.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u set u.enable = true  where  u.email = ?1")
    int enableAppUser(String email);

    boolean existsByUsername(String username);
}
