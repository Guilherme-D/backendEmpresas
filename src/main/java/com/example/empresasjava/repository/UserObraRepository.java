package com.example.empresasjava.repository;

import com.example.empresasjava.models.UserObra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserObraRepository extends JpaRepository<UserObra,Integer> {

}
