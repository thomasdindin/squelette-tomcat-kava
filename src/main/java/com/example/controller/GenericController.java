package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * GenericController to implement the methods of the CRUD operations
 * @param <T> Entity type
 * @param <S> Service type
 */
public abstract class GenericController<T, S> {

    @Autowired
    protected S service;

    @Autowired
    protected ObjectMapper objectMapper;

    public ResponseEntity<String> getJsonData(Object object, HttpStatus status) {
            String jsonData;
            try {
                jsonData = objectMapper.writeValueAsString(object);
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
                return new ResponseEntity<>(jsonData, headers, status);
            } catch (JsonProcessingException e) {
                return new ResponseEntity<>("Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    // CREATE
    public abstract ResponseEntity<String> insert(@RequestBody T object);

    // READ
    public abstract ResponseEntity<String> getAll();
    public abstract ResponseEntity<String> getById(@PathVariable("id") int id);

    //UPDATE
    public abstract ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody T object);

    //DELETE
    public abstract ResponseEntity<String> delete(@PathVariable("id") int id);
}
