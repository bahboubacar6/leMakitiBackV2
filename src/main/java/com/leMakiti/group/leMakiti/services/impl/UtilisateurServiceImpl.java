package com.leMakiti.group.leMakiti.services.impl;

import com.leMakiti.group.leMakiti.dto.UtilisateurDto;
import com.leMakiti.group.leMakiti.exception.EntityNotFoundException;
import com.leMakiti.group.leMakiti.exception.ErrorCodes;
import com.leMakiti.group.leMakiti.exception.InvalidEntityException;
import com.leMakiti.group.leMakiti.repository.UtilisateurRepository;
import com.leMakiti.group.leMakiti.services.UtilisateurService;
import com.leMakiti.group.leMakiti.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        List<String> errors = UtilisateurValidator.validate(utilisateurDto);
        if (!errors.isEmpty()){
            log.error("Utilisateur is not valid {}", utilisateurDto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto))
        );
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null){
            log.error("Utilisateur ID is null");
            return null;
        }

        return utilisateurRepository.findById(id)
        .map(UtilisateurDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
                "Aucun Utilisateur avec l'ID = " + id + "n'a ete trouve dans la BDD",
                ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
    }


    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Utilisateur ID is null");
            return ;
        }
        utilisateurRepository.deleteById(id);
    }
}
