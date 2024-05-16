package com.nicolasbarros.sorteiolibertadores.services;

import com.nicolasbarros.sorteiolibertadores.domains.Draw;
import com.nicolasbarros.sorteiolibertadores.domains.DrawGroup;
import com.nicolasbarros.sorteiolibertadores.domains.Team;
import com.nicolasbarros.sorteiolibertadores.dtos.DrawTeamDTO;
import com.nicolasbarros.sorteiolibertadores.exceptions.ResourceNotFoundException;
import com.nicolasbarros.sorteiolibertadores.repositories.DrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class DrawService {
    @Autowired
    private DrawRepository drawRepository;
    @Autowired
    private TeamService teamService;
    @Autowired
    private DrawGroupService drawGroupService;


    public Draw getById(Long id) {
        return drawRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sorteio com id: " + id + " não encontrado"));
    }

    public Draw drawAll(Long draw_id) {
        Draw drawExist = getById(draw_id);

        List<Team> teams = teamService.getAll();

        // Separar times por potes
        Map<Integer, List<Team>> teamsByPot = teams.stream().collect(Collectors.groupingBy(team -> Math.toIntExact(team.getPot().getPot_id())));

        CompletableFuture<Void> future = CompletableFuture.completedFuture(null);

        for (int potId = 1; potId <= 4; potId++) {
            List<Team> teamsInPot = teamsByPot.getOrDefault(potId, Collections.emptyList());
            Collections.shuffle(teamsInPot);

            for (Team team : teamsInPot) {
                future = future.thenCompose(v -> CompletableFuture.runAsync(() -> {
                        DrawTeamDTO data = new DrawTeamDTO(drawExist.getDraw_id(), team.getTeam_id());
                        drawGroupService.drawTeam(data);
                }));
            }
        }

        future.join();

        return getById(draw_id);
    }
}
