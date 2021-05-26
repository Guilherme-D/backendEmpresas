package com.example.empresasjava.service;

import com.example.empresasjava.models.Bonus;
import com.example.empresasjava.models.Equipe;
import com.example.empresasjava.models.RequestEntity.BonusRequest;
import com.example.empresasjava.models.RequestEntity.EquipeRequest;
import com.example.empresasjava.models.ResponseEntity.EquipeResponse;
import com.example.empresasjava.models.ResponseEntity.ObraResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface EquipeService {

    @PreAuthorize("@authorityChecker.isAllowed(authentication)")
    Equipe create(EquipeRequest equipeRequest);

    Equipe editEquipe(Integer id, EquipeRequest request);

    Equipe deleteEquipe(Integer id);

    Page<EquipeResponse> listEquipeByPage(Pageable pages);

    Equipe findEquipeById(Integer id);
}
