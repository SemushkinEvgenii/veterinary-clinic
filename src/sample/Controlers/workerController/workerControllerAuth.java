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
import sample.Main;
import sample.GetSet.Worker;

public class workerControllerAuth {

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
                loginWorker(textLogin, textPassword); }
            else{
                System.out.println("eror");
            }
        });
        backButt.setOnAction(event -> {
            backButt.getScene().getWindow().hide();
            Main.OpenIcon("/sample/SceneFxml/worker/mainWorkAdm.fxml");
        });


    }
    // авторизация работника
    private void loginWorker(String textLogin, String textPassword) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        Worker worker = new Worker();
        worker.setLogin(textLogin);
        worker.setPassword(textPassword);
        ResultSet result = dbHandler.getWorker(worker);
        int counter = 0;
        try {
            while (result.next()) {
                counter++; }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(counter >= 1){
            System.out.println("Succsec!!");
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Работник не найдет");
            alert.setContentText("Нажмине <Ок>, затем попробуйте сново, возможно вы где-то допустили ошибку.");

            alert.showAndWait();
        }
    }

}
