package com.leo.sheets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdateSheet {
    public UpdateSheet(){
        Stage stage = new Stage();
        stage.setTitle("База данных");
        GridPane leaf = new GridPane();
        Label label1 = new Label("Редактировать объект из таблицы 'Пользователи'");
        Label label2 = new Label("Редактировать объект из таблицы 'Товары'");
        Label label3 = new Label("Редактировать объект из таблицы 'Заказы'");
        Button button1 = new Button("Посмотреть");
        Button button2 = new Button("Посмотреть");
        Button button3 = new Button("Посмотреть");
        button1.setOnAction(e ->{
            UpdateUser updateUser = new UpdateUser();
        });
        button2.setOnAction(e ->{
            UpdateGood updateGood = new UpdateGood();
        });
        button3.setOnAction(e ->{
            UpdateOrder updateOrder = new UpdateOrder();
        });

        leaf.add(label1,0,0);
        leaf.add(label2,0,1);
        leaf.add(label3,0,2);
        leaf.add(button1,1,0);
        leaf.add(button2,1,1);
        leaf.add(button3,1,2);
        Scene scene = new Scene(leaf);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
