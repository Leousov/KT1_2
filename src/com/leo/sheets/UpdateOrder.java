package com.leo.sheets;

import com.leo.COrder;
import com.leo.service.SOrder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.UUID;

public class UpdateOrder {
    SOrder sOrder = new SOrder();
    public UpdateOrder(){
        Stage stage = new Stage();
        stage.setTitle("Редактирование заказа");
        GridPane leaf = new GridPane();
        Scene scene = new Scene(leaf);
        stage.setScene(scene);
        COrder cOrder = new COrder();
        Label label = new Label("Введите id заказа");
        TextField textField = new TextField();
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        textField1.setEditable(false);
        textField2.setEditable(false);
        textField3.setEditable(false);
        textField4.setEditable(false);
        Button button = new Button("Ввод");
        Button button1 = new Button("Внести изменения");
        button1.setDisable(true);
        Button button2 = new Button("Отмена");
        button2.setDisable(true);
        Button button3 = new Button("Удалить");
        button3.setDisable(true);
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                COrder cOrder1 = sOrder.findbyid(UUID.fromString(textField.getText()));
                cOrder.id = cOrder1.id;
                cOrder.setUid(cOrder1.getUid());
                cOrder.setGid(cOrder1.getGid());
                cOrder.setDate(cOrder1.getDate());
                textField1.setEditable(true);
                textField1.setText(cOrder.id.toString());
                textField2.setEditable(true);
                textField2.setText(cOrder.getUid().toString());
                textField3.setEditable(true);
                textField3.setText(cOrder.getGid().toString());
                textField4.setEditable(true);
                textField4.setText(cOrder.getDate().toString());
                button1.setDisable(false);
                button2.setDisable(false);
                button3.setDisable(false);
            }
        });
        button1.setOnAction(e ->{
            sOrder.UpdateOrder(new COrder(UUID.fromString(textField1.getText()), UUID.fromString(textField2.getText()),
                    UUID.fromString(textField3.getText()), LocalDate.parse(textField4.getText())));
        });
        button2.setOnAction(e ->{
            stage.close();
        });
        button3.setOnAction(e ->{
            sOrder.DeleteOrder(cOrder);
        });
        leaf.add(label, 0,0);
        leaf.add(textField, 1,0);
        leaf.add(button, 2,0);
        leaf.add(textField1, 0,1);
        leaf.add(textField2, 1,1);
        leaf.add(textField3, 2,1);
        leaf.add(textField4, 3,1);
        leaf.add(button1, 0,2);
        leaf.add(button2, 1,2);
        leaf.add(button3, 2,2);

        stage.alwaysOnTopProperty();
        stage.show();
    }
}
