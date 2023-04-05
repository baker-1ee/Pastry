package com.baker1ee.pastry.user.controller;

import com.baker1ee.pastry.user.dto.request.UserCreateRequest;
import com.baker1ee.pastry.user.entity.User;
import com.baker1ee.pastry.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request) {
        User user = userService.createUser(request);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> retrieveUser() {
        List<User> users = userService.retrieveUser();
        return ResponseEntity.ok(users);
    }
}
