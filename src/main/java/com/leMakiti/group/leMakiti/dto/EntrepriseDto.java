package com.leMakiti.group.leMakiti.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leMakiti.group.leMakiti.model.Adresse;
import com.leMakiti.group.leMakiti.model.Entreprise;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EntrepriseDto {

    private Integer id;

    private String nom;

    private String description;

    private AdresseDto adresse;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numTel;

    private String siteWeb;

    private Integer idEntreprise;

    @JsonIgnore
    private List<UtilisateurDto> utilisateurDtos;

    public static EntrepriseDto fromEntity(Entreprise entreprise){
        if (entreprise == null){
            return null;
        }

        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .adresse(AdresseDto.fromEntity(entreprise.getAdresse()))
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())
                .email(entreprise.getEmail())
                .numTel(entreprise.getNumTel())
                .siteWeb(entreprise.getSiteWeb())
                .idEntreprise(entreprise.getIdEntreprise())
                .build();
    }

    public static Entreprise toEntity(EntrepriseDto entrepriseDto){
        if (entrepriseDto == null){
            return null;
        }

        Entreprise entreprise = new Entreprise();

        entreprise.setId(entrepriseDto.getId());
        entreprise.setNom(entrepriseDto.getNom());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setAdresse(AdresseDto.toEntity(entrepriseDto.getAdresse()));
        entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setNumTel(entrepriseDto.getNumTel());
        entreprise.setSiteWeb(entrepriseDto.getSiteWeb());
        entreprise.setIdEntreprise(entrepriseDto.getIdEntreprise());

        return entreprise;
    }
}
