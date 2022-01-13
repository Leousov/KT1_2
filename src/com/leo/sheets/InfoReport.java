package com.leo.sheets;

import com.leo.CGood;
import com.leo.CUser;
import com.leo.service.SReport;
import com.leo.service.SUser;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public class InfoReport {
    public InfoReport(){
        Stage stage1 = new Stage();
        stage1.setTitle("Отчет");
        GridPane leaf1 = new GridPane();
        Scene scene1 = new Scene(leaf1);
        stage1.setScene(scene1);
        SReport sReport = new SReport();
        ObservableList<CGood> list = sReport.Report();
        TableView<CGood> table = new TableView<CGood>(list);
        //table.setPrefSize(250,200);
        TableColumn<CGood, String> nameColumn = new TableColumn<CGood, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<CGood, String>("name"));
        table.getColumns().add(nameColumn);
        TableColumn<CGood, Double> priceColumn = new TableColumn<CGood, Double>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<CGood, Double>("price"));
        table.getColumns().add(priceColumn);
        TableColumn<CGood, String> categoryColumn = new TableColumn<CGood, String>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<CGood, String>("category"));
        table.getColumns().add(categoryColumn);
        leaf1.setAlignment(Pos.BASELINE_CENTER);
        leaf1.setHgrow(table, Priority.ALWAYS);
        leaf1.setVgrow(table, Priority.ALWAYS);
        leaf1.add(table,0,0);
        stage1.show();
        stage1.alwaysOnTopProperty();
    }
}
