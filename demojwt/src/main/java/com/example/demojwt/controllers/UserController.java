package com.example.demojwt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello User"; // Tên này phải trùng với tệp view
    }
}
