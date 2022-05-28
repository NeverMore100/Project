import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.lang.reflect.Array;
import java.util.ArrayList.*;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import javax.swing.*;
public class QuizMaker extends Application {
    public static void main(String[] args) throws Exception {launch(args);}
    public Scene scene;
    public void start(Stage stage)throws Exception {
       Pane panec = new Pane();
        Image image = new Image(new FileInputStream("resources\\img\\background.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitWidth(715);
        Button button = new Button("Choose a file");
        button.setLayoutX(310);
        button.setLayoutY(300);
        button.setOnMouseClicked(e->{
        FileChooser fil_chooser = new FileChooser();
        File file = fil_chooser.showOpenDialog(stage);

        Quiz quiz = null;
    try {quiz = Quiz.loadFromFile(file.getAbsolutePath());} catch (Exception ex) {ex.printStackTrace();}
    try {quiz.start(stage);} catch (Exception ex) {ex.printStackTrace();}});

        panec.getChildren().addAll(imageView,button);
        scene = new Scene(panec,630, 730);
        stage.setScene(scene);
        stage.setTitle("JAVAPROJECT2");
        stage.setHeight(630);
        stage.setMinWidth(730);
        stage.show();
    }

}