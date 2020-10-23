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

    public static String gakuban = "18EC025"; // 学籍番号を入力すること
    public static String yourname = "岡野友美"; // 氏名を入力すること

    
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

/* 検証 -- 動作確認テストの内容と方法を記述
まず、1、10、100、1000をボタンを押して加算し、正常に計算できるかを確認する。
正常に作動したら、1、10、100、1000を加算するボタンが正常に作動できていることが確認できる。
次に、値を0に戻すボタンを確認する。
加算ボタン(button1～button4)を数回押し、Clearボタンを押して値が0に戻ることを確認する。次にエラーの確認をする。
最後に1、10、100、1000をそれぞれ加算した後にエラーが起きることが確認できれば、正常にエラー表示が起きることが分かる。
9999に1を、9990に10を、9900に100を、9000に1000を加算し、すべてボタンを押すとエラー表示が起きることを確認する。
最後にEndボタンを押してウィンドウを閉じて処理が終了することを確認する。
 */

/* 考察 -- 調査したこと、考慮したこと、工夫したことを記述
 * 整数0～9999以外が記載されている場合にもエラーを表示するようにした。また、この時のエラーを閉じる際のボタンの名前を調べて変えた。
また、4桁の整数であればキーボードから入力しても計算が正常に行われることが分かった。値を変更不可にしたい場合はラベルを用いるのが適当だと考えられる。
加算する命令をすべて同じメソッドを使うことで、プログラムを単純化させた。
カンマ付き整数にすると、テキストフィールドを整数の型にキャストする際エラーが起きてしまうので、桁数が分かりやすいように右詰めでの表示にした。調べたが、カンマ付き整数を整数に直すメソッドは見つからなかった。整数のみを配列に1度格納してから整数型に変換すれば実現できると考えられるが、プログラムが複雑化するため、このプログラムでは使用しなかった。

 */
 