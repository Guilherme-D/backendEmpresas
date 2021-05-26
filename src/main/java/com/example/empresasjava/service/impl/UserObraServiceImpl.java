package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.Obra;
import com.example.empresasjava.models.User;
import com.example.empresasjava.models.UserObra;
import com.example.empresasjava.repository.ObraRepository;
import com.example.empresasjava.repository.UserObraRepository;
import com.example.empresasjava.service.ObraService;
import com.example.empresasjava.service.UserObraService;
import com.example.empresasjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserObraServiceImpl implements UserObraService {

    @Autowired
    private UserObraRepository userObraRepository;

    @Autowired
    private UserService userService;

    @Override
    public UserObra create(Obra obra) {
        User userByPrincipal = this.userService.getUserByPrincipal();
        UserObra user_obra = new UserObra(obra.getId(), userByPrincipal.getId());
        return this.userObraRepository.save(user_obra);

    }
}
