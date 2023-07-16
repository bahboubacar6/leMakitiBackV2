package com.leMakiti.group.leMakiti.controller;

import com.leMakiti.group.leMakiti.controller.api.CommandeClientApi;
import com.leMakiti.group.leMakiti.dto.CommandeClientDto;
import com.leMakiti.group.leMakiti.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(
            CommandeClientService commandeClientService
    ) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        return commandeClientService.save(dto);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        return commandeClientService.findById(id);
    }

    @Override
    public CommandeClientDto findByCodeCmdClient(String codeArticle) {
        return commandeClientService.findByCode(codeArticle);
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeClientService.delete(id);
    }
}
