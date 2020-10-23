// Exercise 4: Express Counter Application
package ex04;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ExCounter extends Application {



    
    TextField textField = new TextField("0");
    @Override
    public void start(Stage primaryStage) {
    	//create scene1
    	Button button1 = new Button("1");
    	Button button2 = new Button("10");
    	Button button3 = new Button("100");
    	Button button4 = new Button("1000");
    	Button button5 = new Button("Clear");
    	Button button6 = new Button("End");
    	textField.setAlignment(Pos.BASELINE_RIGHT);
    	HBox pane = new HBox(10,textField , button1, button2, button3 , button4 , button5 , button6);
    	pane.setAlignment(Pos.CENTER);
    	Scene scene = new Scene(pane, 550, 100);
    	
    	//register button action
    	button1.setOnAction(e->add(1));
    	button2.setOnAction(e->add(10));
    	button3.setOnAction(e->add(100));
    	button4.setOnAction(e->add(1000));
    	button5.setOnAction(e->textField.setText("0"));
    	button6.setOnAction(e->{
    		e.consume();
    		primaryStage.close();
    	});
    	
    	 
    	//add the scene to the stage,set the title andshow the stage
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("ExCounterApp");
    	primaryStage.show();
    }
    void add(int x) {
    	String number=textField.getText().trim();
    	//テキストフィールド内の値が空文字、5桁以上、文字が入っているかの判別
    	if (number.length()==0||number.length()>4||!number.matches("^^[0-9]*$") ){ 
    		Alert alert = new Alert(Alert.AlertType.ERROR,
                    "5桁以上の整数または整数以外の文字がテキストに記載されているか、テキスト内に値がありません\n初期化するか処理を終了してください",
    				ButtonType.CLOSE);
            
    		alert.showAndWait();
    	}
    	int sum=Integer.parseInt(textField.getText().trim());
    	if((x+sum)<10000)
    		textField.setText(String.valueOf(x+sum));
    	else {
    		Alert alert = new Alert(Alert.AlertType.ERROR,
                    "テキスト内の値が9999を超えます\n初期化するか、処理を終了してください");
            alert.showAndWait();
    	}
    }
    
   
    
    public static void main(String[] args) {
    	System.out.println(gakuban + " " + yourname);
        Application.launch(args);
        System.out.println("完了--ExCounterApp");
    }

}
