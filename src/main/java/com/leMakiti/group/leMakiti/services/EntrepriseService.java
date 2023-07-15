package com.leMakiti.group.leMakiti.services;

import com.leMakiti.group.leMakiti.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto entrepriseDto);

    EntrepriseDto findById(Integer id);

    List<EntrepriseDto> findAll();

    void delete(Integer id);
}
