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
            @ApiResponse(responseCode = "200", description = "Retorna o sorteio com os grupos de times"),
            @ApiResponse(responseCode = "404", description = "Não existe sorteio com o id informado")
    })
    @GetMapping("/{draw_id}")
    public ResponseEntity getDraw(@PathVariable("draw_id") long draw_id) {
        try {
            Draw draw = drawService.getDraw(draw_id);
            return ResponseEntity.ok().body(draw);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("An error ocurred on draw team");
        }
    }
}
