package com.nicolasbarros.sorteiolibertadores.controllers;

import com.nicolasbarros.sorteiolibertadores.domains.Pot;
import com.nicolasbarros.sorteiolibertadores.services.PotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pot")
public class PotController {
    @Autowired
    private PotService potService;

    @GetMapping()
    public ResponseEntity getAll() {
        List<Pot> pots = potService.getAll();
        return ResponseEntity.ok().body(pots);
    }

    @PutMapping("/{pot_id}/team/{team_id}")
    public ResponseEntity drawTeamOnPot(@PathVariable("pot_id") long pot_id, @PathVariable("team_id") long team_id) {
        Pot pot = potService.drawTeamOnPot(pot_id, team_id);
        return ResponseEntity.ok().body(pot);
    }
}
