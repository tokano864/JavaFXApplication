// Exercise 3-2: AddTax Application
package ex03;

import java.util.function.LongUnaryOperator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddTaxApp extends Application {

    public static String gakuban = "18EC025"; // 学籍番号を入力すること
    public static String yourname = "岡野友美"; // 氏名を入力すること

    // 税込み価格を計算するラムダ式
    public static LongUnaryOperator addTax
        // 課題3-1のコードを記述
    =(long price)->(long)(price*1.1)/10*10;

    @Override
    public void start(Stage primaryStage) {
        // 課題3-2のコードを記述
    	//ラベル、ボタン、テキストフィールドの作成
    	Label label=new Label("税抜き価格");
    	Button button=new Button("税込み価格に変換する");
    	TextField textField_1=new TextField();
    	TextField textField_2=new TextField();
    	
    	//ボタンのアクション登録
    	button.setOnAction((ActionEvent event)->{
    		// テキストフィールド1の値を取得する 
    		String tf1 = textField_1.getText().trim(); 
    		// 値をチェックする 
    		if (!tf1.isEmpty() && tf1.matches("^[0-9]*$")) { 
    			// OKならば、値を1.10倍してテキストフィールド2に設定する 
    			long price = Long.parseLong(tf1); 
    			long includeTax = addTax.applyAsLong(price); 
    			label.setText("税抜き価格"); 
    			textField_2.setText(String.format("%,d", includeTax));
    		}
    	     else { // NGならば、正しい値を入れるように促す 
    		label.setText("正しい税抜き価格を入力せよ"); 
    		textField_2.setText("0"); 
    		}
    	}); 
    	
    	//ペインの作成およびコントロールの配置
    	VBox pane=new VBox(label,textField_1,button,textField_2);
    	//シーン作成およびペインへの挿入
    	 Scene scene = new Scene(pane, 300,150);
    	//ペインにステージを入れ、タイトルの設定を行う
    	 primaryStage.setScene(scene);
    	 primaryStage.setTitle("AddTaxSystem");
    	 //ステージの表示
        primaryStage.show();
    }

    public static void main(String[] args) {
        // アプリケーションを起動する
        Application.launch(args);
        System.out.println("完了--AddTaxApp");
    }

}
