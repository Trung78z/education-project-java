package com.app.server.service;

import org.springframework.stereotype.Service;

import com.app.server.model.User;
import com.app.server.repository.AuthorCationRepository;

import java.util.Optional;

@Service
public class AuthorCationService {
    private final AuthorCationRepository authorCationRepository;

    public AuthorCationService(AuthorCationRepository authorCationRepository) {
        this.authorCationRepository = authorCationRepository;
    }

    public Optional<User> getUserByEmail(String email) {
        Optional<User> authOptional = authorCationRepository.findUserByEmail(email);
        return authOptional;
    }

    public Optional<User> getUserByUsername(String username) {
        Optional<User> authOptional = authorCationRepository.findUserByUsername(username);
        return authOptional;
    }

}
