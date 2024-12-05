package com.api.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.backend.models.news.New;

public interface NewRepository extends JpaRepository<New, Integer> {
    @SuppressWarnings("null")
    Optional<New> findById(Integer id);
}
