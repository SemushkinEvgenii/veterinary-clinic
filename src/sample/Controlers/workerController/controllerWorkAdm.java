package sample.Controlers.workerController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Main;

public class controllerWorkAdm {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button adminButton;

    @FXML
    private Button workerButton;

    @FXML
    void initialize() {
        adminButton.setOnAction(event -> {
            adminButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/worker/adminsAuth.fxml");
        });

        workerButton.setOnAction(event -> {
            workerButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/worker/workerAuth.fxml");
        });

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/sample.fxml");
        });
    }

}
