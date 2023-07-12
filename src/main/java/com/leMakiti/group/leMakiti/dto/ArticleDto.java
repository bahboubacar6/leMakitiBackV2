package com.leMakiti.group.leMakiti.dto;

import com.leMakiti.group.leMakiti.model.Category;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto {

    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategoryDto categoryDto;
}
