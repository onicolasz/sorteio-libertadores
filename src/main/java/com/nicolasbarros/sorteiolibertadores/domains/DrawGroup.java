package com.nicolasbarros.sorteiolibertadores.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "draw_group_id")
public class DrawGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long draw_group_id;

    @NotNull
    @Column( nullable = false )
    private String letter;

    @OneToMany(mappedBy = "drawGroup", cascade = CascadeType.ALL)
    private List<Team> teams;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "draw_id")
    private Draw draw;
}
