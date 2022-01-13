package com.leo.sheets;

import com.leo.CUser;
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

import java.util.UUID;

public class InfoUsers {
    public InfoUsers(){
        Stage stage1 = new Stage();
        stage1.setTitle("Пользователи");
        GridPane leaf1 = new GridPane();
        Scene scene1 = new Scene(leaf1);
        stage1.setScene(scene1);
        SUser sUser = new SUser();
        ObservableList<CUser> list = sUser.findAllUsers();
        TableView<CUser> table = new TableView<CUser>(list);
        //table.setPrefSize(250,200);
        TableColumn<CUser, UUID> idColumn = new TableColumn<CUser, UUID>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<CUser, UUID>("id"));
        table.getColumns().add(idColumn);
        TableColumn<CUser, String> loginColumn = new TableColumn<CUser, String>("Login");
        loginColumn.setCellValueFactory(new PropertyValueFactory<CUser, String>("login"));
        table.getColumns().add(loginColumn);
        TableColumn<CUser, String> nameColumn = new TableColumn<CUser, String>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<CUser, String>("name"));
        table.getColumns().add(nameColumn);
        TableColumn<CUser, Boolean> genderColumn = new TableColumn<CUser, Boolean>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<CUser, Boolean>("gender"));
        table.getColumns().add(genderColumn);
        TableColumn<CUser, String> dateColumn = new TableColumn<CUser, String>("Date of birth");
        //dateColumn.setCellValueFactory(new PropertyValueFactory<CUser, String>("datebirth"));
        dateColumn.setCellValueFactory(celldata -> {
            return new ObservableValue<String>() {
                @Override
                public void addListener(ChangeListener<? super String> changeListener) {

                }

                @Override
                public void removeListener(ChangeListener<? super String> changeListener) {

                }

                @Override
                public String getValue() {
                    return celldata.getValue().getDateBirth().toString();
                }

                @Override
                public void addListener(InvalidationListener invalidationListener) {

                }

                @Override
                public void removeListener(InvalidationListener invalidationListener) {

                }
            } ;
        });
        table.getColumns().add(dateColumn);
        leaf1.setAlignment(Pos.BASELINE_CENTER);
        leaf1.setHgrow(table, Priority.ALWAYS);
        leaf1.setVgrow(table, Priority.ALWAYS);
        leaf1.add(table,0,0);
        stage1.show();
        stage1.alwaysOnTopProperty();
    }
}
