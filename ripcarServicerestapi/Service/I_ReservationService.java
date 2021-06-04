package com.example.ripcarServicerestapi.Service;

import com.example.ripcarServicerestapi.Entities.Customer;

import java.sql.SQLException;
import java.util.Collection;

public interface I_ReservationService {
    Collection<Customer> findAll() throws SQLException;

    Customer findById(int id);

    Customer save(Customer customer);

    Customer update(Customer customer);

    Customer deleteById(Long id);
}
