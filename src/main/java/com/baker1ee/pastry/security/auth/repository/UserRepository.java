package com.baker1ee.pastry.security.auth.repository;

import com.baker1ee.pastry.security.auth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String username);
}
