package com.app.server.service;

import com.app.server.model.Profile;
import com.app.server.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void createProfile(Profile profile) {
        profileRepository.save(profile);
    }

}
