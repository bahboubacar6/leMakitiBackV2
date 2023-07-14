package com.leMakiti.group.leMakiti.repository;

import com.leMakiti.group.leMakiti.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findArticleByCodeArticle(String code);
}
