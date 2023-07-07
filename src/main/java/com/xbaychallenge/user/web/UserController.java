package com.xbaychallenge.user.web;

import com.xbaychallenge.user.UserService;
import com.xbaychallenge.user.repository.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody CreateUserRequest request){
        UserEntity user = service.createUser(request);
        return ResponseEntity.ok(user);
    }
}
