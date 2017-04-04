/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    JFXButton btnconnecter, btnExit;
    @FXML
    TextField txtUsername, txtPassword;
    @FXML
    Label lblWarning;
    Stage stage = new Stage();
    @FXML
    public void connecter() {
        UtilisateurUtil utilisateurUtil = new UtilisateurUtil();
        Boolean connect = utilisateurUtil.VerifUsername(txtUsername.getText(), txtPassword.getText());
        if (connect) {
            try {
                Home home = new Home();
                home.start(stage);
                Stage s = (Stage) btnconnecter.getScene().getWindow();
                lblWarning.setText("Verifiez vous données");
                s.close();
            } catch (Exception ex) {
                Logger.getLogger(UserConnectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            lblWarning.setText("Verifiez vous données");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void close() {
        Stage s = (Stage) btnExit.getScene().getWindow();
        s.close();
    }
}
