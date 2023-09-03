package com.baker1ee.pastry.security.auth.controller;

import com.baker1ee.pastry.security.auth.dto.AuthenticationRequest;
import com.baker1ee.pastry.security.auth.dto.AuthenticationResponse;
import com.baker1ee.pastry.security.auth.dto.RegisterRequest;
import com.baker1ee.pastry.security.auth.dto.UserResponse;
import com.baker1ee.pastry.security.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok(authenticationService.getUser());
    }

}
