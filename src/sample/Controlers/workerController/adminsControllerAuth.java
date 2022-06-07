package sample.Controlers.workerController;

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
import sample.GetSet.Admins;
import sample.Main;
import sample.GetSet.Worker;

public class adminsControllerAuth {

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
                loginAdmins(textLogin, textPassword); }
            else{
                System.out.println("error");
            }
        });
        backButt.setOnAction(event -> {
            backButt.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/worker/mainWorkAdm.fxml");
        });
    }

    // авторизация администратора
    private void loginAdmins(String textLogin, String textPassword) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        Admins admins = new Admins();
        admins.setLogin(textLogin);
        admins.setPassword(textPassword);
        ResultSet result = dbHandler.getAdmins(admins);
        int counter = 0;
        try {
            while (result.next()) {
                counter++; }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(counter >= 1){
            System.out.println("Success!!");
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Администратор не найдет");
            alert.setContentText("Нажмине <Ок>, затем попробуйте сново, возможно вы где-то допустили ошибку.");

            alert.showAndWait();
        }
    }
}
