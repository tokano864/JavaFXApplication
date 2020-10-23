// Exercise 08: Ticket Machine
package ex08;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicketMachine extends Application {

    public static String gakuban = "18EC025"; // 学籍番号を入力すること
    public static String yourname = "岡野　友美"; // 氏名を入力すること
    

    Scene scene;
    TextField tf;
    ToggleButton[] tb = new ToggleButton[5];
    ComboBox<String> combo;
    ToggleGroup group = new ToggleGroup();
    ToggleButton button;
    public void setToggleButtons() {
        group.getToggles().addAll(tb);
    }

    public void calculation() {
    	button = (ToggleButton)group.getSelectedToggle();
    	if(button==null)
    		tf.setText("");
    	else {
    		int total = Integer.parseInt(button.getText());
    		total *= Integer.parseInt(combo.getValue());
    		tf.setText(Integer.toString(total));
    	}
    }

    public void createScene() {
    	
    	// Create the label
    	Label Header = new Label("券売機");
    	Label Calc = new Label("金額");
    	Label lbn = new Label("枚数");
    	tb[0] = new ToggleButton("170");
    	tb[1] = new ToggleButton("200");
    	tb[2] = new ToggleButton("250");
    	tb[3] = new ToggleButton("290");
    	tb[4] = new ToggleButton("320");
    	tf = new TextField("");
    	tf.setEditable(false);
    	Button reset = new Button("リセット");
    	combo = new ComboBox<>();
    	combo.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    	combo.setValue("1");
    	
    	// Create the separators
        Separator sepH = new Separator();
        Separator sepV = new Separator(Orientation.VERTICAL);
        
        //Set the Action
        for(int s=0;s<5;s++) {
        	 tb[s].setOnAction(e -> calculation());
        }
        combo.setOnAction(e->calculation());
        reset.setOnAction(e->{
        	button.setSelected(false);
        	combo.setValue("1");
        	calculation();
        });
        
    	//Create the Boxes and pane
        VBox vbox = new VBox(20, Header);
        HBox hbox0 = new HBox(20, tb[0],tb[1],tb[2]);
        HBox hbox01 = new HBox(20, tb[3],tb[4]);
        HBox hcombo = new HBox(40,lbn,combo,sepV);
        VBox vbox0 = new VBox(20,hbox0, hbox01);
    	HBox hbox1 = new HBox(10, Calc, tf, reset);
    	VBox vbox1 = new VBox(10,sepH, hbox1);
    	BorderPane pane = new BorderPane();
    	pane.setTop(vbox);
    	pane.setCenter(vbox0);
    	pane.setLeft(hcombo);
    	pane.setBottom(vbox1);
    	vbox.setAlignment(Pos.CENTER);
    	vbox1.setAlignment(Pos.TOP_CENTER);
    	hcombo.setAlignment(Pos.BASELINE_CENTER);
    	vbox0.setPadding(new Insets(20));
    	hcombo.setPadding(new Insets(10,20,20,15));
    	hbox1.setPadding(new Insets(5,40,20,60));
    	hcombo.setAlignment(Pos.CENTER);
      	scene = new Scene(pane, 450, 250);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	// Create the Scene
        createScene();

        // Set the ToggleButtons
        setToggleButtons();

        // Set the Caluculate Button
        calculation();
        
        // Show the Scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ticket Machine");
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println(gakuban + " " + yourname);
        Application.launch(args);
        System.out.println("完了--TicketMachine");
    }

}

/* 検証 -- 動作確認テストの内容と方法を記述
まずプログラムを実行し、テキストフィールドが空白、人数が1になっているか確認する。
次にトグルボタン、コンボボックス、計算が正常に動作しているか確認するため、人数を１～10まで順に変更し、トグルボタンを押し、テキストフィールドに正しい計算値が表示されるか、確認する。トグルボタンは5つすべてで行う。また、これを行うことによりトグルボタンを押したと同時、そして枚数を変更したと同時に計算が行われることも確認できる。
また、リセットボタンが機能しているか確認するため、1以外の人数、トグルボタンを適当に押し、テキストフィールドに数値が表記されている状態で、リセットボタンを押し、人数が1、テキストフィールドが空白になることを確認する。
 */

/* 考察 -- 調査したこと、考慮したこと、工夫したことを記述
プログラムがわかりやすいようにトグルボタンのグループ化、計算、レイアウトに分けてメソッドを作成した。
Paddingの調整やseparatorを使用し、見やすいレイアウトにした。また、どこに何があるかわかりやすいようにBorderpaneを使用した。
トグルボタンを配列にしたり、計算メソッドでボタンに表記された値を読み込んで計算を行うことによってプログラムを簡潔にした。また、これにより1枚の券の金額を変える際にはトグルボタンの名前を変えるだけで正常に作動するようになる。
コンボボックスでの項目を配列で表すことができれば人数の上限、下限の変更も簡単に行われるが、調べても分からなかったため、今回は1～10までの文字列を選択項目に追加した。
今回は計算を表示するだけなのでテキストフィールドの変更ができないように設定した。
 */
