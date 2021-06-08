package com.example.ripcarServicerestapi.Service;

import com.example.ripcarServicerestapi.Entities.Customer;
import com.example.ripcarServicerestapi.TimeIsFullException;

import java.sql.SQLException;
import java.util.Collection;

public interface I_ReservationService {
    Collection<Customer> findAll() throws SQLException;

    Customer findById(int id);

    boolean save(Customer customer) throws TimeIsFullException;

    Customer update(Customer customer);

    Customer deleteById(Long id);
}
