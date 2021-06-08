package com.example.ripcarServicerestapi.Resource.impl;

import com.example.ripcarServicerestapi.Entities.Customer;
import com.example.ripcarServicerestapi.Service.Implementation.ReservationService;
import com.example.ripcarServicerestapi.TimeIsFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins="http://localhost:3000")
public class ReservationResource /*implements I_Resource<Customer>*/ {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public Collection<Customer> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("{id}")
    public Customer findById(@PathVariable int id){
        return reservationService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity save(@RequestBody Customer customer) throws TimeIsFullException {
        if (reservationService.save(customer)) return new ResponseEntity(HttpStatus.OK);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Customer update(@RequestBody Customer customer){
        return reservationService.update(customer);

    }

    @DeleteMapping("{id}")
    public Customer deleteById(@PathVariable Long id){
        return reservationService.deleteById(id);
    }
}
