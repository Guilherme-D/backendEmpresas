package com.example.empresasjava.service;

import com.example.empresasjava.models.Obra;
import com.example.empresasjava.models.UserObra;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UserObraService {

    @PreAuthorize("@authorityChecker.isAllowed(authentication)")
    UserObra create(Obra obra);

}
