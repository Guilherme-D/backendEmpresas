package com.example.empresasjava.controller;

import com.example.empresasjava.models.RequestEntity.BonusRequest;
import com.example.empresasjava.models.ResponseEntity.BonusResponse;
import com.example.empresasjava.service.BonusService;
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
@RequestMapping("/bonus")
public class BonusController {

    @Autowired
    private BonusService bonusService;

    @PostMapping(path = "/create")
    @ApiOperation(value = "Criar novo bonus")
    public ResponseEntity<BonusResponse> createBonus(
            @ApiParam(value = "Json da requisição que contem o dado do bonus a ser salvo")
            @Valid @RequestBody BonusRequest request){

        return ResponseEntity.ok().body(
                BonusResponse.to_response(this.bonusService.create(request))
        );
    }


    @PostMapping(path = "/edit/{id}")
    @ApiOperation(value = "Editar bonus existente")
    public ResponseEntity<BonusResponse> editBonus(
            @PathVariable(value="id") final Integer id,
            @ApiParam(value = "Json da requisição que contem o dado a ser editado")
            @Valid @RequestBody BonusRequest request){

        return ResponseEntity.ok().body(
                BonusResponse.to_response(this.bonusService.editBonus(id, request))
        );
    }

    @DeleteMapping(path = "/delete/{id}")
    @ApiOperation(value = "Desativa bonus existente")
    public ResponseEntity<BonusResponse> deleteBonus(@PathVariable(value="id") final Integer id){
        return ResponseEntity.ok().body(
                BonusResponse.to_response(this.bonusService.deleteBonus(id))
        );
    }

    @GetMapping(path = "/page/{page}/size/{size}")
    @ResponseBody
    @ApiOperation(value = "Lista bonus criado por usuario por página, definindo quantidade")
    public Page<BonusResponse> listBonusByPageWithSize(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
                    int page,
            @ApiParam(value = "Quantidade de equipes a serem listados por página", example = "10")
            @PathVariable(value="size")
                    int size){

        Pageable pages = PageRequest.of(page, size);
        return this.bonusService.listBonusByPage(pages);

    }

}
