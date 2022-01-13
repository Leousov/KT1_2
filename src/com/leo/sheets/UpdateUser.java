package com.leo.sheets;

import com.leo.CUser;
import com.leo.service.SUser;
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

public class UpdateUser {
    SUser sUser = new SUser();
    public UpdateUser(){
        Stage stage = new Stage();
        stage.setTitle("Редактирование пользователя");
        GridPane leaf = new GridPane();
        Scene scene = new Scene(leaf);
        stage.setScene(scene);
        CUser user = new CUser();
        Label label = new Label("Введите id нужного пользователя");
        TextField textField = new TextField();
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();
        textField1.setEditable(false);
        textField2.setEditable(false);
        textField3.setEditable(false);
        textField4.setEditable(false);
        textField5.setEditable(false);
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
                CUser user1 = sUser.findbyid(UUID.fromString(textField.getText()));
                user.setId(user1.id);
                user.setName(user1.getName());
                user.setLogin(user1.getLogin());
                user.setDateBirth(user1.getDateBirth());
                user.setGender(user1.getGender());
                textField1.setEditable(true);
                textField1.setText(user.getId().toString());
                textField2.setEditable(true);
                textField2.setText(user.getLogin());
                textField3.setEditable(true);
                textField3.setText(user.getName());
                textField4.setEditable(true);
                textField4.setText(String.valueOf(user.getGender()));
                textField5.setEditable(true);
                textField5.setText(user.getDateBirth().toString());
                button1.setDisable(false);
                button2.setDisable(false);
                button3.setDisable(false);
            }
        });
        button1.setOnAction(e ->{
            if (Boolean.valueOf(textField4.getText())){
                sUser.UpdateUser(new CUser(UUID.fromString(textField1.getText()), textField2.getText(), textField3.getText(),
                    true, LocalDate.parse(textField5.getText())));
            }
            else {
                sUser.UpdateUser(new CUser(UUID.fromString(textField1.getText()), textField2.getText(), textField3.getText(),
                        false, LocalDate.parse(textField5.getText())));
            }
        });
        button2.setOnAction(e ->{
            stage.close();
        });
        button3.setOnAction(e ->{
            sUser.DeleteUser(user);
        });
        leaf.add(label, 0,0);
        leaf.add(textField, 1,0);
        leaf.add(button, 2,0);
        leaf.add(textField1, 0,1);
        leaf.add(textField2, 1,1);
        leaf.add(textField3, 2,1);
        leaf.add(textField4, 3,1);
        leaf.add(textField5, 4,1);
        leaf.add(button1, 0,2);
        leaf.add(button2, 1,2);
        leaf.add(button3, 2,2);

        stage.alwaysOnTopProperty();
        stage.show();
    }
}
