package com.nicolasbarros.sorteiolibertadores.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nicolasbarros.sorteiolibertadores.dtos.RequestTeamDTO;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "team_id")
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long team_id;

    @NotNull
    @Column( nullable = false )
    private String name;

    @NotNull
    @Column( nullable = false )
    private Countries country;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pot_id")
    private Pot pot;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "draw_group_id")
    private DrawGroup drawGroup;

    public Team(RequestTeamDTO requestTeamDTO) {
        this.name = requestTeamDTO.name();
        this.country = requestTeamDTO.country();
    }
}
