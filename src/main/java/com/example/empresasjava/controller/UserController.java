package com.example.empresasjava.controller;

import com.example.empresasjava.models.RequestEntity.UserRequest;
import com.example.empresasjava.models.ResponseEntity.UserResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/create")
    @ApiOperation(value = "Criar novo usuário")
    public ResponseEntity<UserResponse> createUser(
            @ApiParam(value = "Json da requisição que contem o dado do usuario a ser salvo")
            @Valid @RequestBody UserRequest request){

        return ResponseEntity.ok().body(
                UserResponse.toResponse(this.userService.create(request))
        );
    }

    @PostMapping(path = "/edit")
    @ApiOperation(value = "Editar usuário existente")
    public ResponseEntity<UserResponse> editUser(
            @ApiParam(value = "Json da requisição que contem o dado a ser editado")
            @Valid @RequestBody UserRequest request){

        return ResponseEntity.ok().body(
                UserResponse.toResponse(this.userService.editUser(request))
        );
    }

    @DeleteMapping(path = "/delete/{email}")
    @ApiOperation(value = "Desativa usuário existente")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable(value="email") final String email){
        return ResponseEntity.ok().body(
                UserResponse.toResponse(this.userService.deleteUser(email))
        );
    }

    @GetMapping(path = "/page/{page}/size/{size}")
    @ResponseBody
    @ApiOperation(value = "Lista usuários por página quantidade")
    public Page<User> listUsersByPageWithSize(
            @ApiParam(value = "Página que deseja visualizar iniciando em 0", example = "0")
            @PathVariable(value="page")
            int page,
            @ApiParam(value = "Quantidade de usuários a serem listados por página", example = "10")
            @PathVariable(value="size")
            int size){

        Pageable pages = PageRequest.of(page, size);
        return this.userService.listUsersByPage(pages);

    }

}
