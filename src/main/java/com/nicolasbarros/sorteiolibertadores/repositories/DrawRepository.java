package com.nicolasbarros.sorteiolibertadores.repositories;


import com.nicolasbarros.sorteiolibertadores.domains.Draw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawRepository extends JpaRepository<Draw, Long> {
}