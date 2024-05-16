package com.nicolasbarros.sorteiolibertadores.services;

import com.nicolasbarros.sorteiolibertadores.domains.DrawGroup;
import com.nicolasbarros.sorteiolibertadores.dtos.DrawTeamDTO;
import com.nicolasbarros.sorteiolibertadores.exceptions.TeamNotDrawnNotFoundException;
import com.nicolasbarros.sorteiolibertadores.repositories.DrawGroupRepository;
import com.nicolasbarros.sorteiolibertadores.domains.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

import java.util.List;

@Service
public class DrawGroupService {
    @Autowired
    private DrawGroupRepository drawGroupRepository;
    @Autowired
    private TeamService teamService;

    public DrawGroup drawTeam(DrawTeamDTO data) {
        Optional<Team> getTeam = teamService.getById(data.team_id());

        Team drawingTeam = getTeam.orElseGet(() -> {
            return teamService.getNotDrawTeam().orElseThrow(TeamNotDrawnNotFoundException::new);
        });

        List<DrawGroup> drawGroups = drawGroupRepository.findAllByDrawId(data.draw_id());

        Optional<DrawGroup> selectedDrawGroupOptional = drawGroups.stream()
                .filter(drawGroup -> drawGroup.getTeams().size() < 4 &&
                        drawGroup.getTeams().stream()
                        .noneMatch(teamInGroup ->
                                (Objects.equals(teamInGroup.getPot().getPot_id(), drawingTeam.getPot().getPot_id()) && drawingTeam.getPot().getPot_id() != 4) ||
                                        (!teamInGroup.equals(drawingTeam) && teamInGroup.getCountry().equals(drawingTeam.getCountry()))
                        )
                )
                .findFirst();

        selectedDrawGroupOptional.ifPresent(drawGroup -> {
            drawingTeam.setDrawGroup(drawGroup);
            teamService.update(drawingTeam);
        });

        return selectedDrawGroupOptional.orElse(null);
    }
}
