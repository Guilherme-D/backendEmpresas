package com.example.empresasjava.service;

import com.example.empresasjava.models.RequestEntity.UserRequest;
import com.example.empresasjava.models.ResponseEntity.UserResponse;
import com.example.empresasjava.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

public interface UserService {
    User create(UserRequest user);
    Optional<User> findByEmail(String email);
    UserResponse save(User user);
    User editUser(UserRequest userRequest);
    User deleteUser(String email);
    User getUserByPrincipal();

    @PreAuthorize("@authorityChecker.isAllowed(authentication)")
    Page<User> listUsersByPage(Pageable page);
}
