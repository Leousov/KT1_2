package com.leo.sheets;

import com.leo.CGood;
import com.leo.COrder;
import com.leo.CUser;
import com.leo.service.SOrder;
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

public class InfoOrders {
    public InfoOrders(){
        Stage stage1 = new Stage();
        stage1.setTitle("Заказы");
        GridPane leaf1 = new GridPane();
        Scene scene1 = new Scene(leaf1);
        stage1.setScene(scene1);
        SOrder sOrder = new SOrder();
        ObservableList<COrder> list = sOrder.findAllUsers();
        TableView<COrder> table = new TableView<COrder>(list);
        //table.setPrefSize(250,200);
        TableColumn<COrder, UUID> idColumn = new TableColumn<COrder, UUID>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<COrder, UUID>("id"));
        table.getColumns().add(idColumn);
        TableColumn<COrder, UUID> uidColumn = new TableColumn<COrder, UUID>("UId");
        uidColumn.setCellValueFactory(new PropertyValueFactory<COrder, UUID>("uid"));
        table.getColumns().add(uidColumn);
        TableColumn<COrder, UUID> gidColumn = new TableColumn<COrder, UUID>("GId");
        gidColumn.setCellValueFactory(new PropertyValueFactory<COrder, UUID>("gid"));
        table.getColumns().add(gidColumn);
        TableColumn<COrder, String> dateColumn = new TableColumn<COrder, String>("Date of buy");
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
                    return celldata.getValue().getDate().toString();
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
