package com.example.ripcarServicerestapi.Resource;

import com.example.ripcarServicerestapi.Entities.Customer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collection;

public interface I_Resource<T> {

    @GetMapping
    ResponseEntity <Collection<T>> findAll() throws SQLException;

    @GetMapping("{id}")
    ResponseEntity <T> findById(@PathVariable long id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity <T> save(@RequestBody T t);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity <T> update(@RequestBody T t);

    @DeleteMapping("{id}")
    ResponseEntity <T> deleteById(@PathVariable Long id);
}
