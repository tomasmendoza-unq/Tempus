package com.Tempus.Models;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Enums.TipoDeMateria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
public class MateriaCorrelativa extends Materia{

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "correlativas",
            joinColumns = @JoinColumn(name = "correlativa_id"),
            inverseJoinColumns = @JoinColumn(name = "simple_id")
    )
    private List<Materia> correlativas;

    public MateriaCorrelativa(){
        correlativas = new ArrayList<>();
    }

    @Override
    public MateriaDTO toDTO() {
        List<MateriaDTO> materiaDTOS = correlativas
                                        .stream()
                                        .map(Materia::toDTO).toList();
        return MateriaCorrelativaDTO.builder()
                .id(id)
                .nombre(nombre)
                .correlativas(materiaDTOS)
                .build();
    }


    public void addCorrelativas(List<Materia> correlativas) {
        this.correlativas.addAll(correlativas);
    }

}
