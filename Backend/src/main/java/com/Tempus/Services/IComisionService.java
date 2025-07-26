package com.Tempus.Services;

import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.DTO.ComisionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IComisionService {
    public ComisionCreatedDTO createdComision(ComisionCreatedDTO comisionDTO);

    public List<ComisionDTO> getComisiones();

    public ComisionDTO getComision(Long id);

    public ComisionCreatedDTO putComision(Long id, ComisionCreatedDTO comisionCreatedDTO);
}
