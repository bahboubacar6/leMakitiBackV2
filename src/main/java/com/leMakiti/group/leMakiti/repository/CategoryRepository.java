package com.leMakiti.group.leMakiti.repository;

import com.leMakiti.group.leMakiti.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findCategoryByCode(String code);
}
