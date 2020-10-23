
package ex07;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RamenOrderApp extends Application {


    Scene scene;
    TextField tfCalc;
    Button btnCalc;
    RadioButton rbSoupShio, rbSoupShoyu, rbSoupMiso; // 味: 塩、醤油、味噌
    RadioButton rbSizeM, rbSizeL; // 量: 並盛, 大盛
    CheckBox cbOptCorn, cbOptMemma, cbOptAjitama, cbOptChashu;
    // 追加: コーン、メンマ、味玉、チャーシュー

    public void setRadioButtons() {
        
    	ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(rbSoupShio, rbSoupShoyu, rbSoupMiso);
        ToggleGroup group1 = new ToggleGroup();
        group1.getToggles().addAll(rbSizeM, rbSizeL);
        
        rbSoupShio.setSelected(true);
        rbSizeM.setSelected(true);

    }

    public void calculation() {
     
    	int price = 0;
    	
    	if(rbSoupShio.isSelected())
    		price += 500;
    	if(rbSoupShoyu.isSelected())
    		price += 550;
    	if(rbSoupMiso.isSelected())
    		price += 600;
    	
    	if(rbSizeL.isSelected())
    		price += 200;
    	
    	if(cbOptCorn.isSelected())
    		price += 50;
    	if(cbOptMemma.isSelected())
    		price += 100;
    	if(cbOptAjitama.isSelected())
    		price += 150;
    	if(cbOptChashu.isSelected())
    		price += 200;
    	tfCalc.setText(Integer.toString(price));

    }

    

    public void createScene() {
        // Create the Header
        Label lblHeader = new Label("ラーメン注文票");
        lblHeader.setFont(new Font(20));
        lblHeader.setUnderline(true);
        HBox hbHeader = new HBox(lblHeader);
        hbHeader.setPadding(new Insets(10));
        hbHeader.setAlignment(Pos.CENTER);

        // Create the Soup selecter
        Label lblSoup = new Label("味：");
        lblSoup.setMinWidth(70);
        lblSoup.setAlignment(Pos.CENTER_RIGHT);
        rbSoupShio = new RadioButton("塩 (500)");
        rbSoupShoyu = new RadioButton("醤油 (550)");
        rbSoupMiso = new RadioButton("味噌 (600)");
        HBox hbSoup = new HBox(20, lblSoup, rbSoupShio, rbSoupShoyu, rbSoupMiso);

        // Create the Size selecter
        Label lblSize = new Label("量：");
        lblSize.setMinWidth(70);
        lblSize.setAlignment(Pos.CENTER_RIGHT);
        rbSizeM = new RadioButton("並盛 (+0)");
        rbSizeL = new RadioButton("大盛 (+200)");
        HBox hbSize = new HBox(20, lblSize, rbSizeM, rbSizeL);

        // Create the Option selector
        Label lblOption = new Label("追加：");
        lblOption.setMinWidth(70);
        lblOption.setAlignment(Pos.CENTER_RIGHT);
        cbOptCorn = new CheckBox("コーン (+50)");
        cbOptMemma = new CheckBox("メンマ (+100)");
        cbOptAjitama = new CheckBox("味玉 (+150)");
        cbOptChashu = new CheckBox("チャーシュー (+200)");
        FlowPane fpOption = new FlowPane(20, 10,
                cbOptCorn, cbOptMemma, cbOptAjitama, cbOptChashu);
        HBox hbOption = new HBox(20, lblOption, fpOption);

        // Create the Calculator
        Label lblCalc = new Label("金額：");
        lblCalc.setMinWidth(70);
        lblCalc.setAlignment(Pos.BASELINE_RIGHT);
        tfCalc = new TextField("0");
        tfCalc.setPrefColumnCount(5);
        tfCalc.setAlignment(Pos.CENTER_RIGHT);
        tfCalc.setEditable(false);
        btnCalc = new Button("再計算する");
        btnCalc.setOnAction(e -> calculation());
        HBox hbCalc = new HBox(20, lblCalc, tfCalc, btnCalc);

        // Create the Layout
        VBox vbox = new VBox(30, hbHeader, hbSoup, hbSize, hbOption, hbCalc);
        vbox.setPadding(new Insets(10));
        scene = new Scene(vbox, 450, 350);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the Scene
        createScene();

        // Set the RadioButtons
        setRadioButtons();

        // Set the Caluculate Button
        calculation();

        // Show the Scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ramen Order App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
        System.out.println("完了--RamenOrderApp");
    }

}
