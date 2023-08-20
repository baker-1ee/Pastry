package com.baker1ee.pastry.domain.user.repository;

import com.baker1ee.pastry.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> existsByEmail(String email);

    Optional<User> findByEmail(String username);
}
