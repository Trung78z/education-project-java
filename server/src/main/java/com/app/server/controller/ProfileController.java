package com.app.server.controller;
import com.app.server.model.Profile;
import com.app.server.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/api/v1/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProfile(@RequestBody Profile profile) {
        if (profile.getUser() == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "User cannot be null"));
        }
        profileService.createProfile(profile);
        return ResponseEntity.status(201).body(Map.of("success", true, "messages", "created"));
    }
}
