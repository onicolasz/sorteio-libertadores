package com.nicolasbarros.sorteiolibertadores.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "draw_id")
public class Draw {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long draw_id;

    @OneToMany(mappedBy = "draw", cascade = CascadeType.ALL)
    private List<DrawGroup> drawGroups;
}
