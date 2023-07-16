package com.leMakiti.group.leMakiti.controller;

import com.leMakiti.group.leMakiti.controller.api.UtilisateurApi;
import com.leMakiti.group.leMakiti.dto.ClientDto;
import com.leMakiti.group.leMakiti.dto.UtilisateurDto;
import com.leMakiti.group.leMakiti.services.ClientService;
import com.leMakiti.group.leMakiti.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(
            UtilisateurService utilisateurService
    ) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}
