package sample.Controlers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DataBaseHandler;
import sample.Main;
import sample.GetSet.User;

public class ControllerAuth {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButt;

    @FXML
    private Button enterButton;

    @FXML
    private TextField loginFieldAurh;

    @FXML
    private PasswordField passwordFieldAuth;

    @FXML
    void initialize() {

        enterButton.setOnAction(event -> {
            String textLogin = loginFieldAurh.getText().trim();
            String textPassword = passwordFieldAuth.getText().trim();
            if(!textLogin.equals("") && !textPassword.equals("")){
                loginUser(textLogin, textPassword); }
            else{
                System.out.println("error");
            }
        });
        backButt.setOnAction(event -> {
            backButt.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/sample.fxml");
        });

    }

    private void loginUser(String textLogin, String textPassword) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setLogin(textLogin);
        user.setPassword(textPassword);
        ResultSet result = dbHandler.getUser(user);
        int counter = 0;

        try {
            while (result.next()) {
                counter++; }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(counter >= 1){
            enterButton.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/windowUsers.fxml");
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("!!!Ошибка!!!");
            alert.setHeaderText("Пользователь не найдет/введён неверно");
            alert.setContentText("Нажмине <Ок>, чтобы продолжить. Попробуйте проверить введённые данные.");

            alert.showAndWait();
        }
    }
}
