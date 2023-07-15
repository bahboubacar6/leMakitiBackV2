package com.leMakiti.group.leMakiti.dto;

import com.leMakiti.group.leMakiti.model.LigneVente;
import com.leMakiti.group.leMakiti.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;

    private ArticleDto article;

    private Ventes vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if (ligneVente == null){
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .vente(ligneVente.getVente())
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto){
        if (ligneVenteDto == null){
            return null;
        }
        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setArticle(ArticleDto.toEntity(ligneVenteDto.getArticle()));
        ligneVente.setVente(ligneVenteDto.getVente());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());

        return ligneVente;
    }
}
