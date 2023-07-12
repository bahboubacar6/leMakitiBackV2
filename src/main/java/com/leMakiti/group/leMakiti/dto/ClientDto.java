package com.leMakiti.group.leMakiti.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresseDto;

    private String photo;

    private String email;

    private String numTel;

    private List<CommandeClientDto> commandeClientDtos;
}
