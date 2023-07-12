package com.leMakiti.group.leMakiti.dto;

import com.leMakiti.group.leMakiti.model.Fournisseur;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private Fournisseur fournisseur;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurDtos;
}
