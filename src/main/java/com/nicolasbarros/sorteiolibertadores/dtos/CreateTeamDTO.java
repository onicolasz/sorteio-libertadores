package com.nicolasbarros.sorteiolibertadores.dtos;

import com.nicolasbarros.sorteiolibertadores.domains.Countries;

public record CreateTeamDTO(String name, Countries country) {
}
