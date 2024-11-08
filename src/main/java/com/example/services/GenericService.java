package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public abstract class GenericService<E, W>{
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected W wrapper;

    public abstract List<E> getAll();
    public abstract E getByID(int id);
    public abstract int insert(E object);
    public abstract int update(E object);
    public abstract int delete(int id);
    
}
