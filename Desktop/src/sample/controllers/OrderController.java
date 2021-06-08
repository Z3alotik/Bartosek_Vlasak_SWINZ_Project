package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import sample.alertBoxes.AlertBox;
import sample.db.DBConnect;
import sample.models.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private ImageView imageView;

    //Text fields
    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_surname;
    @FXML
    private TextField txt_phone_number;
    @FXML
    private TextField txt_car;
    @FXML
    private DatePicker date_picker;
    @FXML
    private ChoiceBox time_picker;
    @FXML
    private Label name_label;
    @FXML
    private Label surname_label;
    @FXML
    private Label phone_label;
    @FXML
    private Label car_label;

    //Table
    @FXML
    private TableView<Data> table;
    @FXML
    private TableColumn<Data, String> col_id;
    @FXML
    private TableColumn<Data, String> col_name;
    @FXML
    private TableColumn<Data, String> col_surname;
    @FXML
    private TableColumn<Data, String> col_phone_number;
    @FXML
    private TableColumn<Data, String> col_car;
    @FXML
    private TableColumn<Data, String> col_date;

    ObservableList<Data> observe_List = FXCollections.observableArrayList();

    DBConnect database = new DBConnect();

    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Data data = null ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imageView.setImage(new Image ("resources/CarLogo.png"));

        initializeTime();
        initializeFields();
        initializeDatePicker();

        database.ConnectDb();
        try {
            Connection con = DBConnect.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from customer");
            while (rs.next()) {
                observe_List.add(new Data(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone_number"),
                        rs.getString("car"),
                        rs.getString("date")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_phone_number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        col_car.setCellValueFactory(new PropertyValueFactory<>("car"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        //table.refresh();
        table.setItems(observe_List);
    }

    public void initializeTime() {
        time_picker.getItems().add("01:00");
        time_picker.getItems().add("02:00");
        time_picker.getItems().add("03:00");
        time_picker.getItems().add("04:00");
        time_picker.getItems().add("05:00");
        time_picker.getItems().add("06:00");
        time_picker.getItems().add("07:00");
        time_picker.getItems().add("08:00");
        time_picker.getItems().add("09:00");
        time_picker.getItems().add("10:00");
        time_picker.getItems().add("11:00");
        time_picker.getItems().add("12:00");
        time_picker.getItems().add("13:00");
        time_picker.getItems().add("14:00");
        time_picker.getItems().add("15:00");
        time_picker.getItems().add("16:00");
        time_picker.getItems().add("17:00");
        time_picker.getItems().add("18:00");
        time_picker.getItems().add("19:00");
        time_picker.getItems().add("20:00");
        time_picker.getItems().add("21:00");
        time_picker.getItems().add("22:00");
        time_picker.getItems().add("23:00");
        time_picker.getItems().add("24:00");
    }


    public void initializeFields() {
        txt_name.textProperty().addListener((observable, oldValue, newValue) -> {
            String s = newValue;
            int numcounter = 0;
            char[] chars = s.toCharArray();
            for (char c : chars) {
                if (Character.isDigit(c)) numcounter++;
            }
            if (newValue.length() > 15) {
                name_label.setText("Příliš moc znaků!");
                name_label.setVisible(true);
            } else if (numcounter > 0) {
                name_label.setText("Zadávejte pouze písmena!");
                name_label.setVisible(true);
            } else name_label.setVisible(false);
            if (newValue.isEmpty()) name_label.setVisible(false);
        });

        txt_surname.textProperty().addListener((observable, oldValue, newValue) -> {
            String s = newValue;
            int numcounter = 0;
            char[] chars = s.toCharArray();
            for (char c : chars) {
                if (Character.isDigit(c)) numcounter++;
            }
            if (newValue.length() > 25) {
                surname_label.setText("Příliš moc znaků!");
                surname_label.setVisible(true);
            } else if (numcounter > 0) {
                surname_label.setText("Zadávejte pouze písmena!");
                surname_label.setVisible(true);
            } else surname_label.setVisible(false);
            if (newValue.isEmpty()) surname_label.setVisible(false);
        });

        txt_phone_number.textProperty().addListener((observable, oldValue, newValue) -> {
            String s = newValue;
            int charcounter = 0;
            char[] chars = s.toCharArray();
            for (char c : chars) {
                if (Character.isLetter(c)) charcounter++;
            }
            if (charcounter > 0) {
                phone_label.setText("Zadávejte pouze čísla!");
                phone_label.setVisible(true);
            } else if (newValue.length() > 9) {
                phone_label.setText("Maximum pro zadání tel.č. je 9 čísel");
                phone_label.setVisible(true);
            } else phone_label.setVisible(false);
        });

        txt_car.textProperty().addListener((observable, oldValue, newValue) -> {
            String s = newValue;
            int numcounter = 0;
            char[] chars = s.toCharArray();
            for (char c : chars) {
                if (Character.isDigit(c)) numcounter++;
            }
            if (newValue.length() > 20) {
                car_label.setText("Příliš moc znaků!");
                car_label.setVisible(true);
            } else if (numcounter > 0) {
                car_label.setText("Zadávejte pouze písmena!");
                car_label.setVisible(true);
            } else car_label.setVisible(false);
            if (newValue.isEmpty()) car_label.setVisible(false);
        });
    }
    public void initializeDatePicker(){
        date_picker.setDayCellFactory(picker ->new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today=LocalDate.now(ZoneId.of("CET"));
                if (empty || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    setStyle("-fx-background-color: #ffc0cb; -fx-text-fill: darkgray;");
                    setDisable(true);
                }
                else if(date.compareTo(today) < 0){
                    setDisable(true);
                }
            }
        });

        StringConverter<LocalDate> converter = new LocalDateStringConverter() {
            @Override
            public LocalDate fromString(String string) {
                LocalDate date = super.fromString(string);
                if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                    return date.minusDays(1);
                } else {
                    return date ;
                }
            }
        };
        date_picker.setConverter(converter);
    }


    public boolean checkDate(){
        String datetime = date_picker.getValue().toString() + " " + time_picker.getValue().toString()+":00";
        if (database.checkDate(datetime)>=2) return true;
        else return false;
    }

    public void Add_order() {
        String name = txt_name.getCharacters().toString();
        String surname = txt_surname.getCharacters().toString();
        String phone_number = txt_phone_number.getCharacters().toString();
        String car = txt_car.getCharacters().toString();
        String datetime = date_picker.getValue().toString() + " " + time_picker.getValue().toString();

        if(checkDate()){
            AlertBox.display("Chyba", "Tento čas je plně obsazen. Zvolte prosím jiný.");
        }
        else if(!checkDate()) {
            database.insert(name, surname, phone_number, car, datetime);
            JOptionPane.showMessageDialog(null, "Objednávka byla úspěšně přidána!");
            txt_name.clear();
            txt_surname.clear();
            txt_phone_number.clear();
            txt_car.clear();
            date_picker.setValue(null);
            time_picker.setValue(null);
        }
    }

    public void refresh_Orders(){
        table.refresh();
        observe_List = FXCollections.observableArrayList();
        try {
            Connection con = DBConnect.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from customer");
            while (rs.next()) {
                observe_List.add(new Data(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone_number"),
                        rs.getString("car"),
                        rs.getString("date")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(observe_List);
    }

    public void Delete(){
        DBConnect.ConnectDb();
        try {
            data = table.getSelectionModel().getSelectedItem();
            query = "DELETE FROM customer WHERE id  ="+data.getId();
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            refresh_Orders();

        } catch (SQLException ex) {
            /*Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);*/
        }
    }

}