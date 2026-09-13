package com.leilao.experience.repository;

import com.leilao.experience.entity.Lance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanceRepository extends JpaRepository<Lance,Long> {
}
