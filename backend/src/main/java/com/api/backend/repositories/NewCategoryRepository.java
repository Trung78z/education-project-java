package com.api.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.backend.models.news.NewCategory;

@Repository
public interface NewCategoryRepository extends JpaRepository<NewCategory, Integer> {
    @SuppressWarnings("null")
    Optional<NewCategory> findById(Integer id);

    Optional<NewCategory> findByName(String name);

    @Query("SELECT c FROM NewCategory c LEFT JOIN FETCH c.news WHERE c.name = :name")
    NewCategory findNewCategoryWithNews(@Param("name") String name);

}