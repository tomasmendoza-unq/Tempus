package com.tempus.Factory;

import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.dto.comision.ComisionResponseDTO;
import com.tempus.models.Comision;

public interface IComisionFactory {
    Comision toEntity(ComisionPostDTO comisionPostDTO);

    ComisionPostDTO toPostDTO(Comision saved);

    ComisionResponseDTO toResponseDTO(Comision comision);

    void updateEntity(Comision comision, ComisionPostDTO comisionPostDTO);
}
