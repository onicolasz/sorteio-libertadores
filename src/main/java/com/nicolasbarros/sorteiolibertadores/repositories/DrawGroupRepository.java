package com.nicolasbarros.sorteiolibertadores.repositories;

import com.nicolasbarros.sorteiolibertadores.domains.DrawGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrawGroupRepository extends JpaRepository<DrawGroup, Long> {
    @Query("SELECT dg FROM DrawGroup dg WHERE dg.draw.draw_id = :drawId")
    List<DrawGroup> findAllByDrawId(Long drawId);
}