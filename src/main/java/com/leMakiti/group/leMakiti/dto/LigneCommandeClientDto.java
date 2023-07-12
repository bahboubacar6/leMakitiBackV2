package com.leMakiti.group.leMakiti.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeClientDto {

    private Integer id;

    private ArticleDto articleDto;

    private CommandeClientDto commandeClientDto;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;
}