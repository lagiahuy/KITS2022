package com.bezkoder.springjwt.controllers;



import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List <User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity< User > getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();;
        return ResponseEntity.ok(user);
    }
    @PutMapping("/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity < User > updateUser(@PathVariable Long id, @RequestBody User userDetail) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFullname(userDetail.getFullname());
        user.setEmail(userDetail.getEmail());
        user.setAddress(userDetail.getAddress());
        user.setPhone(userDetail.getPhone());
        user.setUrlimage(userDetail.getUrlimage());

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <Map< String, Boolean >> deleteEmployee(@PathVariable Long id) {
        User employee = userRepository.findById(id).orElseThrow();
        userRepository.delete(employee);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
