package com.tempus.data;

public interface IEntityFinder <T>{

    public T findById(Long id);
}
