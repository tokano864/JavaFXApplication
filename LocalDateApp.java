// Exercise 9: Local Date App
package ex09;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LocalDateApp extends Application {

    public static String gakuban = "18EC025"; // 学籍番号を入力すること
    public static String yourname = "岡野　友美"; // 氏名を入力すること

    LocalDate date = LocalDate.now();
    MenuBar menuBar = new MenuBar();
    Label label = new Label();

    @Override
    public void start(Stage primaryStage) {
        // 以下にコードを記入
    	Menu menuFile = new Menu("ファイル");
    	MenuItem today = new MenuItem("今日");
    	today.setOnAction(e -> label.setText(LocalDate.now().toString()));
    	MenuItem exit = new MenuItem("終了");
    	exit.setOnAction(e->primaryStage.close());
    	menuFile.getItems().addAll(today,exit);
    	//Create the Past menu
    	Menu menuPast = new Menu("過去");
    	MenuItem lastmonth = new MenuItem("前月");
    	lastmonth.setOnAction(e ->{
    		date = date.plusMonths(-1);
    		label.setText(date.toString());});	
    	
    	MenuItem yesterday = new MenuItem("前日");
    	yesterday.setOnAction(e -> {
    		date = date.plusDays(-1);
    		label.setText(date.toString());});
    	menuPast.getItems().addAll(lastmonth, yesterday);
    	//Create the Future menu
    	Menu menuFuture = new Menu("未来");
    	MenuItem nextmonth = new MenuItem("翌月");
    	nextmonth.setOnAction(e -> {
    		date = date.plusMonths(1);
    		label.setText(date.toString());});
    	MenuItem tomorrow = new MenuItem("翌日");
    	tomorrow.setOnAction(e -> {
    		date = date.plusDays(1);
    		label.setText(date.toString());});
    	menuFuture.getItems().addAll(nextmonth, tomorrow);
    	
    	//Create the MenuBar
    	menuBar.getMenus().addAll(menuFile, menuPast, menuFuture);
    	
        // Set everything and show on the stage
        label.setText(date.toString());
        label.setFont(new Font(24));
        BorderPane pane = new BorderPane();
        pane.setTop(menuBar);
        pane.setCenter(label);
        primaryStage.setScene(new Scene(pane, 300, 100));
        primaryStage.setTitle("Local Date App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
        System.out.println("完了--LocalDateApp");
    }
}
