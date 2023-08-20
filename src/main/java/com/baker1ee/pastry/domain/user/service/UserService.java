package com.baker1ee.pastry.domain.user.service;

import com.baker1ee.pastry.domain.user.dto.request.UserCreateRequest;
import com.baker1ee.pastry.domain.user.entity.User;
import com.baker1ee.pastry.domain.user.repository.UserRepository;
import com.baker1ee.pastry.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public String signUp(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 ID 입니다.");
        }
        User user = userRepository.save(User.of(request));
        return jwtService.generateToken(user);
    }

    public List<User> retrieveUser() {
        return userRepository.findAll();
    }
}
