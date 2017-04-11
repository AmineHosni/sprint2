/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 *
 * @author jamel_pc
 */
public class PieChartSample {
    public void inserData(Stage stage,Double nouveau,Double Occasion){
         Scene scene = new Scene(new Group());
        stage.setTitle("Produit Nouveau/Occasion");
        stage.setWidth(500);
        stage.setHeight(500);
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Nouveau", nouveau),
                new PieChart.Data("Occasion", Occasion)
                
                );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
}
