package com.nicolasbarros.sorteiolibertadores.controllers;

import com.nicolasbarros.sorteiolibertadores.domains.DrawGroup;
import com.nicolasbarros.sorteiolibertadores.services.DrawGroupService;
import com.nicolasbarros.sorteiolibertadores.dtos.SetTeamGroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/draw-group")
public class DrawGroupController {
    @Autowired
    private DrawGroupService drawGroupService;

    @PutMapping("/draw-team")
    public ResponseEntity setTeamGroup(@RequestBody SetTeamGroupDTO data) {
        try {
            DrawGroup drawTeam = drawGroupService.setTeamGroup(data);
            return ResponseEntity.ok().body(drawTeam);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("An error ocurred on draw team");
        }
    }
}
