package com.leMakiti.group.leMakiti.services.impl;

import com.leMakiti.group.leMakiti.dto.EntrepriseDto;
import com.leMakiti.group.leMakiti.exception.EntityNotFoundException;
import com.leMakiti.group.leMakiti.exception.ErrorCodes;
import com.leMakiti.group.leMakiti.exception.InvalidEntityException;
import com.leMakiti.group.leMakiti.repository.EntrepriseRepository;
import com.leMakiti.group.leMakiti.services.EntrepriseService;
import com.leMakiti.group.leMakiti.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if (!errors.isEmpty()){
            log.error("Client is not valid {}", entrepriseDto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        return EntrepriseDto.fromEntity(
                entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto))
        );
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null){
            log.error("Entreprise ID is null");
            return null;
        }

        return entrepriseRepository.findById(id)
        .map(EntrepriseDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
                "Aucune Entreprise avec l'ID = " + id + "n'a ete trouve dans la BDD",
                ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
    }


    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Entreprise ID is null");
            return ;
        }
        entrepriseRepository.deleteById(id);
    }
}
