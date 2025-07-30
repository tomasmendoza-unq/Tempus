package com.tempus.service;

import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.dto.comision.ComisionResponseDTO;

import java.util.List;

public interface IComisionService {
    public ComisionPostDTO createdComision(ComisionPostDTO comisionPostDTO);

    public List<ComisionResponseDTO> getComisiones();

    public ComisionResponseDTO getComision(Long id);

    public ComisionResponseDTO putComision(ComisionPostDTO comisionPostDTO, Long id);

    public void deleteComision(Long id);
}
