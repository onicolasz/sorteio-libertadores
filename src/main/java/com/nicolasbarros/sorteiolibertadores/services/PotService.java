package com.nicolasbarros.sorteiolibertadores.services;

import com.nicolasbarros.sorteiolibertadores.domains.Pot;
import com.nicolasbarros.sorteiolibertadores.domains.Team;
import com.nicolasbarros.sorteiolibertadores.repositories.TeamRepository;
import com.nicolasbarros.sorteiolibertadores.repositories.PotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PotService {
    @Autowired
    private PotRepository potRepository;
    @Autowired
    private TeamRepository teamRepository;

    public List<Pot> getAll() {
        return potRepository.findAll();
    }
    public Pot setTeamOnPot(long pot_id, long team_id) {
        Pot pot = potRepository.findById(pot_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pot with id: " + pot_id + " not found"));
        Team team = teamRepository.findById(team_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team with id: " + team_id + " not found"));

        team.setPot(pot);
        teamRepository.save(team);

        return potRepository.getReferenceById(pot_id);
    }
}
