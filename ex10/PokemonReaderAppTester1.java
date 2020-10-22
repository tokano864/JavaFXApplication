// Exercise 10-1T: Pokemon Reader App Tester1
package ex10;

import java.io.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PokemonReaderAppTester1 extends Application {

    PokemonReaderApp app;
    String appName = "PokemonReaderApp";
    int score = 0, scoreMax = 4, s1 = 1, s2 = 1, s3 = 1;

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.printf("%s 第一テスト開始\n", appName);
            app = new PokemonReaderApp();
            app.start(primaryStage);

            Scene scene = findScene(primaryStage);
            if (scene != null) {
                score += s1;
            } else {
                printScore();
                return;
            }

            System.out.print("--- 確認 openFile() ... ");
            File file = app.openFile();
            if (file != null) {
                System.out.println("成功");
                score += s1;
            } else {
                System.out.println("失敗 テキストファイルを選択してください");
                printScore();
                primaryStage.close();
                return;
            }

            System.out.print("--- 確認 Pokemon.txt の存在 ... ");
            if ("Pokemon.txt".equals(file.getName())) {
                System.out.println("成功");
                score += s1;
            } else {
                System.out.println("失敗 Pokemon.txt を選択してください");
                printScore();
                primaryStage.close();
                return;
            }

            System.out.print("--- 確認 Pokemon1.txt の場所 ... ");
            File file2 = new File("Pokemon.txt");
            if (file.getAbsolutePath().equals(file2.getAbsolutePath())) {
                System.out.println("成功");
                score += s1;
            } else {
                System.out.println("失敗 Pokemon.txt を以下に移動してください");
                System.out.println(file2.getAbsolutePath());
                printScore();
                primaryStage.close();
                return;
            }

            primaryStage.close();
            System.out.printf("%s 第一テスト終了\n", appName);
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
