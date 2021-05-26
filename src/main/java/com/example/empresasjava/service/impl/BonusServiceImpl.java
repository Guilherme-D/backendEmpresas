package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Bonus;
import com.example.empresasjava.models.Obra;
import com.example.empresasjava.models.RequestEntity.BonusRequest;
import com.example.empresasjava.models.ResponseEntity.BonusResponse;
import com.example.empresasjava.models.ResponseEntity.EquipeResponse;
import com.example.empresasjava.models.ResponseEntity.ObraResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.repository.BonusRepository;
import com.example.empresasjava.service.BonusService;
import com.example.empresasjava.service.ObraService;
import com.example.empresasjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BonusServiceImpl implements BonusService {

    @Autowired
    private BonusRepository bonusRepository;

    @Autowired
    private ObraService obraService;

    @Autowired
    private UserService userService;

    @Override
    public Bonus create(BonusRequest bonusRequest) {
        Obra obraById = this.obraService.findObraById(bonusRequest.getObra_id());
        Bonus bonus = BonusRequest.to_bonus(bonusRequest, obraById);
        return this.bonusRepository.save(bonus);
    }

    @Override
    public Bonus editBonus(Integer id, BonusRequest request) {
        Bonus bonusById = this.findBonusById(id);

        bonusById.setDias(request.getDias());
        bonusById.setValor(request.getValor());
        bonusById.setObra(this.obraService.findObraById(request.getObra_id()));

        return Optional.of(this.bonusRepository.save(bonusById)).orElseThrow(
                () -> new IllegalArgumentException("Não foi possível editar bonus, verifique os argumentos utilizados!")
        );
    }

    @Override
    public Bonus findBonusById(Integer id) {
        return this.bonusRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Nenhum bonus encontrado com o id informado!"));
    }

    @Override
    public Bonus deleteBonus(Integer id) {
        Bonus bonusById = this.findBonusById(id);

        bonusById.setDeletedAt(LocalDateTime.now());

        return Optional.of(this.bonusRepository.save(bonusById)).orElseThrow(
                () -> new IllegalArgumentException("Não foi possível deletar bonus, verifique os argumentos utilizados!")
        );
    }

    @Override
    public Page<BonusResponse> listBonusByPage(Pageable pages) {
        User userByPrincipal = this.userService.getUserByPrincipal();
        List<Integer> allbyUserId = this.obraService.findAllbyUserId(userByPrincipal.getId())
                .stream().map(Obra::getId).collect(Collectors.toList());
        return this.bonusRepository.findAllByObraIdInAndDeletedAtIsNullOrderByCreatedAt(allbyUserId, pages)
                .map(BonusResponse::to_response);
    }
}
