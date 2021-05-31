package com.example.ripcarServicerestapi.Service.Implementation;

import com.example.ripcarServicerestapi.Entities.Customer;
import com.example.ripcarServicerestapi.Service.I_ReservationService;
import com.example.ripcarServicerestapi.db.DBConnect;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReservationService implements I_ReservationService {

    private Long reservationId = 1L;
    private Map<Long, Customer> reservationMap = new HashMap<Long, Customer>();

    @Override
    public Collection<Customer> findAll() {
        return DBConnect.getCustomersReservations();
    }

    @Override
    public Customer findById(int id) {
        try {
            return DBConnect.getCustomerReservation(id);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return DBConnect.AddNewCustomerReservation(customer);
    }

    @Override
    public Customer update(Customer customer) {
        try {
            if(DBConnect.getCustomerReservation(customer.getId()) != null){
                return DBConnect.updateCustomerReservation(customer);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer deleteById(Long id) {
        if (reservationMap.get(id) != null){
            return reservationMap.remove(id);
        }
        return null;
    }
}
