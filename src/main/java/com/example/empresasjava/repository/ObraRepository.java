package com.example.empresasjava.repository;

import com.example.empresasjava.models.Obra;
import com.example.empresasjava.models.ResponseEntity.ObraResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraRepository extends JpaRepository<Obra,Integer> {
    Page<Obra> findAllByUserIdAndDeletedAtIsNullOrderByCreatedAt(Integer id, Pageable pageable);

    List<Obra> findAllByUserIdAndDeletedAtIsNullOrderByCreatedAt(Integer id);
}
