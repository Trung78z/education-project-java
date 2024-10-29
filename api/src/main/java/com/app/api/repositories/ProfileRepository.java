package com.app.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.api.model.Profile;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findProfileById(Long id);

    Optional<Profile> findProfileByBankAccountNumber(String email);

    Optional<Profile> findProfileByTotal(Long total);

}
