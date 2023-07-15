package com.leMakiti.group.leMakiti.services.impl;

import com.leMakiti.group.leMakiti.dto.MvtStkDto;
import com.leMakiti.group.leMakiti.exception.EntityNotFoundException;
import com.leMakiti.group.leMakiti.exception.ErrorCodes;
import com.leMakiti.group.leMakiti.exception.InvalidEntityException;
import com.leMakiti.group.leMakiti.repository.MvtStkRepository;
import com.leMakiti.group.leMakiti.services.MvtStkService;
import com.leMakiti.group.leMakiti.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {

    private MvtStkRepository mvtStkRepository;

    @Autowired
    public MvtStkServiceImpl(MvtStkRepository mvtStkRepository) {
        this.mvtStkRepository = mvtStkRepository;
    }


    @Override
    public MvtStkDto save(MvtStkDto dto) {
        List<String> errors = MvtStkValidator.validate(dto);
        if (!errors.isEmpty()){
            log.error("Client is not valid {}", dto);
            throw new InvalidEntityException("Le Client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(dto))
        );
    }

    @Override
    public MvtStkDto findById(Integer id) {
        if (id == null){
            log.error("Mouvement de stock ID is null");
            return null;
        }

        return mvtStkRepository.findById(id)
        .map(MvtStkDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
                "Aucun Mouvement de stock avec l'ID = " + id + "n'a ete trouve dans la BDD",
                ErrorCodes.MVT_STK_NOT_FOUND)
        );
    }


    @Override
    public List<MvtStkDto> findAll() {
        return mvtStkRepository.findAll().stream()
                .map(MvtStkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("MvtStk ID is null");
            return ;
        }
        mvtStkRepository.deleteById(id);
    }
}
