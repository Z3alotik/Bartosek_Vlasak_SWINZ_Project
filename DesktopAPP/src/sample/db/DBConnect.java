package sample.db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBConnect {

    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static String url = "jdbc:mysql://localhost:3306/ripcarDB";
    private static String user = "root";
    private static String pass = "";

    public void ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, pass);
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select * from customer");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int phone_number = resultSet.getInt(4);
                String car = resultSet.getString(5);
                String date = resultSet.getString(6);

                //System.out.println(id + " | " + name + " | " + surname + " | " + phone_number + " | " + car);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(String name, String surname, String phone_number, String car, String datetime) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, pass);
            statement = connect.createStatement();
            PreparedStatement post = connect.prepareStatement("INSERT INTO customer (name, surname, phone_number, car, date)" +
                    "VALUES ('" + name + "','" + surname + "','" + phone_number + "','" + car + "','" + datetime + "')");
            post.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int checkDate(String datetime){
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

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }
}