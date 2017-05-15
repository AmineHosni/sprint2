/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.magasin;

import entities.Magasin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;

import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import services.MagasinInterfaceService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import netscape.javascript.JSObject;
import services.FtpUtil;
import java.sql.Timestamp;
import javafx.scene.control.Alert;
import services.MagasinService;

/**
 * FXML Controller class
 *
 * @author AmineHosni
 */
public class FXMLAddController implements Initializable, MapComponentInitializedListener {

    private GoogleMap map;

    private Marker marker;

    @FXML
    private GoogleMapView mapView;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField owner;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXButton addMagasin;
    @FXML
    private Label imageName;
    private Magasin m = new Magasin();
    File image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        adresse.setDisable(true);
    }

    @FXML
    private void PhotoButtonAction(ActionEvent event) {
        FileChooser fileChoser = new FileChooser();
        fileChoser.getExtensionFilters().add(
                
                new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif")
        );
        fileChoser.setInitialDirectory(new File("C:\\Users\\AmineHosni\\Desktop"));
        Node source = (Node) event.getSource();
        Window stage = source.getScene().getWindow();

        image = fileChoser.showOpenDialog(stage);
        if (image != null) {
            int index = (image.getPath().lastIndexOf("\\"));
            imageName.setText(image.getPath().substring(index + 1));
            
        }
    }

    @FXML
    private void AddMagasinAction(ActionEvent event) {

        m.setName(name.getText());
        m.setOwner(owner.getText());
        m.setDescription(description.getText());
        m.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));
        m.setOwner_id(2);
        m.setImage_name(imageName.getText());
        try {
            FtpUtil.storeFile(image, imageName.getText());
        } catch (IOException ex) {
            Logger.getLogger(FXMLAddController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(m.toString());
        MagasinInterfaceService dao = new MagasinService();
        dao.add(m);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("");
        alert.setContentText("Your Store has been added !");

        alert.showAndWait();

    }

    @Override
    public void mapInitialized() {

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(33.8869, 9.5375))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(6);

        map = mapView.createMap(mapOptions);
        //GeocodingService service=  new GeocodingService();

        map.addUIEventHandler(UIEventType.click, (jso) -> {
            JSObject ob = (JSObject) jso.getMember("latLng");
            Double lat = Double.valueOf(ob.call("lat").toString());
            Double lng = Double.valueOf(ob.call("lng").toString());
            GeocodingService s = new GeocodingService();

            s.reverseGeocode(lat, lng, (grs, gs) -> {
                if (gs.equals(gs.OK)) {
                    LatLong clickedLocation = new LatLong(lat, lng);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(clickedLocation);
                    markerOptions.animation(Animation.DROP);
                    m.setAddress(grs[0].getAddressComponents().get(1).getLongName() + ", " + grs[0].getAddressComponents().get(2).getLongName());
                    m.setLongitude(lng.toString());
                    m.setAltitude(lat.toString());
                    adresse.setText(m.getAddress());
                    if (marker != null) {
                        map.removeMarker(marker);
                        marker = new Marker(markerOptions);
                        map.addMarker(marker);
                    } else {
                        marker = new Marker(markerOptions);
                        map.addMarker(marker);

                    }
                    System.out.println(grs[0].getAddressComponents().get(1).getLongName() + ", " + grs[0].getAddressComponents().get(2).getLongName());

                }
            });

        });
    }

}
