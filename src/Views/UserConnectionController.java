/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import dao.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jamel_pc
 */
public class UserConnectionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button btnconnecter, btnexit;
    @FXML
    TextField txtUsername, txtPassword;
    @FXML
    Label lblWarning;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        btnconnecter.setOnMousePressed(e -> {

            Home home = new Home();
            Stage stage = new Stage();
            UtilisateurUtil utilisateurUtil = new UtilisateurUtil();
            Boolean connect = utilisateurUtil.VerifUsername(txtUsername.getText(), txtPassword.getText());
            UserConnection uc = new UserConnection();
            if (connect) {
                try {
                    home.start(stage);
                    Stage s = (Stage) btnconnecter.getScene().getWindow();
                    s.close();
                } catch (Exception ex) {
                    Logger.getLogger(UserConnectionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                lblWarning.setText("Verifiez vous données");
            }

        });
    }

}
