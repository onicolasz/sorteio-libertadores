package com.nicolasbarros.sorteiolibertadores.domains;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "pot_id")
public class Pot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pot_id;

    @NotNull
    @Column( nullable = false )
    private long number;

    @OneToMany(mappedBy = "pot", cascade = CascadeType.ALL)
    private List<Team> teams;
}
