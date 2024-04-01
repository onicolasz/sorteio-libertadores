package com.nicolasbarros.sorteiolibertadores.dtos;

import com.nicolasbarros.sorteiolibertadores.domains.Countries;

public record RequestTeamDTO(String name, Countries country) {
}
