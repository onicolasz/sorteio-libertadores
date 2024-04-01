package com.nicolasbarros.sorteiolibertadores.repositories;

import com.nicolasbarros.sorteiolibertadores.domains.Pot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PotRepository extends JpaRepository<Pot, Long> {
}