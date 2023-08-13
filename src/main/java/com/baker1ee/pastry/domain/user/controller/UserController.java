package com.baker1ee.pastry.domain.user.controller;

import com.baker1ee.pastry.domain.user.dto.request.UserCreateRequest;
import com.baker1ee.pastry.domain.user.entity.User;
import com.baker1ee.pastry.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/regist")
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
