package com.leMakiti.group.leMakiti.controller;

import com.leMakiti.group.leMakiti.controller.api.CommandeFourisseurApi;
import com.leMakiti.group.leMakiti.dto.CommandeFournisseurDto;
import com.leMakiti.group.leMakiti.services.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFourisseurApi {

    private CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(
            CommandeFournisseurService commandeFournisseurService
    ) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        return commandeFournisseurService.save(dto);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        return commandeFournisseurService.findById(id);
    }

    @Override
    public CommandeFournisseurDto findByCodeCmdFournisseur(String codeArticle) {
        return commandeFournisseurService.findByCode(codeArticle);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeFournisseurService.delete(id);
    }
}
