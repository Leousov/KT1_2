package com.leo;

import com.leo.config.CConfigHibernate;
import com.leo.service.SGood;
import com.leo.service.SOrder;
import com.leo.service.SUser;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.hibernate.Session;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends Application {
    private static final ArrayList<CUser> usermas = new ArrayList<>();
    private static final ArrayList<CGood> goodmas = new ArrayList<>();
    private static final ArrayList<COrder> ordermas = new ArrayList<>();
    private static void loading(){
        File f = new File("D:\\java\\files","Magazin.xlsx");
        Lusermas(f);
        Lgoodmas(f);
        Lordermas(f);
    }
    private static void Lusermas(File file){
        try (XSSFWorkbook wb = new XSSFWorkbook(file)){
            Sheet sheet = wb.getSheet("Пользователи");
            Integer rows = sheet.getLastRowNum();
            Row row;
            String login, name;
            LocalDate datebirth;
            UUID id;
            boolean gender = false;
            for (int i = 1; i<=rows; i++){
                row = sheet.getRow(i);
                if (row == null){
                    continue;
                }
                id = UUID.fromString(row.getCell(0).getStringCellValue());
                login = row.getCell(1).getStringCellValue();
                name = row.getCell(2).getStringCellValue();
                if (row.getCell(3).getStringCellValue().equals("м")){
                    gender = true;}
                datebirth = row.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                usermas.add(new CUser(id,login,name,gender,datebirth));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void Lgoodmas(File file){
        try (XSSFWorkbook wb = new XSSFWorkbook(file)){
            Sheet sheet = wb.getSheet("Товары");
            Integer rows = sheet.getLastRowNum();
            Row row;
            String category, name;
            double price;
            UUID id;
            for (int i = 1; i<rows; i++){
                row = sheet.getRow(i);
                if (row == null){
                    continue;
                }
                id = UUID.fromString(row.getCell(0).getStringCellValue());
                name = row.getCell(1).getStringCellValue();
                price = row.getCell(2).getNumericCellValue();
                category = row.getCell(3).getStringCellValue();
                goodmas.add(new CGood(id,name,price,category));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void Lordermas(File file){
        try (XSSFWorkbook wb = new XSSFWorkbook(file)){
            Sheet sheet = wb.getSheet("Покупки");
            Integer rows = sheet.getLastRowNum();
            Row row;
            LocalDate date;
            UUID id1, id2;
            for (int i = 1; i<rows; i++){
                row = sheet.getRow(i);
                if (row == null){
                    continue;
                }
                if (row.getCell(0).getStringCellValue().equals("")){
                    continue;
                }
                id1 = UUID.fromString(row.getCell(0).getStringCellValue());
                id2 = UUID.fromString(row.getCell(1).getStringCellValue());
                date = row.getCell(2).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                ordermas.add(new COrder(id1, id2, date));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void OrderReport(){
    int Ordnumb = ordermas.size();
    ArrayList<CGood> ManGoods = new ArrayList<>();
    int Usernumb = usermas.size();
    for (int i = 0; i < Ordnumb; i++){
        for (int j = 0; j < Usernumb; j++){
            if ((ordermas.get(i).getUid().equals(usermas.get(j).getId())) && (usermas.get(j).getGender())){
                for (int h = 0; h < goodmas.size(); h++){
                    if (ordermas.get(i).getGid().equals(goodmas.get(h).getId())){
                        if (!ManGoods.contains(goodmas.get(h))) {
                            ManGoods.add(goodmas.get(h));
                        }
                    }
                }
            }
        }
    }
    WriteOrder(ManGoods);
    }
    private static void WriteOrder(ArrayList<CGood> manGoods) {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun run1 = paragraph1.createRun();
        paragraph1.setAlignment(ParagraphAlignment.CENTER);
        paragraph1.setVerticalAlignment(TextAlignment.TOP);
        run1.setBold(true);
        run1.setFontFamily("TimesNewRoman");
        run1.setFontSize(16);
        run1.setUnderline(UnderlinePatterns.THICK);
        run1.setText("Отчет по продажам товаров.");
        XWPFParagraph paragraph2 = document.createParagraph();
        XWPFRun run2 = paragraph2.createRun();
        paragraph2.setAlignment(ParagraphAlignment.LEFT);
        paragraph2.setVerticalAlignment(TextAlignment.AUTO);
        run2.setFontSize(14);
        run2.setFontFamily("TimesNewRoman");
        run2.setText("Товары, покупаемые мужчинами" );
        XWPFTable table = document.createTable();
        table.setWidth("100%");
        XWPFTableRow row = table.getRow(0);
        row.getCell(0).setText("Наименование товара");
        row.addNewTableCell().setText("Категория");
        row.addNewTableCell().setText("Стоимость");
        for (CGood good: manGoods){
            row = table.createRow();
            row.getCell(0).setText(good.getName());
            row.getCell(1).setText(good.getCategory());
            row.getCell(2).setText(String.valueOf(good.getPrice()));
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream("D:\\java\\files\\Report.docx")) {
            document.write(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void LoadFromXLSX(){
        loading();
        /*OrderReport();*/
        try {Session session = CConfigHibernate.getSessionFactory().openSession();
            session.beginTransaction();
            /*CUser user1 = new CUser();
            user1.setLogin("awa");
            user1.setGender(false);
            user1.setName("Lol");
            user1.setDateBirth(LocalDate.now());
            session.save(user1);*/
            for (int i = 0; i < usermas.size(); i++){
                session.save(usermas.get(i));
            }
            for (int i = 0; i < goodmas.size(); i++){
                session.save(goodmas.get(i));
            }
            for (int i = 0; i < ordermas.size(); i++){
                session.save(ordermas.get(i));
            }

            session.getTransaction().commit();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Application.launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Leo's Application");
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);
        Button button1 = new Button("Загрузить");
        Label label1 = new Label("Загрузить данные в базу данных из XLSX файла");
        button1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                button1.setText("Загрузка");
                LoadFromXLSX();
                button1.setText("Загрузить");
            }
        });
        Label label2 = new Label("Смотреть\\Редактировать базу данных");
        Button button2 = new Button("Открыть");
        button2.setOnAction( e -> {
            LookUp();
        });
        GridPane root = new GridPane();
        root.add(label1, 0, 0);
        root.add(button1, 1, 0);
        root.add(label2,0, 1);
        root.add(button2,1, 1);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

        primaryStage.show();
    }
    public void LookUp(){
        Stage stage = new Stage();
        stage.setTitle("База данных");
        GridPane leaf = new GridPane();
        Label label1 = new Label("Посмотреть таблицу 'Пользователи'");
        Label label2 = new Label("Посмотреть таблицу 'Товары'");
        Label label3 = new Label("Посмотреть таблицу 'Заказы'");
        Label label4 = new Label("Посмотреть отчет по продажам среди мужчин");
        Button button1 = new Button("Посмотреть");
        Button button2 = new Button("Посмотреть");
        Button button3 = new Button("Посмотреть");
        Button button4 = new Button("Посмотреть");
        button1.setOnAction(e ->{
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
        });
        button2.setOnAction(e ->{
            Stage stage1 = new Stage();
            stage1.setTitle("Товары");
            GridPane leaf1 = new GridPane();
            Scene scene1 = new Scene(leaf1);
            stage1.setScene(scene1);
            SGood sGood = new SGood();
            ObservableList<CGood> list = sGood.findAllUsers();
            TableView<CGood> table = new TableView<CGood>(list);
            //table.setPrefSize(250,200);
            TableColumn<CGood, UUID> idColumn = new TableColumn<CGood, UUID>("Id");
            idColumn.setCellValueFactory(new PropertyValueFactory<CGood, UUID>("id"));
            table.getColumns().add(idColumn);
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
        });
        button3.setOnAction(e ->{
            Stage stage1 = new Stage();
            stage1.setTitle("Заказы");
            GridPane leaf1 = new GridPane();
            Scene scene1 = new Scene(leaf1);
            stage1.setScene(scene1);
            SOrder sOrder = new SOrder();
            ObservableList<COrder> list = sOrder.findAllUsers();
            TableView<COrder> table = new TableView<COrder>(list);
            //table.setPrefSize(250,200);
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
        });
        button4.setOnAction(e ->{

        });
        leaf.add(label1,0,0);
        leaf.add(label2,0,1);
        leaf.add(label3,0,2);
        leaf.add(label4,0,3);
        leaf.add(button1,1,0);
        leaf.add(button2,1,1);
        leaf.add(button3,1,2);
        leaf.add(button4,1,3);
        Scene scene = new Scene(leaf);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    private void UserView(){
        try {Session session = CConfigHibernate.getSessionFactory().openSession();
            session.beginTransaction();

            for (int i = 0; i < usermas.size(); i++){
                session.save(usermas.get(i));
            }
            for (int i = 0; i < goodmas.size(); i++){
                session.save(goodmas.get(i));
            }
            for (int i = 0; i < ordermas.size(); i++){
                session.save(ordermas.get(i));
            }

            session.getTransaction().commit();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
