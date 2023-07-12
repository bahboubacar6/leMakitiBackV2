package com.leMakiti.group.leMakiti.dto;

import com.leMakiti.group.leMakiti.model.Adresse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FournisseurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private Adresse adresse;

    private String photo;

    private String email;

    private String numTel;

    private List<CommandeFournisseurDto> commandeFournisseurDtos;
}
