package com.app.server.repository;

import com.app.server.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository  extends JpaRepository<Profile, Long> {
    Optional<Profile> findProfileById(Long id);
    Optional<Profile> findProfileByBankAccountNumber(String email);
    Optional<Profile> findProfileByTotal(Long total);

}
