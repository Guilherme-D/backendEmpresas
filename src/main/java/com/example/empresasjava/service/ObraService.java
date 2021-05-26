package com.example.empresasjava.service;

import com.example.empresasjava.models.Obra;
import com.example.empresasjava.models.RequestEntity.ObraRequest;
import com.example.empresasjava.models.ResponseEntity.ObraResponse;
import com.example.empresasjava.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ObraService {

    @PreAuthorize("@authorityChecker.isAllowed(authentication)")
    Obra create(ObraRequest obra);

    Obra findObraById(Integer id);

    Obra editObra(Integer id, ObraRequest request);

    Obra deleteObra(Integer id);

    Page<ObraResponse> listObraByPage(Pageable pages);

    List<Obra> findAllbyUserId(Integer id);
}
