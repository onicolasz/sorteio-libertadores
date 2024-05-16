package com.nicolasbarros.sorteiolibertadores.repositories;

import com.nicolasbarros.sorteiolibertadores.domains.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(value = "SELECT * FROM Team t WHERE t.draw_group_id IS NULL AND t.pot_id = (SELECT MIN(pot_id) FROM Team WHERE draw_group_id IS NULL) ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Team> findTeamNotDraw();

    @Query(value = "SELECT COUNT(*) FROM Team t WHERE t.draw_group_id IS NULL", nativeQuery = true)
    int countAllNotDrawned();
}