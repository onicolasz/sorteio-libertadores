package com.nicolasbarros.sorteiolibertadores.controllers;

import com.nicolasbarros.sorteiolibertadores.dtos.CreateTeamDTO;
import com.nicolasbarros.sorteiolibertadores.domains.Team;
import com.nicolasbarros.sorteiolibertadores.exceptions.TeamNotDrawnNotFoundException;
import com.nicolasbarros.sorteiolibertadores.infra.RestErrorMessage;
import com.nicolasbarros.sorteiolibertadores.services.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Operation(description = "Retorna todos os times cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos times retornada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar times")
    })
    @GetMapping
    public ResponseEntity getAll() {
        List<Team> list = teamService.getAll();
        return ResponseEntity.ok(list);
    }

    @Operation(description = "Retorna um time aleatório ainda não sorteado do pote de menor número possível.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não existe time para ser sorteado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar time")
    })
    @GetMapping("/not-draw")
    public ResponseEntity getNotDrawTeam() {
        Optional<Team> team = teamService.getNotDrawTeam();
        return ResponseEntity.ok().body(team);
    }

    @Operation(description = "Cadastra um novo time para o sorteio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar time")
    })
    @PostMapping
    public ResponseEntity create(@RequestBody CreateTeamDTO data) {
            Team createdTeam = teamService.create(data);
            return ResponseEntity.ok().body(createdTeam);
    }

    @Operation(description = "Deleta um time do sorteio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Time deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Time com id {id} não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar time")
    })
    @DeleteMapping("/{team_id}")
    public ResponseEntity delete(@PathVariable("team_id") long team_id) {
        Team deletedTeam = teamService.delete(team_id);
        return ResponseEntity.ok().body(deletedTeam);
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        RestErrorMessage threatError = new RestErrorMessage(HttpStatus.BAD_REQUEST, "País inválido para cadastro de time.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatError);
    }
}
