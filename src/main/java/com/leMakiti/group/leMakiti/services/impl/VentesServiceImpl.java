package com.leMakiti.group.leMakiti.services.impl;

import com.leMakiti.group.leMakiti.dto.LigneVenteDto;
import com.leMakiti.group.leMakiti.dto.VentesDto;
import com.leMakiti.group.leMakiti.exception.EntityNotFoundException;
import com.leMakiti.group.leMakiti.exception.ErrorCodes;
import com.leMakiti.group.leMakiti.exception.InvalidEntityException;
import com.leMakiti.group.leMakiti.model.*;
import com.leMakiti.group.leMakiti.repository.*;
import com.leMakiti.group.leMakiti.services.VentesService;
import com.leMakiti.group.leMakiti.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private VentesRepository ventesRepository;
    private ArticleRepository articleRepository;

    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VentesServiceImpl(VentesRepository ventesRepository,
                             ArticleRepository articleRepository, LigneVenteRepository ligneVenteRepository) {
        this.ventesRepository = ventesRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors = VentesValidator.validate(dto);

        if (!errors.isEmpty()){
            log.error("Ventes n'est pas valide {}", dto);
            throw new InvalidEntityException("La Vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

            dto.getLigneVentes().forEach(ligneVenteDto -> {
                Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec l'ID " + ligneVenteDto.getArticle().getId() + " n'existe pas dans la BDD");
                    }
            });


        if (!articleErrors.isEmpty()){
            log.error("One or more article were not found in the DB");
            throw new InvalidEntityException("Un ou plusieur articles n'ont pas ete trouve dans la BDD", ErrorCodes.VENTE_NOT_FOUND, articleErrors);
        }

        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));

        dto.getLigneVentes().forEach(ligneVenteDto -> {
                LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
                ligneVente.setVente(savedVentes);
                ligneVenteRepository.save(ligneVente);
            });

        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) {
        if (id == null){
            log.error("Vente ID is null");
            return null;
        }

        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Vente avec l'ID = " + id + "n'a ete trouve dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND)
                );
    }

    @Override
    public VentesDto findByCode(String code) {
        if (code == null){
            log.error("Commande Client CODE is null");
            return null;
        }

        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Vente avec le CODE = " + code + "n'a ete trouve dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND)
                );
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Vente ID is null");
            return ;
        }
        ventesRepository.deleteById(id);
    }
}
