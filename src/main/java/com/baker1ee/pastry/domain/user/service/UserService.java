package com.baker1ee.pastry.domain.user.service;

import com.baker1ee.pastry.domain.user.dto.request.UserCreateRequest;
import com.baker1ee.pastry.domain.user.entity.User;
import com.baker1ee.pastry.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserCreateRequest request) {
        return userRepository.save(User.of(request));
    }

    public List<User> retrieveUser() {
        return userRepository.findAll();
    }
}
