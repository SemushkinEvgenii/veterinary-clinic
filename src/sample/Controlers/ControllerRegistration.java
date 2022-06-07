package sample.Controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DataBaseHandler;
import sample.Main;
import sample.GetSet.User;

public class ControllerRegistration {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButt;

    @FXML
    private TextField firstname;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField pastname;

    @FXML
    private Button registerButton;

    @FXML
    void initialize() {

        registerButton.setOnAction(event -> {
            String textName = firstname.getText().trim();
            String textLastName = pastname.getText().trim();
            String textLogin = loginField.getText().trim();
            String textPassword = passwordField.getText().trim();
            if(!textName.equals("") && !textLastName.equals("") &&
                    !textLogin.equals("") && !textPassword.equals("")){
                signUpNewUser();
                registerButton.getScene().getWindow().hide();
                Main.OpenIcon("/sample/SceneFxml/sample.fxml");
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("!!!Ошибка!!!");
                alert.setHeaderText("Поля не заполнены!");
                alert.setContentText("Заполните все поля!");

                alert.showAndWait();
            }


        });
        backButt.setOnAction(event -> {
            backButt.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/sample.fxml");
        });

    }

    private void signUpNewUser() {
        DataBaseHandler dBHandler = new DataBaseHandler();

        String firstName = firstname.getText();
        String lastName = pastname.getText();
        String login = loginField.getText();
        String password = passwordField.getText();

        User user = new User(firstName, lastName, login, password);

        dBHandler.singUpUser(user);
    }

}
