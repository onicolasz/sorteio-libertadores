package com.nicolasbarros.sorteiolibertadores.controllers;

import com.nicolasbarros.sorteiolibertadores.domains.DrawGroup;
import com.nicolasbarros.sorteiolibertadores.services.DrawGroupService;
import com.nicolasbarros.sorteiolibertadores.dtos.DrawTeamDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/draw-group")
public class DrawGroupController {
    @Autowired
    private DrawGroupService drawGroupService;

    @Operation(description = "Realiza o sorteio de um time dentro de um grupo.", method= "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time sorteado em um grupo com sucesso."),
            @ApiResponse(responseCode = "404", description = "Sorteio com id {id} não encontrado"),
            @ApiResponse(responseCode = "404", description = "Time com id {id} não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o sorteio do time")
    })
    @PostMapping("/draw-team")
    public ResponseEntity drawTeam(@RequestBody DrawTeamDTO data) {
        try {
            DrawGroup drawTeam = drawGroupService.drawTeam(data);
            return ResponseEntity.ok().body(drawTeam);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("An error ocurred on draw team");
        }
    }
}
