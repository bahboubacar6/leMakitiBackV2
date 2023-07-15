package com.leMakiti.group.leMakiti.services;

import com.leMakiti.group.leMakiti.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    FournisseurDto findById(Integer id);

    List<FournisseurDto> findAll();

    void delete(Integer id);
}
