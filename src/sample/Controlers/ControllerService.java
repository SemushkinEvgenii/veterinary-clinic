package sample.Controlers;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import sample.Main;
import sample.GetSet.Service;

public class ControllerService implements Initializable{

    private Service selectedTable1 = new Service();

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

        btnRem1.setOnAction(event -> {
            selectedTable1 = tableServicefull.getSelectionModel().getSelectedItem();
            selectedTable1.getIDService1();
            deleteTable1(selectedTable1.getIDService1());
            tableServicefull.getItems().remove(selectedTable1);
        });

        btnAdd1.setOnAction(event -> {
            paneAdd1.setVisible(true);
        });
        btnSave1.setOnAction(event -> {
            paneAdd1.setVisible(false);
            if (!(tfName.getText().isEmpty() || tfNum.getText().isEmpty()
                    || tfPrice.getText().isEmpty())) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localHost:3306/project",
                            "root", "Qw12345678")){
                        PreparedStatement statement = conn.prepareStatement
                                ("INSERT into service(idService,NameService,PriceService) VALUES(?,?,?)");
                        statement.setString(1, tfNum.getText());
                        statement.setString(2, tfName.getText());
                        statement.setString(3, tfPrice.getText());
                        statement.executeUpdate();
                        initData();
                    }}
                catch (Exception e) {
                    System.out.println("Table problems");
                }}
        });
        btnBack1.setOnAction(event -> {
            paneAdd1.setVisible(false);
        });
    }

    public static void deleteTable1(int Code1) {
        String querry = "DELETE FROM service WHERE `idService` = "+ Code1;
        try {Connection conn = DriverManager.getConnection("jdbc:mysql://localHost:3306/project",
                "root", "Qw12345678");
            PreparedStatement preparedStatement = conn.prepareStatement(querry);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
    private Pane paneAdd1;

    @FXML
    private Button mainButton;

    @FXML
    private Button btnSave1;

    @FXML
    private Button btnAdd1;

    @FXML
    private Button btnBack1;

    @FXML
    private TextField tfNum;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPrice;

    @FXML
    private TableColumn<Service, Integer> idService;

    @FXML
    private TableColumn<Service, String> nameService;

    @FXML
    private TableColumn<Service, String> priceService;

    @FXML
    private TableView<Service> tableServicefull;

    @FXML
    private Button btnRem1;

}