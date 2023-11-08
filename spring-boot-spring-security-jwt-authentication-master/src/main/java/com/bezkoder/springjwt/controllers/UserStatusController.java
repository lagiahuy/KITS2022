package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.UserStatus;
import com.bezkoder.springjwt.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class UserStatusController {
    @Autowired
    private UserStatusRepository userStatusRepository;

    @GetMapping("/userStatus")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<UserStatus> getAllUserStatus() {
        return userStatusRepository.findAll();
    }

    @GetMapping("/userStatus/{id}")
    public ResponseEntity< UserStatus > getBookById(@PathVariable Long id) {
        UserStatus userStatus = userStatusRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(userStatus);
    }
}
