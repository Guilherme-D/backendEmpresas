package com.example.empresasjava.repository;

import com.example.empresasjava.models.Equipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe,Integer> {

    Page<Equipe> findAllByObraIdInAndDeletedAtIsNullOrderByCreatedAt(List<Integer> id, Pageable pages);
}
