package com.nicolasbarros.sorteiolibertadores.services;

import com.nicolasbarros.sorteiolibertadores.domains.DrawGroup;
import com.nicolasbarros.sorteiolibertadores.repositories.DrawGroupRepository;
import com.nicolasbarros.sorteiolibertadores.repositories.DrawRepository;
import com.nicolasbarros.sorteiolibertadores.domains.Team;
import com.nicolasbarros.sorteiolibertadores.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

import java.util.List;

@Service
public class DrawGroupService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private DrawRepository drawRepository;
    @Autowired
    private DrawGroupRepository drawGroupRepository;

    public DrawGroup setTeamGroup(SetTeamGroupDTO data) {
        Team drawingTeam = teamRepository.findById(data.team_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team with id: " + data.team_id() + " not found"));
        drawRepository.findById(data.draw_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Draw with id: " + data.draw_id() + " not found"));

        if (drawingTeam.getPot() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team with id: " + data.draw_id() + " dont have pot");
        }

        List<DrawGroup> drawGroups = drawGroupRepository.findAllByDrawId(data.draw_id());

        Optional<DrawGroup> selectedDrawGroupOptional = drawGroups.stream()
                .filter(drawGroup -> drawGroup.getTeams().stream()
                        .noneMatch(teamInGroup ->
                                (teamInGroup.getPot().getPot_id() == drawingTeam.getPot().getPot_id() && drawingTeam.getPot().getPot_id() != 4) ||
                                        (!teamInGroup.equals(drawingTeam) && teamInGroup.getCountry().equals(drawingTeam.getCountry()))
                        )
                )
                .findFirst();

        selectedDrawGroupOptional.ifPresent(drawGroup -> {
            drawingTeam.setDrawGroup(drawGroup);
            teamRepository.save(drawingTeam);
        });

        return selectedDrawGroupOptional.orElse(null);
    }
}
