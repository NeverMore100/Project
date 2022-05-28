import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientVIP extends Application {
    public Stage window;
    public final double width = 600, height = 600;
    public Pane pane;
    public Socket socket;
    public DataOutputStream toServer;
    public DataInputStream fromServer;
    public  int count =0;
    public int vihod =0;

    public void connectToServer() throws IOException {
        socket = new Socket("localhost", 7777);
        toServer = new DataOutputStream(socket.getOutputStream());
        fromServer = new DataInputStream(socket.getInputStream());
    }
    public Button stylebuttos() {
        Button btn = new Button();
        btn.setMinWidth(width / 2. - 5);
        btn.setMinHeight(height / 2. - 5);
        btn.setWrapText(true);
        btn.setPadding(new Insets(10));
        return btn;
    }
    public StackPane buttonsconnect(String nickname) {
        StackPane stackPane = new StackPane();
        VBox vBox1 = new VBox(10);
        VBox vBox2 = new VBox(10);
        HBox hBox = new HBox(10);
        Button btn1 = stylebuttos();
        Button btn2 = stylebuttos();
        Button btn3 = stylebuttos();
        Button btn4 = stylebuttos();
        btn1.setStyle("-fx-background-color:Red ");
        btn2.setStyle("-fx-background-color:Blue");
        btn3.setStyle("-fx-background-color:Yellow");
        btn4.setStyle("-fx-background-color:Green");
        vBox1.getChildren().addAll(btn1, btn3);
        vBox2.getChildren().addAll(btn2, btn4);
        hBox.getChildren().addAll(vBox1, vBox2);
        stackPane.getChildren().addAll(hBox);
        btn1.setOnAction(e -> {
            vihod++;
            if (vihod <= 5) {
                try {
                    toServer.writeUTF(nickname);
                    toServer.writeInt(0);
                    if (fromServer.readUTF().equals("YES")) {
                        count = count + 100;
                        Timeline timeline = new Timeline();
                        timeline.setCycleCount(2);
                        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                                event -> {
                                    window.setScene(new Scene(panecorrect1(), 600, 600));
                                });
                        timeline.setOnFinished(event -> Platform.runLater(() -> {
                            window.setScene(new Scene(buttonsconnect(nickname), 600, 600));
                        }));
                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();
                    } else {
                        Timeline timeline = new Timeline();
                        timeline.setCycleCount(2);
                        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                                event -> {
                                    window.setScene(new Scene(panewrong1(), 600, 600));
                                });
                        timeline.setOnFinished(event -> Platform.runLater(() -> {
                            window.setScene(new Scene(buttonsconnect(nickname), 600, 600));
                        }));
                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (vihod > 5) {
                window.setScene(new Scene(paneotpravka(nickname, count), 600, 600));

            }


        });
        btn2.setOnAction(e -> {
            vihod++;
            if (vihod <= 5) {
                try {
                    // vihod++;
                    toServer.writeUTF(nickname);
                    toServer.writeInt(2);
                    if (fromServer.readUTF().equals("YES")) {
                        count = count + 100;
                        Timeline timeline = new Timeline();
                        timeline.setCycleCount(2);
                        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                                event -> {
                                    window.setScene(new Scene(panecorrect1(), 600, 600));
                                });
                        timeline.setOnFinished(event -> Platform.runLater(() -> {
                            window.setScene(new Scene(buttonsconnect(nickname), 600, 600));
                        }));
                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();
                    } else {
                        Timeline timeline = new Timeline();
                        timeline.setCycleCount(2);
                        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                                event -> {
                                    window.setScene(new Scene(panewrong1(), 600, 600));
                                });
                        timeline.setOnFinished(event -> Platform.runLater(() -> {
                            window.setScene(new Scene(buttonsconnect(nickname), 600, 600));
                        }));
                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (vihod > 5) {

                window.setScene(new Scene(paneotpravka(nickname, count), 600, 600));

//                try {
//                    toServer.writeUTF(nickname);
//                    toServer.writeInt(count);
//                } catch (IOException e2) {
//                    e2.printStackTrace();
//                }
            }
        });
        btn3.setOnAction(e -> {
            vihod++;
            if (vihod <= 5) {
                try {
                    // vihod++;
                    toServer.writeUTF(nickname);
                    toServer.writeInt(1);
                    if (fromServer.readUTF().equals("YES")) {
                        count = count + 100;
                        Timeline timeline = new Timeline();
                        timeline.setCycleCount(2);
                        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                                event -> {
                                    window.setScene(new Scene(panecorrect1(), 600, 600));
                                });
                        timeline.setOnFinished(event -> Platform.runLater(() -> {
                            window.setScene(new Scene(buttonsconnect(nickname), 600, 600));
                        }));
                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();
                    } else {
                        Timeline timeline = new Timeline();
                        timeline.setCycleCount(2);
                        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                                event -> {
                                    window.setScene(new Scene(panewrong1(), 600, 600));
                                });
                        timeline.setOnFinished(event -> Platform.runLater(() -> {
                            window.setScene(new Scene(buttonsconnect(nickname), 600, 600));
                        }));
                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (vihod > 5) {

                window.setScene(new Scene(paneotpravka(nickname, count), 600, 600));

//                try {
//                    toServer.writeUTF(nickname);
//                    toServer.writeInt(count);
//                } catch (IOException e2) {
//                    e2.printStackTrace();
//                }
            }
        });
        btn4.setOnAction(e -> {
            vihod++;
            if (vihod <= 5) {
                try {
                    // vihod++;
                    toServer.writeUTF(nickname);
                    toServer.writeInt(3);
                    if (fromServer.readUTF().equals("YES")) {
                        count = count + 100;
                        Timeline timeline = new Timeline();
                        timeline.setCycleCount(2);
                        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                                event -> {
                                    window.setScene(new Scene(panecorrect1(), 600, 600));
                                });
                        timeline.setOnFinished(event -> Platform.runLater(() -> {
                            window.setScene(new Scene(buttonsconnect(nickname), 600, 600));
                        }));
                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();
                    } else {
                        Timeline timeline = new Timeline();
                        timeline.setCycleCount(2);
                        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                                event -> {
                                    window.setScene(new Scene(panewrong1(), 600, 600));
                                });
                        timeline.setOnFinished(event -> Platform.runLater(() -> {
                            window.setScene(new Scene(buttonsconnect(nickname), 600, 600));
                        }));
                        timeline.getKeyFrames().add(keyFrame);
                        timeline.play();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (vihod > 5) {

                window.setScene(new Scene(paneotpravka(nickname, count), 600, 600));

//                try {
//                    toServer.writeUTF(nickname);
//                    toServer.writeInt(count);
//                } catch (IOException e2) {
//                    e2.printStackTrace();
//                }
            }
        });
        return stackPane;
    }

    public StackPane nicknamePane() {
        StackPane stackPane = new StackPane();
        TextField tf = new TextField();
        tf.setPromptText("Enter username");
        tf.setMaxWidth(width / 3);
        tf.setMinHeight(40);
        tf.setAlignment(Pos.CENTER);
        Button btn = new Button("Enter");
        btn.setMaxWidth(width / 3);
        btn.setMinHeight(40);
        btn.setStyle("-fx-background-color:#333333");
        btn.setTextFill(Color.WHITE);
        btn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
        VBox vBox = new VBox(10);
        vBox.setMaxWidth(width / 2);
        vBox.setMaxHeight(height / 2);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(tf, btn);
        stackPane.getChildren().addAll(vBox);
        stackPane.setStyle("-fx-background-color: #b28d2f");
        btn.setOnAction(e -> {
            try {
                toServer.writeUTF(tf.getText());
                // if (fromServer.readUTF().equals("START")) {
                window.setScene(new Scene(buttonsconnect(tf.getText()), width, height));
                //   }
            } catch (IOException ex) {throw new RuntimeException(ex);}

        });
        return stackPane;
    }
    public StackPane pinPane() {
        StackPane stackPane = new StackPane();
        TextField tf = new TextField();
        tf.setPromptText("Game PIN");
        tf.setMaxWidth(width / 3);
        tf.setMinHeight(40);
        tf.setAlignment(Pos.CENTER);
        Button btn = new Button("Enter");
        btn.setMaxWidth(width / 3);
        btn.setMinHeight(40);
        btn.setStyle("-fx-background-color:#333333");
        btn.setTextFill(Color.WHITE);
        btn.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));
        VBox vBox = new VBox(10);
        vBox.setMaxWidth(width / 2);
        vBox.setMaxHeight(height / 2);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(tf, btn);

        stackPane.getChildren().addAll(vBox);
        stackPane.setStyle("-fx-background-color: #3e147f");

        btn.setOnAction(e -> {
            try {
                toServer.writeInt(Integer.parseInt(tf.getText()));
                String status = fromServer.readUTF();
                if (status.equals("Success!")) {
                    window.setScene(new Scene(nicknamePane(), width, height));
                    window.setTitle("Enter Nickname");
                }
                System.out.println(status);
            } catch (IOException ex) {throw new RuntimeException(ex);}
        });
        return stackPane;
    }
    public Pane panecorrect1(){
        Pane panecorrect = new Pane();
        panecorrect.setStyle("-fx-background-color: rgba(6,231,28,0.93)");
        Text textballcorrect = new Text();
        textballcorrect.setFill(Color.WHITE);
        textballcorrect.setFontSmoothingType(FontSmoothingType.LCD);
        textballcorrect.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
        textballcorrect.setLayoutX(250);
        textballcorrect.setLayoutY(213);
        textballcorrect.setText("CORRECT");
        Text textdobavkacorrect = new Text();
        textdobavkacorrect.setFill(Color.WHITE);
        textdobavkacorrect.setFontSmoothingType(FontSmoothingType.LCD);
        textdobavkacorrect.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
        textdobavkacorrect.setLayoutX(250);
        textdobavkacorrect.setLayoutY(300);
        textdobavkacorrect.setText("+100");
        panecorrect.getChildren().addAll(textballcorrect,textdobavkacorrect);
        return panecorrect;
    }
    public Pane panewrong1(){
        Pane panewrong = new Pane();
        panewrong.setStyle("-fx-background-color: rgb(194,4,35)");
        Text textballwrong = new Text();
        textballwrong.setFill(Color.WHITE);
        textballwrong.setFontSmoothingType(FontSmoothingType.LCD);
        textballwrong.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
        textballwrong.setLayoutX(250);
        textballwrong.setLayoutY(213);
        textballwrong.setText("WRONG");
        Text textdobavkawrong = new Text();
        textdobavkawrong.setFill(Color.WHITE);
        textdobavkawrong.setFontSmoothingType(FontSmoothingType.LCD);
        textdobavkawrong.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
        textdobavkawrong.setLayoutX(250);
        textdobavkawrong.setLayoutY(300);
        textdobavkawrong.setText("+0");
        panewrong.getChildren().addAll(textballwrong,textdobavkawrong);
        return panewrong;
    }

    public Pane paneotpravka(String nick,int count1){
        Pane panewrong = new Pane();
        panewrong.setStyle("-fx-background-color: rgb(194,4,35)");


        Button buttonotpravka = new Button("Go");
        buttonotpravka.setLayoutX(300);
        buttonotpravka.setLayoutY(300);
        buttonotpravka.setMaxWidth(width / 3);
        buttonotpravka.setMinHeight(40);
        buttonotpravka.setStyle("-fx-background-color:#333333");
        buttonotpravka.setTextFill(Color.WHITE);
        buttonotpravka.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 16));

        buttonotpravka.setOnMouseClicked(e->{

            try {
                toServer.writeUTF(nick);
                toServer.writeInt(count1);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        });


        panewrong.getChildren().addAll(buttonotpravka);
        return panewrong;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        connectToServer();
        pane = pinPane();
        pane.requestFocus();
        window.setScene(new Scene(pane, width, height));
        window.show();
        window.setTitle("Enter PIN!");

    }
}
