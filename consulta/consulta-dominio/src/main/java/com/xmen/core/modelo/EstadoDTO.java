package com.xmen.core.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EstadoDTO {
    protected int count_mutant_dna;
    protected int count_human_dna;
    protected double ratio;
}