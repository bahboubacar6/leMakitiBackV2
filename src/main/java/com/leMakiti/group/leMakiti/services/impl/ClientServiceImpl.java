package com.leMakiti.group.leMakiti.services.impl;

import com.leMakiti.group.leMakiti.dto.ClientDto;
import com.leMakiti.group.leMakiti.exception.EntityNotFoundException;
import com.leMakiti.group.leMakiti.exception.ErrorCodes;
import com.leMakiti.group.leMakiti.exception.InvalidEntityException;
import com.leMakiti.group.leMakiti.repository.ClientRepository;
import com.leMakiti.group.leMakiti.services.ClientService;
import com.leMakiti.group.leMakiti.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validate(clientDto);
        if (!errors.isEmpty()){
            log.error("Client is not valid {}", clientDto);
            throw new InvalidEntityException("Le Client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ClientDto.fromEntity(
                clientRepository.save(ClientDto.toEntity(clientDto))
        );
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id == null){
            log.error("Client ID is null");
            return null;
        }

        return clientRepository.findById(id)
        .map(ClientDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
                "Aucun Client avec l'ID = " + id + "n'a ete trouve dans la BDD",
                ErrorCodes.CLIENT_NOT_FOUND)
        );
    }


    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Client ID is null");
            return ;
        }
        clientRepository.deleteById(id);
    }
}
