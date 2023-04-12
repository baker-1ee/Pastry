package com.baker1ee.pastry.domain.user.service;

import com.baker1ee.pastry.domain.user.dto.request.UserCreateRequest;
import com.baker1ee.pastry.domain.user.repository.UserRepository;
import com.baker1ee.pastry.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public User createUser(UserCreateRequest request) {
        Long id = request.getUserSeq();
        User user = User.of(id, request);
        return userRepository.save(user);
    }

    public List<User> retrieveUser() {
        return userRepository.findAll();
    }
}
