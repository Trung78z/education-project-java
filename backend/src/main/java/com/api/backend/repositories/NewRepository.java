package com.api.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.backend.models.news.New;

@Repository
public interface NewRepository extends JpaRepository<New, Integer> {
    @SuppressWarnings("null")
    Optional<New> findById(Integer id);

    Optional<New> findByTitleAndNewCategoryId(String title, Integer categoryId);
}
