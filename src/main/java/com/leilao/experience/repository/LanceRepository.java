package com.leilao.experience.repository;

import com.leilao.experience.entity.Lance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanceRepository extends JpaRepository<Lance,Long> {
    List<Lance> findByUsuarioId(Long id);
}
