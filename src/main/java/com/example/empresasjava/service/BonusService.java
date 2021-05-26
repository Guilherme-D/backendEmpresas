package com.example.empresasjava.service;

import com.example.empresasjava.models.Bonus;
import com.example.empresasjava.models.RequestEntity.BonusRequest;
import com.example.empresasjava.models.ResponseEntity.BonusResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface BonusService {

    @PreAuthorize("@authorityChecker.isAllowed(authentication)")
    Bonus create(BonusRequest bonusRequest);

    Bonus editBonus(Integer id, BonusRequest request);

    Bonus findBonusById(Integer id);

    Bonus deleteBonus(Integer id);

    Page<BonusResponse> listBonusByPage(Pageable pages);
}
