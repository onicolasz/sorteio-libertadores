package com.nicolasbarros.sorteiolibertadores.services;

import com.nicolasbarros.sorteiolibertadores.domains.Team;
import com.nicolasbarros.sorteiolibertadores.dtos.RequestTeamDTO;
import com.nicolasbarros.sorteiolibertadores.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    public Team getNotDrawTeam() {
        return teamRepository.findTeamNotDraw();
    }
    public Team create(RequestTeamDTO data) {
        Team newTeam = new Team(data);
        return teamRepository.save(newTeam);
    }

    public Team delete(long id) {
        if (!teamRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found with id: " + id);
        }

        Team team = teamRepository.getReferenceById(id);
        teamRepository.delete(team);

        return team;
    }
}
