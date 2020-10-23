package ex12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RainfallGraphApp extends Application {

    public static String gakuban = "18EC025"; // 学籍番号を入力すること
    public static String yourname = "岡野友美"; // 氏名を入力すること

    private File file;
    private int year;
    private int column;

    public double[] selectData(File csv, int year, int column) {
        /* 以下に課題12-1のコードを記述 */
    	double[] data=new double[12];
    	try {
    		FileInputStream fis = new FileInputStream(file);
    		InputStreamReader isr = new InputStreamReader(fis,"MS932");
    		BufferedReader br = new BufferedReader(isr); 
    		String line = br.readLine();
    		while(line != null) {
    			String[] clmn = line.split(",");
    			if(clmn[0].contentEquals(Integer.toString(year))) {
    				data[Integer.parseInt(clmn[1].trim())-1] = Double.parseDouble(clmn[column].trim());
    				}
    			line = br.readLine();
    		}
    		br.close();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
        return data;
    }

    public PieChart makePieChart(double[] data) {
        /* 以下に課題12-2のコードを記述 */
    	PieChart pieChart = new PieChart();
    	for(int i=0;i<12;i++) {
    		pieChart.getData().add(new PieChart.Data((Integer.toString(i+1)+"月"), data[i]));
    	}
    	pieChart.setTitle("月別降水量");
    	pieChart.setStartAngle(90);
    	pieChart.setClockwise(true);
    	pieChart.setLabelLineLength(20);
        return pieChart; 
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        Button setFile = new Button("1. ファイルを選択");
        setFile.setPrefWidth(120);
        setFile.setAlignment(Pos.CENTER_LEFT);
        setFile.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("ファイルを選択");
            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("CSV files", "*.csv"));
            file = chooser.showOpenDialog(null);
            System.out.println("File: " + file);
            if (file != null) {
                setFile.setText(file.getName());
            }
        });

        ComboBox<Integer> comboYear = new ComboBox<>();
        comboYear.setPromptText("2. 年を選択");
        comboYear.getItems().setAll(
                2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019);
        comboYear.setPrefWidth(120);
        comboYear.setOnAction(e -> {
            year = comboYear.getValue();
        });

        ComboBox<String> comboCity = new ComboBox<>();
        comboCity.setPromptText("3. 都市を選択");
        comboCity.getItems().setAll("東京", "札幌", "福岡");
        comboCity.setPrefWidth(120);
        comboCity.setOnAction(e -> {
            String city = comboCity.getValue();
            if ("東京".equals(city)) {
                column = 2;
            } else if ("札幌".equals(city)) {
                column = 3;
            } else {
                column = 4;
            }
        });

        Button showChart = new Button("4. 円グラフを表示");
        showChart.setPrefWidth(120);
        showChart.setAlignment(Pos.CENTER_LEFT);
        showChart.setOnAction(e -> {
            if (file != null && year != 0 && column != 0) {
                System.out.println("Year: " + year + ", Column: " + column);
                double[] data = selectData(file, year, column);
                PieChart pieChart = makePieChart(data);
                pane.setCenter(pieChart);
            }
        });

        VBox vbox = new VBox(10, setFile, comboYear, comboCity, showChart);
        vbox.setPadding(new Insets(10));
        pane.setLeft(vbox);
        pane.setCenter(new PieChart());
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Railfall Graph Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
        System.out.println("完了--RailfallGraphApp");
    }
}

/* 検証 -- 動作確認テストの内容と方法を記述
まずファイルを選択し、年を2010、都市を東京を選択し円グラフを表示させる。
このとき、円グラフが1月から12月まで順に、真上を起点として右回りに表示されているか確認する。
また、この時にラベル表示が正しく表示されているか確認する。以上のことが確認できたならば円グラフを正しく表示することができたと言える。
次に年を2011から2019まで順次変更し、表示する。
これによって、値が変わった時にもう一度データの読みこみをし、円グラフを正常に表示しなおすのを確認するとともに、年を変えた時に表示されるデータが指定した年に対応しているか確認できる。
同じように都市を札幌、福岡に変更し、年を2010年から2019年を順に選択し、円グラフが正しいデータで表示されるかを確認する。
 */

/* 考察 -- 調査したこと、考慮したこと、工夫したことを記述
課題1について、ファイルの文字の読み込みについて、文字コードを指定することにより実行するソフトによって文字化けすることを防いだ。
課題2について、PieChartの作成の仕方や開始位置を真上にするコード、右周りに値を表示させるやり方等を調べた。また、円グラフはEclipseでは初期に右回りに表示されたが、他のソフト
でどのように表示されているかわからないので、右回りに表示するというコードを記入した。また、ラベルが見やすいようい、ラベルの間隔を20にした。
 */
