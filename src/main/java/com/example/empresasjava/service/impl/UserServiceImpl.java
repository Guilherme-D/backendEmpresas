package com.example.empresasjava.service.impl;

import com.example.empresasjava.models.RequestEntity.UserRequest;
import com.example.empresasjava.models.ResponseEntity.UserResponse;
import com.example.empresasjava.models.User;
import com.example.empresasjava.repository.UserRepository;
import com.example.empresasjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public User create(UserRequest user) throws NonUniqueResultException{

        Optional<User> usr = Optional.ofNullable(this.userRepository.findOneByEmail(user.getEmail()));

        if(!usr.isPresent()){
            user.setPassword(this.bcryptEncoder.encode(user.getPassword()));
            return this.userRepository.save(UserRequest.toUser(user));
        }else{
            throw new NonUniqueResultException("Email ja cadastrado!");
        }

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(this.userRepository.findOneByEmail(email));
    }

    @Override
    public UserResponse save(User user) {
        user.setPassword(this.bcryptEncoder.encode(user.getPassword()));
        return UserResponse.toResponse(this.userRepository.save(user));
    }

    @Override
    public User editUser(UserRequest userRequest) {
        User user = this.userRepository.findOneByEmail(userRequest.getEmail());

        //usuarios nao administradores nao podem editar campo is_admin
        //usuarios nao administradores nao podem editar outros usuarios
        User userByPrincipal = this.getUserByPrincipal();
        if(userByPrincipal.getIsAdmin()) {
            user.setIsAdmin(userRequest.getIs_admin());
            user.setName(userRequest.getName());
            user.setPassword(this.bcryptEncoder.encode(user.getPassword()));
        }
        if(user == userByPrincipal) {
            user.setName(userRequest.getName());
            user.setPassword(this.bcryptEncoder.encode(user.getPassword()));
        }else {
            throw new BadCredentialsException("Usuários não administradores não podem alterar outros usuários!");
        }
        return this.userRepository.save(user);
    }

    @Override
    public User deleteUser(String email) {
        User user = this.userRepository.findOneByEmail(email);

        user.setDeletedAt(LocalDateTime.now());
        return this.userRepository.save(user);
    }

    @Override
    public User getUserByPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.userRepository.findOneByEmail(((UserDetails)principal).getUsername());

    }


    @Override
    public Page<User> listUsersByPage(Pageable page) {
        return this.userRepository.findAllByIsAdminAndDeletedAtIsNullOrderByName(false, page);
    }

}
