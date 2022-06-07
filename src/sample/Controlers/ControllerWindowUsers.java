package sample.Controlers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.GetSet.Service;

public class ControllerWindowUsers implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        idService.setCellValueFactory(new PropertyValueFactory<Service, Integer>("idService"));
        nameService.setCellValueFactory(new PropertyValueFactory<Service, String>("NameService"));
        priceService.setCellValueFactory(new PropertyValueFactory<Service, String>("PriceService"));


        // заполняем таблицу данными
        tableServicefull.setItems(service);


        mainButton.setOnAction(event -> {
            mainButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/sample.fxml");
        });
    }
    private void initData() {
        try {
            dbConnection = getDbConnection();
            ResultSet resSet = dbConnection.createStatement().executeQuery("SELECT * FROM service");
            while (resSet.next()) {
                service.add(new Service(resSet.getInt("idService"), resSet.getString("NameService"), resSet.getString("PriceService")));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + "localHost" + ":" + "3306" + "/" + "project";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, "root", "Qw12345678");
        return dbConnection;
    }

    private ObservableList<Service> service = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainButton;

    @FXML
    private TableColumn<Service, Integer> idService;

    @FXML
    private TableColumn<Service, String> nameService;

    @FXML
    private TableColumn<Service, String> priceService;

    @FXML
    private TableView<Service> tableServicefull;


}

