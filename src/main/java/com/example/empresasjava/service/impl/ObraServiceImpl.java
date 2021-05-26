package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Obra;
import com.example.empresasjava.models.RequestEntity.ObraRequest;
import com.example.empresasjava.models.ResponseEntity.ObraResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserObra;
import com.example.empresasjava.repository.ObraRepository;
import com.example.empresasjava.service.ObraService;
import com.example.empresasjava.service.UserObraService;
import com.example.empresasjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ObraServiceImpl implements ObraService {

    @Autowired
    private ObraRepository obraRepository;

    @Autowired
    private UserService userService;

    @Override
    public Obra create(ObraRequest obraRequest) {
        User userByPrincipal = this.userService.getUserByPrincipal();
        Obra obra = ObraRequest.to_Obra(obraRequest, userByPrincipal);
        return Optional.of(this.obraRepository.save(obra)).orElseThrow(
                () -> new IllegalArgumentException("Não foi possível salvar obra, verifique os argumentos utilizados!")
        );
    }

    @Override
    public Obra findObraById(Integer id) {
        return this.obraRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Nenhuma obra encontrada com o id informado!"));
    }

    @Override
    public Obra editObra(Integer id, ObraRequest request) {
        Obra obra = this.findObraById(id);

        obra.setPrazo(request.getPrazo());
        obra.setBonus_entrega(request.getBonus_entrega());
        obra.setValor_total(request.getValor_total());
        obra.setTempo_limite(request.getTempo_limite());

        return Optional.of(this.obraRepository.save(obra)).orElseThrow(
                () -> new IllegalArgumentException("Não foi possível editar obra, verifique os argumentos utilizados!")
        );
    }

    @Override
    public Obra deleteObra(Integer id) {
        Obra obraById = this.findObraById(id);

        obraById.setDeletedAt(LocalDateTime.now());

        return Optional.of(this.obraRepository.save(obraById)).orElseThrow(
                () -> new IllegalArgumentException("Não foi possível deletar obra, verifique os argumentos utilizados!")
        );
    }

    @Override
    public Page<ObraResponse> listObraByPage(Pageable pages) {
        User userByPrincipal = this.userService.getUserByPrincipal();
        return this.obraRepository.findAllByUserIdAndDeletedAtIsNullOrderByCreatedAt(userByPrincipal.getId(), pages)
                .map(ObraResponse::to_response);

    }

    @Override
    public List<Obra> findAllbyUserId(Integer id) {
        return this.obraRepository.findAllByUserIdAndDeletedAtIsNullOrderByCreatedAt(id);
    }
}
