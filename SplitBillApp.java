package ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SplitBillApp extends Application {


	Label lb1, lb2, lb3, lb4, lb5;
	TextField tf1, tf2, tf3, tf4, tf5;
	Button btn;
	VBox vb;
	Alert alert;

	@Override
	public void start(Stage primaryStage) {
		// 入力領域
		lb1 = new Label("前回からの繰越額");
		lb1.setPrefWidth(100);
		lb1.setAlignment(Pos.BASELINE_RIGHT);
		tf1 = new TextField("");
		lb2 = new Label("全体の支払い額");
		lb2.setPrefWidth(100);
		lb2.setAlignment(Pos.BASELINE_RIGHT);
		tf2 = new TextField("0");
		lb3 = new Label("人数");
		lb3.setPrefWidth(100);
		lb3.setAlignment(Pos.BASELINE_RIGHT);
		tf3 = new TextField("1");

		// ボタン領域
		btn = new Button("計算");
		btn.setOnAction(ae -> btnClicked()); // 課題6-2でこのメソッドを実装
		btn.setPrefWidth(150);

		// 出力領域
		lb4 = new Label("各人の支払い額");
		lb4.setPrefWidth(100);
		lb4.setAlignment(Pos.BASELINE_RIGHT);
		tf4 = new TextField();
		lb5 = new Label("次回への繰越額");
		lb5.setPrefWidth(100);
		lb5.setAlignment(Pos.BASELINE_RIGHT);
		tf5 = new TextField();

		// レイアウト
		makeLayout(); // 課題6-1でこのメソッドを実装

		// レイアウト表示
		Scene scene = new Scene(vb);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Split Bill Application");
		primaryStage.show();
	}

	// レイアウトの作成
	public void makeLayout() {
		// 課題6-1のコードを記述
		HBox hb1 = new HBox(30, lb1 , tf1);
		HBox hb2 = new HBox(30, lb2 , tf2);
		HBox hb3 = new HBox(30, lb3 , tf3);
		HBox hb4 = new HBox(btn);
		hb4.setAlignment(Pos.CENTER);
		HBox hb5 = new HBox(30, lb4 , tf4);
		tf4.setEditable(false);
		HBox hb6 = new HBox(30, lb5 , tf5);
		tf5.setEditable(false);
		lb1.setPrefWidth(112);
		vb = new VBox(10,hb1 , hb2 , hb3 , hb4 , hb5 , hb6); // 不要ならばこの行を削除
	}

	// 計算ボタンのアクション
	public void btnClicked() {
		// 課題6-2のコードを記述
		String prebefore = tf1.getText();
		String pretotal = tf2.getText();
		String pren = tf3.getText();
		if(prebefore.length()==0)
			prebefore = "0";
		
		try {
			
			int before=Integer.parseInt(prebefore);
			int total = Integer.parseInt(pretotal);
			int n = Integer.parseInt(pren);
			if(before<0) 
				alert = new Alert(AlertType.ERROR,"入力に誤りがあります");
			if(total<0) 
				alert = new Alert(AlertType.ERROR,"入力に誤りがあります");
			if(n<0) 
				alert = new Alert(AlertType.ERROR,"入力に誤りがあります");
			
			int pay = total - before;
			int preach=pay / n;
			if((preach % 100) != 0) 
				preach += 100;
			int each = preach /100 * 100;
			if(pay < 0)
				each = 0;
			int after= (n * each) + before - total;
			if(after < 0 )
				after = 0;
			tf4.setText(Integer.toString(each));
			tf5.setText(Integer.toString(after));
			
			
		
		}catch (NumberFormatException e) {			
			alert = new Alert(AlertType.ERROR,"入力に誤りがあります");
		}catch (ArithmeticException e) {
			alert = new Alert(AlertType.ERROR,"入力に誤りがあります");
		}

	}

	public static void main(String[] args) {
		// アプリケーションを起動する
		Application.launch(args);
		System.out.println("完了--SplitBillApp");
	}

}
