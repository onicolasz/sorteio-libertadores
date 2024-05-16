package com.nicolasbarros.sorteiolibertadores.controllers;

import com.nicolasbarros.sorteiolibertadores.domains.Draw;
import com.nicolasbarros.sorteiolibertadores.services.DrawService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/draw")
public class DrawController {
    @Autowired
    private DrawService drawService;

    @Operation(description = "Retorna o sorteio com os grupos e times")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sorteio retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sorteio com id {id} não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar sorteio")
    })
    @GetMapping("/{draw_id}")
    public ResponseEntity getDraw(@PathVariable("draw_id") Long draw_id) {
        Draw draw = drawService.getById(draw_id);
        return ResponseEntity.ok().body(draw);
    }

    @Operation(description = "Realiza todo o sorteio, colocando os times dos potes em seus respectivos grupos sorteados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sorteio realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sorteio com id {id} não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar sorteio")
    })
    @PostMapping("/{draw_id}/draw-all")
    public ResponseEntity drawAll(@PathVariable("draw_id") Long draw_id) {
        Draw draw = drawService.drawAll(draw_id);
        return ResponseEntity.ok().body(draw);
    }
}
