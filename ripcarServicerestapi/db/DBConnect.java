package com.example.ripcarServicerestapi.db;

import com.example.ripcarServicerestapi.Entities.Customer;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class DBConnect {

    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static String url = "jdbc:mysql://localhost:3306/ripcarDB";
    private static String user = "root";
    private static String pass = "";

    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/ripcarDB", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/ripcarDB");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    public static Collection<Customer> getCustomersReservations() {
        Connection conn = ConnectDb();
        Collection<Customer> reservationMap = new ArrayList<>();
        try {
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement("select * from customer");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("surname"),
                        Integer.parseInt(rs.getString("phone_number")),
                        rs.getString("car"),
                        rs.getDate("date"));
                reservationMap.add(customer);
            }
            return reservationMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }


    public static Customer getCustomerReservation(int id) throws SQLException {
        Customer customer;

        try {
            Connection conn = ConnectDb();
            String sql = String.format("select * from customer where id = %s", id);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                customer = new Customer(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("surname"),
                        Integer.parseInt(rs.getString("phone_number")),
                        rs.getString("car"),
                        rs.getDate("date"));

            } else {
                throw new SQLException("No reservation was found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return customer;
    }

    public static Customer updateCustomerReservation(Customer customer) {
        Connection conn = ConnectDb();
        String sql = String.format("UPDATE customer " +
                        "SET name = '%s', surname = '%s', phone_number = '%s', car = '%s', date = '%s'" +
                        "WHERE id = %s",
                customer.getName(),
                customer.getSurname(),
                customer.getPhone(),
                customer.getCar(),
                customer.getDate(),
                customer.getId());

        System.out.println(sql);
        try {
            assert conn != null;
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public static Customer AddNewCustomerReservation(Customer customer) {
        Connection conn = ConnectDb();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        System.out.println("Sent date: " + customer.getDate());

        var formattedDate = sdf.format(customer.getDate());

        System.out.println("Formatted date: " + formattedDate);

        String sql = String.format("INSERT INTO customer (name, surname, phone_number, car, date) " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s')",
                customer.getName(),
                customer.getSurname(),
                customer.getPhone(),
                customer.getCar(),
                formattedDate);

        System.out.println(sql);
        try {
            assert conn != null;
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating reservation failed, no ID obtained.");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public static int checkDate(Date datetime){
        int count = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, pass);
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT date FROM customer");
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(datetime)){
                    count++;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }
}
