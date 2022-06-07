package sample.Controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authorizationButton;

    @FXML
    private Button registrationButton;

    @FXML
    private Button viewingServices;

    @FXML
    private Button workerButton;

    @FXML
    void initialize() {
        viewingServices.setOnAction(event -> {
            viewingServices.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/services.fxml");
        });

        registrationButton.setOnAction(event -> {
            registrationButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/windowRegistr.fxml");
        });


        authorizationButton.setOnAction(event -> {
            authorizationButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/authWindow.fxml");
        });

        workerButton.setOnAction(event -> {
            workerButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/worker/mainWorkAdm.fxml");
        });

    }

}
