package com.example.ripcarServicerestapi.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
public class Customer {

    private @Id @GeneratedValue
    int id;
    private String name;
    private String surname;
    private String phone_number;
    private String car;
    public Date date;


    public Customer() {
    }


    public Customer(int id, String name, String surname, int phone_number, String car, Date date) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone_number = String.valueOf(phone_number);
        this.car = car;
        this.date = date;
    }

    @Override
    public String toString() {

        var dateString = date.toString();
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            dateString = format.format(date);
        }
        catch (Exception ex){

        }

        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", car='" + car + '\'' +
                ", phone_number=" + phone_number +
                ", date=" + dateString +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getPhone() {
        return phone_number;
    }

    public void setPhone(String phone_number) {
        this.phone_number = phone_number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
