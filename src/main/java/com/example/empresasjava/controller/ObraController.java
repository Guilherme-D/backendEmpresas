package com.example.empresasjava.controller;

import com.example.empresasjava.models.RequestEntity.ObraRequest;
import com.example.empresasjava.models.ResponseEntity.ObraResponse;
import com.example.empresasjava.service.ObraService;
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

@RestController
@CrossOrigin
@RequestMapping("/obra")
public class ObraController {

    @Autowired
    private ObraService obraService;

    @PostMapping(path = "/create")
    @ApiOperation(value = "Criar nova obra")
    public ResponseEntity<ObraResponse> createObra(
            @ApiParam(value = "Json da requisição que contem o dado da obra a ser salvo")
            @Valid @RequestBody ObraRequest request){

        return ResponseEntity.ok().body(
                ObraResponse.to_response(this.obraService.create(request))
        );
    }

    @PostMapping(path = "/edit/{id}")
    @ApiOperation(value = "Editar obra existente")
    public ResponseEntity<ObraResponse> editObra(
            @PathVariable(value="id") final Integer id,
            @ApiParam(value = "Json da requisição que contem o dado a ser editado")
            @Valid @RequestBody ObraRequest request){

        return ResponseEntity.ok().body(
                ObraResponse.to_response(this.obraService.editObra(id, request))
        );
    }

    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation(value = "Desativa obra existente")
    public ResponseEntity<ObraResponse> deleteObra(@PathVariable(value="id") final Integer id){
        return ResponseEntity.ok().body(
                ObraResponse.to_response(this.obraService.deleteObra(id))
        );
    }

    @GetMapping(path = "/page/{page}/size/{size}")
    @ResponseBody
    @ApiOperation(value = "Lista obras de usuario por página, definindo quantidade")
    public Page<ObraResponse> listObraByPageWithSize(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
                    int page,
            @ApiParam(value = "Quantidade de obras a serem listados por página", example = "10")
            @PathVariable(value="size")
                    int size){

        Pageable pages = PageRequest.of(page, size);
        return this.obraService.listObraByPage(pages);

    }
}
