package com.app.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.api.model.Profile;
import com.app.api.repositories.ProfileRepository;

@Service
public class ProfileService {
    @Autowired
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void createProfile(Profile profile) {
        profileRepository.save(profile);
    }

}
