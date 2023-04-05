package com.baker1ee.pastry.user.service;

import com.baker1ee.pastry.user.dto.request.UserCreateRequest;
import com.baker1ee.pastry.user.repository.UserRepository;
import com.baker1ee.pastry.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public User createUser(UserCreateRequest request) {
        // TODO: 2023/04/06 ID 생성기 작성 필요 
        Long id = 1L;
        User user = User.of(id, request);
        return userRepository.save(user);
    }

    public List<User> retrieveUser() {
        return userRepository.findAll();
    }
}
