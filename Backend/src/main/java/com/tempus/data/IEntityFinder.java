package com.tempus.data;

import java.util.List;

public interface IEntityFinder <T>{

    public T findById(Long id);

    public List<T> findAll();
}
