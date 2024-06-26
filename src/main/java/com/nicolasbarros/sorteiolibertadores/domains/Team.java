package com.nicolasbarros.sorteiolibertadores.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nicolasbarros.sorteiolibertadores.dtos.CreateTeamDTO;
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
    private Long team_id;

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

    public Team(CreateTeamDTO createTeamDTO) {
        this.name = createTeamDTO.name();
        this.country = createTeamDTO.country();
    }
}
