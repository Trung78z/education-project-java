package com.api.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.backend.dto.users.ChangePasswordRequest;

import com.api.backend.dto.users.UserDTO;
import com.api.backend.dto.users.UserLoginDTO;
import com.api.backend.dto.users.UserToken;
import com.api.backend.models.user.Users;
import com.api.backend.services.JWTService;
import com.api.backend.services.UserService;
import com.api.backend.utils.ResponseWrapper;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1/auth")

public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTService tokenService;

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<UserDTO>> register(@RequestBody Users user) {
        try {
            Users savedUser = userService.saveUser(user);
            UserDTO userDTO = new UserDTO(savedUser);
            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, userDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
        }

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<UserToken>> login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            Users user = new Users();
            user.setUsername(userLoginDTO.getUsername());
            user.setPassword(userLoginDTO.getPassword());
            String token = userService.verify(user);
            if (token.equals("fail")) {
                return ResponseEntity.badRequest()
                        .body(new ResponseWrapper<>(400, "Invalid username or password"));
            }

            UserToken userDTO = new UserToken(token);
            userDTO.setToken(token);
            return ResponseEntity.ok(new ResponseWrapper<>(true, 200, userDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseWrapper<>(400, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
        }
    }

    @GetMapping("/check-token")
    public ResponseEntity<ResponseWrapper<String>> checkToken(HttpServletRequest httpServletRequest) {
        try {
            String token = httpServletRequest.getHeader("Authorization").substring(7);
            boolean isValid = tokenService.validateToken(token);
            System.out.println(token);
            if (isValid) {
                return ResponseEntity.ok(new ResponseWrapper<>(true, 200, "Token is valid"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ResponseWrapper<>(401, "Invalid token"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<ResponseWrapper<String>> changePassword(@RequestBody ChangePasswordRequest request,
            HttpServletRequest httpServletRequest) {
        try {
            String token = httpServletRequest.getHeader("Authorization").substring(7);
            String username = tokenService.extractUserName(token);

            boolean isChanged = userService.changePassword(username, request.getOldPassword(),
                    request.getNewPassword());
            if (isChanged) {
                return ResponseEntity.ok(new ResponseWrapper<>(200, "Password changed successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseWrapper<>(400, "Invalid old password"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseWrapper<>(500, "Internal Server Error"));
        }
    }
}
