package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Bonus;
import com.example.empresasjava.models.Equipe;
import com.example.empresasjava.models.Obra;
import com.example.empresasjava.models.RequestEntity.BonusRequest;
import com.example.empresasjava.models.RequestEntity.EquipeRequest;
import com.example.empresasjava.models.ResponseEntity.EquipeResponse;
import com.example.empresasjava.models.ResponseEntity.ObraResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.repository.BonusRepository;
import com.example.empresasjava.repository.EquipeRepository;
import com.example.empresasjava.service.EquipeService;
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
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private ObraService obraService;

    @Autowired
    private UserService userService;
    @Override
    public Equipe create(EquipeRequest equipeRequest) {
        Obra obraById = this.obraService.findObraById(equipeRequest.getObra_id());
        Equipe equipe = EquipeRequest.to_equipe(equipeRequest, obraById);
        return this.equipeRepository.save(equipe);
    }

    @Override
    public Equipe editEquipe(Integer id, EquipeRequest request) {
        Equipe equipeById = this.findEquipeById(id);
        equipeById.setQuantidade_funcionarios(request.getQuantidade_funcionarios());
        equipeById.setObra(this.obraService.findObraById(request.getObra_id()));
        return Optional.of(this.equipeRepository.save(equipeById)).orElseThrow(
                () -> new IllegalArgumentException("Não foi possível editar equipe, verifique os argumentos utilizados!")
        );
    }

    @Override
    public Equipe deleteEquipe(Integer id) {
        Equipe equipeById = this.findEquipeById(id);

        equipeById.setDeletedAt(LocalDateTime.now());

        return Optional.of(this.equipeRepository.save(equipeById)).orElseThrow(
                () -> new IllegalArgumentException("Não foi possível deletar equipe, verifique os argumentos utilizados!")
        );
    }

    @Override
    public Page<EquipeResponse> listEquipeByPage(Pageable pages) {
        User userByPrincipal = this.userService.getUserByPrincipal();
        List<Integer> allbyUserId = this.obraService.findAllbyUserId(userByPrincipal.getId())
                .stream().map(Obra::getId).collect(Collectors.toList());
        return this.equipeRepository.findAllByObraIdInAndDeletedAtIsNullOrderByCreatedAt(allbyUserId, pages)
                .map(EquipeResponse::to_response);
    }

    @Override
    public Equipe findEquipeById(Integer id) {
        return this.equipeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Nenhuma obra encontrada com o id informado!"));
    }
}
