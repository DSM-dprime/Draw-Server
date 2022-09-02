package com.project.draw.domain.user.facade;

import com.project.draw.domain.user.domain.User;
import com.project.draw.domain.user.domain.repository.UserRepository;
import com.project.draw.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(email);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByAccountId(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public boolean emailIsExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}