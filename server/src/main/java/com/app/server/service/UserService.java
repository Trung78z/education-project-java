package com.app.server.service;

import com.app.server.model.User;
import com.app.server.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User SaveUser(User user) {

        String hashPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return   userRepository.save(user);
    }

    @Transactional
    public void editUser(User user, User UserNew) {
        if (UserNew.getPassword() != null) {
            user.setPassword(UserNew.getPassword());
        }
        if (UserNew.getBirthDay() != null) {
            user.setBirthDay(UserNew.getBirthDay());
        }
        if (UserNew.getPhone() != null) {
            user.setPhone(UserNew.getPhone());
        }
        userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public Optional<User> getUserById(String userId) {
        Optional<User> userOptional = userRepository.findUserById(userId);
        return userOptional;
    }

    public Optional<User> getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        return optionalUser;
    }

    public Optional<User> getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        return optionalUser;
    }

    public Optional<User> getUserByPhone(String phone) {
        Optional<User> optionalUser = userRepository.findUserByPhone(phone);
        return optionalUser;
    }


}
