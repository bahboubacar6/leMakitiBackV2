package com.leMakiti.group.leMakiti.services;

import com.leMakiti.group.leMakiti.dto.MvtStkDto;

import java.util.List;

public interface MvtStkService {

    MvtStkDto save(MvtStkDto dto);

    MvtStkDto findById(Integer id);

    List<MvtStkDto> findAll();

    void delete(Integer id);
}
