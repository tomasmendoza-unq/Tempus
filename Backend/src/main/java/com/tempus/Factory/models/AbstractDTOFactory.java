package com.tempus.Factory.models;

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

}
