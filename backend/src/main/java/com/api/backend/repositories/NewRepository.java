package com.api.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.backend.models.news.New;

import jakarta.transaction.Transactional;

@Repository
public interface NewRepository extends JpaRepository<New, Integer> {
    @SuppressWarnings("null")
    Optional<New> findById(Integer id);

    Optional<New> findByTitleAndNewCategoryId(String title, Integer categoryId);

    @Modifying
    @Transactional
    @Query("DELETE FROM New n WHERE n.id = ?1")
    void deleteById(Integer id);
}
