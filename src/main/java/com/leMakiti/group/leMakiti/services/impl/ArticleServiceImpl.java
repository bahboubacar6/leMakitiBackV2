package com.leMakiti.group.leMakiti.services.impl;

import com.leMakiti.group.leMakiti.dto.ArticleDto;
import com.leMakiti.group.leMakiti.exception.EntityNotFoundException;
import com.leMakiti.group.leMakiti.exception.ErrorCodes;
import com.leMakiti.group.leMakiti.exception.InvalidEntityException;
import com.leMakiti.group.leMakiti.model.Article;
import com.leMakiti.group.leMakiti.repository.ArticleRepository;
import com.leMakiti.group.leMakiti.services.ArticleService;
import com.leMakiti.group.leMakiti.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(
            ArticleRepository articleRepository
    ) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if (!errors.isEmpty()){
            log.error("Article is not valid {}", articleDto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null){
            log.error("Article ID is null");
            return null;
        }

        Optional<Article> article = articleRepository.findById(id);

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() -> new EntityNotFoundException(
                "Aucun Article avec l'ID = " + id + "n'a ete trouve dans la BDD",
                ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (!StringUtils.hasLength(codeArticle)){
            log.error("Article code is null");
            return null;
        }

        Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() -> new EntityNotFoundException(
                "Aucun Article avec le CODE = " + codeArticle + "n'a ete trouve dans la BDD",
                ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Article ID is null");
            return ;
        }
        articleRepository.deleteById(id);
    }
}
