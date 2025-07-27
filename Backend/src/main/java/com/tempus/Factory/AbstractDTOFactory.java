package com.tempus.Factory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDTOFactory {

    @Autowired
    protected ModelMapper modelMapper;

    protected <T, U> T toEntity(U source, Class<T> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    protected <T, U> U toDTO(T source, Class<U> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public <T, U> void updateEntity(T source, U destination){
        modelMapper.map(destination, source);
    }
}
