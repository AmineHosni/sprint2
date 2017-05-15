/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.magasin;

import entities.Magasin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import services.MagasinService;

/**
 * FXML Controller class
 *
 * @author AmineHosni
 */
public class FXMLHomeController implements Initializable {

    @FXML
    private JFXButton addStore;
    @FXML
    private JFXButton removeStore;
    @FXML
    private JFXTextField search;
    @FXML
    private TableView<Magasin> table;

    @FXML
    private StackPane stackpane;

    @FXML
    private JFXButton detailsButton;
    List<Magasin> magasins = new ArrayList<Magasin>();
    /**
     * Initializes the controller class.
     */
    MagasinService service = new MagasinService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        magasins = service.afficherMagasins();
        initTableView();

    }

    @FXML
    private void AddStoreButtonAction(ActionEvent event) {
        try {
            // try {
            /*JFXDialog dialog=  new JFXDialog();
            dialog.setDialogContainer(stackpane);
            AnchorPane root = FXMLLoader.load(getClass().getResource("FXMLAdd.fxml"));
            dialog.setContent(root);
            
            dialog.show();*/
            //} catch (IOException ex) {
            //     Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            //}
            AddMain popup = new AddMain();
            popup.start(new Stage());

        } catch (Exception ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RemoveStoreButtonAction(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Magasin m = table.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Magasin ?");
            alert.setHeaderText("You're about to delete " + m.getName());
            alert.setContentText("Are you sure you want to delete this shop ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK
                service.remove(m);
                magasins.remove(m);
                update();
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        }

    }

    public void update() {
        table.getItems().setAll(magasins);
    }

    @FXML
    private void SearchAction(KeyEvent e) {
        List<Magasin> newList = magasins.stream().filter((mag) -> {
            boolean addressMatched = mag.getAddress().toLowerCase().contains(search.getText().toLowerCase());
            boolean nameMatched = mag.getName().toLowerCase().contains(search.getText().toLowerCase());
            boolean ownerMatched = mag.getOwner().toLowerCase().contains(search.getText().toLowerCase());
            boolean descriptionMatched = mag.getDescription().toLowerCase().contains(search.getText().toLowerCase());
            return (addressMatched || nameMatched || ownerMatched || descriptionMatched);
        }).collect(Collectors.toList());
        table.getItems().setAll(newList);
    }

    public void initTableView() {
        TableColumn<Magasin, String> magasinName = new TableColumn<Magasin, String>("Name");
        magasinName.setCellValueFactory((param) -> {
            return new SimpleStringProperty(param.getValue().getName());
        });
        TableColumn<Magasin, String> magasinOwner = new TableColumn<Magasin, String>("Owner");
        magasinOwner.setCellValueFactory((param) -> {
            return new SimpleStringProperty(param.getValue().getOwner());
        });
        TableColumn<Magasin, String> magasinDescription = new TableColumn<Magasin, String>("Description");
        magasinDescription.setCellValueFactory((param) -> {
            return new SimpleStringProperty(param.getValue().getDescription());
        });
        TableColumn<Magasin, String> magasinAddress = new TableColumn<Magasin, String>("Address");
        magasinAddress.setCellValueFactory((param) -> {
            return new SimpleStringProperty(param.getValue().getAddress());
        });
        magasinAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        magasinOwner.setCellFactory(TextFieldTableCell.forTableColumn());
        magasinDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        magasinName.setCellFactory(TextFieldTableCell.forTableColumn());

        table.getColumns().add(magasinName);
        table.getColumns().add(magasinOwner);
        table.getColumns().add(magasinDescription);
        table.getColumns().add(magasinAddress);
        table.getItems().setAll(magasins);
        table.setEditable(true);

        magasinAddress.setOnEditCommit((e) -> {
            Magasin m = table.getSelectionModel().getSelectedItem();
            m.setAddress(e.getNewValue());
            service.update(m);
        });
        magasinOwner.setOnEditCommit((e) -> {
            Magasin m = table.getSelectionModel().getSelectedItem();
            m.setOwner(e.getNewValue());
            service.update(m);
        });
        magasinDescription.setOnEditCommit((e) -> {
            Magasin m = table.getSelectionModel().getSelectedItem();
            m.setDescription(e.getNewValue());
            service.update(m);
        });
        magasinName.setOnEditCommit((e) -> {
            Magasin m = table.getSelectionModel().getSelectedItem();
            m.setName(e.getNewValue());
            service.update(m);
        });

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, (t) -> {
            detailsButton.setVisible(true);
        });

    }

    @FXML
    void viewDetails(ActionEvent event) {
        // System.out.println(table.getSelectionModel().getSelectedItem().getImage_name());
        try {
            FXMLDetailsController.magasin = table.getSelectionModel().getSelectedItem();
            DetailsMain m = new DetailsMain();
            Stage stage = new Stage();
            m.start(stage);
            Stage s = (Stage) removeStore.getScene().getWindow();
            s.close();

        } catch (Exception ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void exportPDF(ActionEvent event) {

        String query = "Select * from magasin";
        try {
            pdfFromXmlFile.CreateMagasin(query);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("");
            alert.setContentText("Your PDF file has been created !");

            alert.showAndWait();
        } catch (JRException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void updateTable(ActionEvent event) {
        magasins = service.afficherMagasins();
        update();
    }
}
