package com.nicolasbarros.sorteiolibertadores.services;

import com.nicolasbarros.sorteiolibertadores.domains.Team;
import com.nicolasbarros.sorteiolibertadores.dtos.CreateTeamDTO;
import com.nicolasbarros.sorteiolibertadores.exceptions.ResourceNotFoundException;
import com.nicolasbarros.sorteiolibertadores.exceptions.TeamNotDrawnNotFoundException;
import com.nicolasbarros.sorteiolibertadores.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    public int countAllNotDrawned() {
        return teamRepository.countAllNotDrawned();
    }

    public Optional<Team> getById(Long id) {
        return teamRepository.findById(id);
    }

    public void update(Team team) {
        teamRepository.save(team);
    }

    public Optional<Team> getNotDrawTeam() {
        return Optional.ofNullable(teamRepository.findTeamNotDraw().orElseThrow(TeamNotDrawnNotFoundException::new));
    }

    public Team create(CreateTeamDTO data) {
        Team newTeam = new Team(data);
        return teamRepository.save(newTeam);
    }

    public Team delete(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " not found"));
        teamRepository.delete(team);

        return team;
    }
}
