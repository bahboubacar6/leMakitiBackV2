package com.leMakiti.group.leMakiti.services.impl;

import com.leMakiti.group.leMakiti.dto.ClientDto;
import com.leMakiti.group.leMakiti.dto.CommandeClientDto;
import com.leMakiti.group.leMakiti.dto.LigneCommandeClientDto;
import com.leMakiti.group.leMakiti.exception.EntityNotFoundException;
import com.leMakiti.group.leMakiti.exception.ErrorCodes;
import com.leMakiti.group.leMakiti.exception.InvalidEntityException;
import com.leMakiti.group.leMakiti.model.Article;
import com.leMakiti.group.leMakiti.model.Client;
import com.leMakiti.group.leMakiti.model.CommandeClient;
import com.leMakiti.group.leMakiti.model.LigneCommandeClient;
import com.leMakiti.group.leMakiti.repository.ArticleRepository;
import com.leMakiti.group.leMakiti.repository.ClientRepository;
import com.leMakiti.group.leMakiti.repository.CommandeClientRepository;
import com.leMakiti.group.leMakiti.repository.LigneCommandeClientRepository;
import com.leMakiti.group.leMakiti.services.CommandeClientService;
import com.leMakiti.group.leMakiti.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    private LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     ClientRepository clientRepository,
                                     ArticleRepository articleRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CommandeClientValidator.validate(dto);

        if (!errors.isEmpty()){
            log.error("Commande Client n'est pas valide {}", dto);
            throw new InvalidEntityException("La Commande Client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client = clientRepository.findById(dto.getId());

        if (client.isEmpty()){
            log.warn("Client with ID {} was not found in the DB", dto.getClient().getId());
            throw new EntityNotFoundException("Aucun Client avec l'ID " +dto.getClient().getId() + "n'a ete trouve dans la BDD",ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (dto.getLigneCommandeClients() != null){
            dto.getLigneCommandeClients().forEach(ligCmtClt -> {
                if (ligCmtClt.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(ligCmtClt.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec l'ID " + ligCmtClt.getArticle().getId() + " n'existe pas dans la BDD");
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

        CommandeClient savedCmtClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

        if (dto.getLigneCommandeClients() != null){
            dto.getLigneCommandeClients().forEach(ligCmtClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmtClt);
                ligneCommandeClient.setCommandeClient(savedCmtClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(savedCmtClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null){
            log.error("Commande Client ID is null");
            return null;
        }

        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Commande Client avec l'ID = " + id + "n'a ete trouve dans la BDD",
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND)
                );
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (code == null){
            log.error("Commande Client CODE is null");
            return null;
        }

        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Commande Client avec le CODE = " + code + "n'a ete trouve dans la BDD",
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND)
                );
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Commande Client ID is null");
            return ;
        }
        commandeClientRepository.deleteById(id);
    }
}
