package com.nicolasbarros.sorteiolibertadores.services;

import com.nicolasbarros.sorteiolibertadores.domains.Pot;
import com.nicolasbarros.sorteiolibertadores.domains.Team;
import com.nicolasbarros.sorteiolibertadores.exceptions.ResourceNotFoundException;
import com.nicolasbarros.sorteiolibertadores.repositories.PotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotService {
    @Autowired
    private PotRepository potRepository;
    @Autowired
    private TeamService teamService;

    public List<Pot> getAll() {
        return potRepository.findAll();
    }
    public Pot drawTeamOnPot(long pot_id, long team_id) {
        Pot pot = potRepository.findById(pot_id).orElseThrow(() -> new ResourceNotFoundException("Pot with id: " + pot_id + " not found"));
        Team team = teamService.getById(team_id).orElseThrow(() -> new ResourceNotFoundException("Team with id: " + team_id + " not found"));

        team.setPot(pot);
        teamService.update(team);

        return potRepository.getReferenceById(pot_id);
    }
}
