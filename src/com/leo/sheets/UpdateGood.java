package com.leo.sheets;

import com.leo.CGood;
import com.leo.service.SGood;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.UUID;

public class UpdateGood {
    SGood sGood = new SGood();
    public UpdateGood(){
        Stage stage = new Stage();
        stage.setTitle("Редактирование товара");
        GridPane leaf = new GridPane();
        Scene scene = new Scene(leaf);
        stage.setScene(scene);
        CGood good = new CGood();
        Label label = new Label("Введите id нужного товара");
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
                CGood good1 = sGood.findbyid(UUID.fromString(textField.getText()));
                good.setId(good1.getId());
                good.setName(good1.getName());
                good.setPrice(good1.getPrice());
                good.setCategory(good1.getCategory());
                textField1.setEditable(true);
                textField1.setText(good.getId().toString());
                textField2.setEditable(true);
                textField2.setText(good.getName());
                textField3.setEditable(true);
                textField3.setText(String.valueOf(good.getPrice()));
                textField4.setEditable(true);
                textField4.setText(String.valueOf(good.getCategory()));
                button1.setDisable(false);
                button2.setDisable(false);
                button3.setDisable(false);
            }
        });
        button1.setOnAction(e ->{
            sGood.UpdateUser(new CGood(UUID.fromString(textField1.getText()), textField2.getText(),
                    Double.parseDouble(textField3.getText()), textField4.getText()));
        });
        button2.setOnAction(e ->{
            stage.close();
        });
        button3.setOnAction(e ->{
            sGood.DeleteUser(good);
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
