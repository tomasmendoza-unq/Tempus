package com.tempus.service.impls;

import com.tempus.Factory.IComisionFactory;
import com.tempus.Factory.impls.ComisionFactory;
import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.models.Comision;
import com.tempus.repository.IComisionRepository;
import com.tempus.service.IComisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComisionService implements IComisionService {

    @Autowired
    private IComisionRepository comisionRepository;

    @Autowired
    private IComisionFactory comisionFactory;

    @Override
    public ComisionPostDTO createdComision(ComisionPostDTO comisionPostDTO) {
        Comision comision = comisionFactory.toEntity(comisionPostDTO);
        Comision saved = this.save(comision);

        return comisionFactory.toPostDTO(saved);
    }

    private Comision save(Comision comision) {
        return comisionRepository.save(comision);
    }


}
