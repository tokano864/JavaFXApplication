// Exercise 10-3T: Pokemon Reader App Tester2
package ex10;

import java.io.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*;

public class PokemonReaderAppTester3 extends Application {

    PokemonReaderApp app;
    String appName = "PokemonReaderApp";
    int score = 0, scoreMax = 4, s1 = 1, s2 = 1, s3 = 1;

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.printf("%s 第三テスト開始\n", appName);
            app = new PokemonReaderApp();
            app.start(primaryStage);

            Scene scene = findScene(primaryStage);
            if (scene != null) {
                score += s1;
            } else {
                printScore();
                return;
            }

            app.readFile(new File("Pokemon.txt"));
            ObservableList<String> list = app.combo.getItems();

            String[] pokemon = {"", "フシギソウ", "", "", "リザード", "",
                "", "カメール", ""};
            String[] description = {"", "フシギダネの進化形。", "",
                "", "ヒトカゲの進化形。", "",
                "", "ゼニガメの進化形。", ""};
            for (int i = 1; i < 9; i += 3) {
                System.out.printf("--- 確認 %s ... ", pokemon[i]);
                app.combo.setValue(pokemon[i]);
                //System.out.println(app.label.getText());
                if (app.label.getText().contains(description[i])) {
                    System.out.println("成功");
                    score += s3;
                } else {
                    System.out.printf("失敗 原因 %s?\n",
                            app.label.getText().substring(0, 10));
                    printScore();
                    return;
                }
            }

            primaryStage.close();
            System.out.printf("%s 第三テスト終了\n", appName);
            printScore();
        } catch (Exception e) {
            printScore();
            e.printStackTrace();
        }
    }

    void printScore() {
        System.out.println(String.format(
                "\n【実行対象:%s, 学籍番号:%s, 学生氏名:%s, 評点:%d】",
                appName, app.gakuban, app.yourname, 10 * score / scoreMax));
    }

    public static Scene findScene(Stage stage) {
        System.out.print("--- 起動 Scene ... ");
        Scene scene = stage.getScene();
        if (scene != null) {
            System.out.println("成功");
            return scene;
        } else {
            System.out.println("失敗");
            return null;
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
