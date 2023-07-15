package com.leMakiti.group.leMakiti.services.impl;

import com.leMakiti.group.leMakiti.dto.CommandeClientDto;
import com.leMakiti.group.leMakiti.dto.CommandeFournisseurDto;
import com.leMakiti.group.leMakiti.dto.LigneCommandeClientDto;
import com.leMakiti.group.leMakiti.dto.LigneCommandeFournisseurDto;
import com.leMakiti.group.leMakiti.exception.EntityNotFoundException;
import com.leMakiti.group.leMakiti.exception.ErrorCodes;
import com.leMakiti.group.leMakiti.exception.InvalidEntityException;
import com.leMakiti.group.leMakiti.model.*;
import com.leMakiti.group.leMakiti.repository.*;
import com.leMakiti.group.leMakiti.services.CommandeFournisseurService;
import com.leMakiti.group.leMakiti.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;

    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
                                          FournisseurRepository fournisseurRepository,
                                          ArticleRepository articleRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);

        if (!errors.isEmpty()){
            log.error("Commande Fournbisseur n'est pas valide {}", dto);
            throw new InvalidEntityException("La Commande Fourisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND, errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getId());

        if (fournisseur.isEmpty()){
            log.warn("Fournisseur with ID {} was not found in the DB", dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun Fournisseur avec l'ID " +dto.getFournisseur().getId() + "n'a ete trouve dans la BDD",ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (dto.getLigneCommandeFournisseurs() != null){
            dto.getLigneCommandeFournisseurs().forEach(ligCmtfou -> {
                if (ligCmtfou.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(ligCmtfou.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec l'ID " + ligCmtfou.getArticle().getId() + " n'existe pas dans la BDD");
                    }
                } else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article null");
                }
            });
        }

        if (!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("L'article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeFournisseur savedCmtfou = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

        if (dto.getLigneCommandeFournisseurs() != null){
            dto.getLigneCommandeFournisseurs().forEach(ligCmtfou -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmtfou);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmtfou);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }

        return CommandeFournisseurDto.fromEntity(savedCmtfou);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id == null){
            log.error("Commande Client ID is null");
            return null;
        }

        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Commande Fournisseur avec l'ID = " + id + "n'a ete trouve dans la BDD",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND)
                );
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (code == null){
            log.error("Commande Fournisseur CODE is null");
            return null;
        }

        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Commande Fournisseur avec le CODE = " + code + "n'a ete trouve dans la BDD",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND)
                );
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Commande Fournisseur ID is null");
            return ;
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
