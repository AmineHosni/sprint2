/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.magasin;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import entities.Magasin;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.jasperreports.engine.JRException;
import services.FtpUtil;

/**
 * FXML Controller class
 *
 * @author AmineHosni
 */
public class FXMLDetailsController implements Initializable, MapComponentInitializedListener {

    /**
     * Initializes the controller class.
     */
    public static Magasin magasin;
    @FXML
    private Label name;
    @FXML
    private ImageView img;
    @FXML
    private Label address;
    @FXML
    private Label owner;
    @FXML
    private GoogleMapView mapView;

    private Image image;
    private GoogleMap map;
    private Marker marker;
    private InputStream is;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mapView.addMapInializedListener(this);
            name.setText(magasin.getName());
            owner.setText(magasin.getOwner());
            address.setText(magasin.getAddress());

            
            //String path = "http://localhost/CodeNameOne/"+magasin.getImage_name();
            System.out.println(magasin.getImage_name());
            is = FtpUtil.getFile(magasin.getImage_name());
            image = new Image(is);
            img.setImage(image);
        } catch (IOException ex) {
            //Logger.getLogger(FXMLDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //img.snapshot(new SnapshotParameters(),new WritableImage(1, 1));
    @Override
    public void mapInitialized() {

        Double lat = Double.valueOf(magasin.getAltitude());
        Double lng = Double.valueOf(magasin.getLongitude());

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        LatLong latlng = new LatLong(lat, lng);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.animation(Animation.DROP);

        marker = new Marker(markerOptions);

        mapOptions.center(latlng)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(14);

        map = mapView.createMap(mapOptions);

        map.addMarker(marker);

    }

    @FXML
    void exportPDF(ActionEvent event) {
        String query = "SELECT * from magasin where name = '" + magasin.getName() + "'";
        try {
            pdfFromXmlFile.CreateMagasin(query);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("");
            alert.setContentText("Your PDF file has been created !");

            alert.showAndWait();
        } catch (JRException ex) {
            Logger.getLogger(FXMLDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
