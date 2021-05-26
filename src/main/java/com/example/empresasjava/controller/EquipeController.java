package com.example.empresasjava.controller;

import com.example.empresasjava.models.Equipe;
import com.example.empresasjava.models.RequestEntity.BonusRequest;
import com.example.empresasjava.models.RequestEntity.EquipeRequest;
import com.example.empresasjava.models.RequestEntity.ObraRequest;
import com.example.empresasjava.models.ResponseEntity.BonusResponse;
import com.example.empresasjava.models.ResponseEntity.EquipeResponse;
import com.example.empresasjava.models.ResponseEntity.ObraResponse;
import com.example.empresasjava.service.BonusService;
import com.example.empresasjava.service.EquipeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping("/equipe")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @PostMapping(path = "/create")
    @ApiOperation(value = "Criar nova equipe")
    public ResponseEntity<EquipeResponse> createEquipe(
            @ApiParam(value = "Json da requisição que contem o dado da equipe a ser salvo")
            @Valid @RequestBody EquipeRequest request){

        return ResponseEntity.ok().body(
                EquipeResponse.to_response(this.equipeService.create(request))
        );
    }

    @PostMapping(path = "/edit/{id}")
    @ApiOperation(value = "Editar equipe existente")
    public ResponseEntity<EquipeResponse> editObra(
            @PathVariable(value="id") final Integer id,
            @ApiParam(value = "Json da requisição que contem o dado a ser editado")
            @Valid @RequestBody EquipeRequest request){

        return ResponseEntity.ok().body(
                EquipeResponse.to_response(this.equipeService.editEquipe(id, request))
        );
    }

    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation(value = "Desativa equipe existente")
    public ResponseEntity<EquipeResponse> equipeObra(@PathVariable(value="id") final Integer id){
        return ResponseEntity.ok().body(
                EquipeResponse.to_response(this.equipeService.deleteEquipe(id))
        );
    }

    @GetMapping(path = "/page/{page}/size/{size}")
    @ResponseBody
    @ApiOperation(value = "Lista equipes criada por usuario por página, definindo quantidade")
    public Page<EquipeResponse> listObraByPageWithSize(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
                    int page,
            @ApiParam(value = "Quantidade de equipes a serem listados por página", example = "10")
            @PathVariable(value="size")
                    int size){

        Pageable pages = PageRequest.of(page, size);
        return this.equipeService.listEquipeByPage(pages);

    }
}
