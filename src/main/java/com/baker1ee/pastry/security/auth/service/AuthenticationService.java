package com.baker1ee.pastry.security.auth.service;

import com.baker1ee.pastry.security.auth.dto.AuthenticationRequest;
import com.baker1ee.pastry.security.auth.dto.AuthenticationResponse;
import com.baker1ee.pastry.security.auth.dto.RegisterRequest;
import com.baker1ee.pastry.security.auth.dto.UserResponse;
import com.baker1ee.pastry.security.auth.repository.UserRepository;
import com.baker1ee.pastry.security.auth.user.User;
import com.baker1ee.pastry.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        validate(request);
        User user = User.of(request, passwordEncoder);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.of(jwtToken, UserResponse.from(user));
    }

    private void validate(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용중인 ID 입니다.");
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.of(jwtToken, UserResponse.from(user));
    }

    public UserResponse getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserResponse.from(user);
    }
}
