package sk.upjs.paz1c.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ForgottenPasswordController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button sendPasswordButton;

    @FXML
    void initialize() {
        sendPasswordButton.setOnAction(eh -> {
            String email = emailTextField.textProperty().get();
            //treba dorobit manager
            //LostPasswordManager.sendNewPassword(email);
            sendPasswordButton.getScene().getWindow().hide();
        });
    }
}
