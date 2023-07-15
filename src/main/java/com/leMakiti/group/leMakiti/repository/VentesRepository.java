package com.leMakiti.group.leMakiti.repository;

import com.leMakiti.group.leMakiti.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

    Optional<Ventes> findVentesByCode(String code);
}
