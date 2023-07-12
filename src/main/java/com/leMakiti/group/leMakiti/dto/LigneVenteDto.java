package com.leMakiti.group.leMakiti.dto;

import com.leMakiti.group.leMakiti.model.Ventes;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;

    private Ventes vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;
}
