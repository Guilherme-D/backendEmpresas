package com.example.empresasjava.repository;

import com.example.empresasjava.models.Bonus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonusRepository extends JpaRepository<Bonus,Integer> {

    Page<Bonus> findAllByObraIdInAndDeletedAtIsNullOrderByCreatedAt(List<Integer> allbyUserId, Pageable pages);
}
