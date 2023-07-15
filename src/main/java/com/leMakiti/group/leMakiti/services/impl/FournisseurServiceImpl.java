package com.leMakiti.group.leMakiti.services.impl;

import com.leMakiti.group.leMakiti.dto.FournisseurDto;
import com.leMakiti.group.leMakiti.exception.EntityNotFoundException;
import com.leMakiti.group.leMakiti.exception.ErrorCodes;
import com.leMakiti.group.leMakiti.exception.InvalidEntityException;
import com.leMakiti.group.leMakiti.repository.FournisseurRepository;
import com.leMakiti.group.leMakiti.services.FournisseurService;
import com.leMakiti.group.leMakiti.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String> errors = FournisseurValidator.validate(fournisseurDto);
        if (!errors.isEmpty()){
            log.error("Fournisseur is not valid {}", fournisseurDto);
            throw new InvalidEntityException("Le Fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        return FournisseurDto.fromEntity(
                fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto))
        );
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id == null){
            log.error("Fournisseur ID is null");
            return null;
        }

        return fournisseurRepository.findById(id)
        .map(FournisseurDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
                "Aucun Fournisseur avec l'ID = " + id + "n'a ete trouve dans la BDD",
                ErrorCodes.FOURNISSEUR_NOT_FOUND)
        );
    }


    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Fournisseur ID is null");
            return ;
        }
        fournisseurRepository.deleteById(id);
    }
}
