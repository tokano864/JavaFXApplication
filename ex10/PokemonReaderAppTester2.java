// Exercise 10-2T: Pokemon Reader App Tester2
package ex10;

import java.io.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.*;

public class PokemonReaderAppTester2 extends Application {

    PokemonReaderApp app;
    String appName = "PokemonReaderApp";
    int score = 0, scoreMax = 6, s1 = 1, s2 = 1, s3 = 1;

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.printf("%s 第二テスト開始\n", appName);
            app = new PokemonReaderApp();
            app.start(primaryStage);

            Scene scene = findScene(primaryStage);
            if (scene != null) {
                score += s1;
            } else {
                printScore();
                return;
            }

            System.out.print("--- 確認 Pokemon.txt ... ");
            File file2 = new File("Pokemon.txt");
            if ("Pokemon.txt".equals(file2.getName())) {
                System.out.println("成功");
                score += s1;
            } else {
                System.out.println("失敗 Pokemon.txt を以下に移動してください");
                System.out.println(file2.getAbsolutePath());
                printScore();
                primaryStage.close();
                return;
            }

            System.out.print("--- 確認 readFile() ... ");
            app.readFile(file2);
            ObservableList<String> list = app.combo.getItems();
            if (list.size() > 0) {
                System.out.println("成功");
                score += s1;
            } else {
                System.out.println("失敗 ComboBox is empty!");
                printScore();
                return;
            }

            String[] pokemon = {"フシギダネ", "", "", "", "リザード", "",
                "", "", "カメックス"};
            for (int i = 0; i < 9; i += 4) {
                System.out.printf("--- 確認 %s ... ", pokemon[i]);
                if (pokemon[i].equals(list.get(i).toString())) {
                    System.out.println("成功");
                    score += s2;
                } else {
                    System.out.printf("失敗 原因 %s?\n", list.get(i).toString());
                    printScore();
                    return;
                }
            }

            primaryStage.close();
            System.out.printf("%s 第二テスト終了\n", appName);
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
