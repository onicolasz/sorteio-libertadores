package com.nicolasbarros.sorteiolibertadores.controllers;

import com.nicolasbarros.sorteiolibertadores.dtos.RequestTeamDTO;
import com.nicolasbarros.sorteiolibertadores.domains.Team;
import com.nicolasbarros.sorteiolibertadores.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity getAll() {
        List<Team> list = teamService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/not-draw")
    public ResponseEntity getNotDrawTeam() {
        Team team = teamService.getNotDrawTeam();
        return ResponseEntity.ok().body(team);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody RequestTeamDTO data) {
        try {
            Team createdTeam = teamService.create(data);
            return ResponseEntity.ok().body(createdTeam);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("An error ocurred on create team");
        }
    }

    @DeleteMapping("/{team_id}")
    public ResponseEntity create(@PathVariable("team_id") long team_id) {
        try {
            Team deletedTeam = teamService.delete(team_id);
            return ResponseEntity.ok().body(deletedTeam);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("An error ocurred on delete team");
        }
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Invalid country provided.");
    }
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity handleHttpMessageNotReadableException(org.springframework.dao.DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body("Invalid request body");
    }

}
