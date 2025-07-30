package com.tempus.service;

import com.tempus.dto.comision.ComisionPostDTO;
import org.springframework.stereotype.Service;

public interface IComisionService {
    public ComisionPostDTO createdComision(ComisionPostDTO comisionPostDTO);
}
