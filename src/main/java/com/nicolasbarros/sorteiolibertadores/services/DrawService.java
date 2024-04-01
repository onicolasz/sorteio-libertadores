package com.nicolasbarros.sorteiolibertadores.services;

import com.nicolasbarros.sorteiolibertadores.domains.Draw;
import com.nicolasbarros.sorteiolibertadores.repositories.DrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DrawService {
    @Autowired
    private DrawRepository drawRepository;

    public Draw getDraw(long draw_id) {
        return drawRepository.findById(draw_id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Draw with id: " + draw_id + " not found"));
    }
}
