import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.util.*;
public class Quiz  extends Application {
    private static String name;
    public static List<String> question = new ArrayList<>();
    public static final ArrayList<Question> hh = new ArrayList<>();
    public void addQuestion(Question q) {
        hh.add(q);
    }
    public int sec = 30;
    public int min = 0;
    public int fradio = 0;
    public Test test;
    public String[] yk;
    public Scene[] scenes = new Scene[100];
    public Pane[] panes = new Pane[100];
    public ArrayList<RadioButton> radioButtons = new ArrayList<>();
    public Button buttonnext = new Button(">");
    public static Quiz quiz = new Quiz();
    public Stage stage = new Stage();
    public int f = 0;
    public int f1 = 0;

    ArrayList<String> names12 = new ArrayList<>();
    ArrayList<Integer> points12 = new ArrayList<>();

    public Button buttonstart = new Button("START");
    public Text textcount ;

    public  static final int pincode = (int)(Math.random()*((999999-111111)+1))+111111;
    public int people=0;
    public int peopleans=0;
    public VBox vBox = new VBox();

    public String [][] testVariantAns = new String[100][4];

    public Text countans = new Text("0");
    public List<String> ansradio = new ArrayList<>();
    public ArrayList<String> showoptionTest = new ArrayList<>();

    public Media sound = new Media(new File("resources/kahoot_music.mp3").toURI().toString());
    public MediaPlayer mediaPlayer = new MediaPlayer(sound);

    public static Quiz loadFromFile(String file) throws Exception {
        try {
            File f = new File(file);
            Scanner in = new Scanner(f);
            name = f.getName().replace(".txt", "");
            String k = "";
            if (!in.hasNextLine()) throw new InvalidQuizFormatException();
            while (in.hasNextLine()) {
                String dd = in.nextLine();
                k += (dd + "\n");
            }
            if (k == "" || k == null) throw new InvalidQuizFormatException();

            question = Arrays.asList(k.split("\n\n"));
            Collections.shuffle(question);

            for (int i = 0; i < question.size(); i++) {
                String[] sdu = question.get(i).split("\n");

                if (sdu[0].contains("{blank}")) {
                    FillIn fill = new FillIn();
                    fill.setDescription(sdu[0]);
                    fill.setAnswer(sdu[1]);
                    quiz.addQuestion(fill);
                } else {
                    Test test = new Test();
                    test.setDescription(sdu[0]);
                    test.setAnswer(sdu[1]);
                    String sdu2 = "";
                    for (int j = 1; j < sdu.length; j++) {
                        sdu2 += sdu[j] + "\n";
                    }
                    String[] gotoSdu = sdu2.split("\n");
                    test.setOptions(gotoSdu);
                    quiz.addQuestion(test);
                }
            }
            in.close();

        } catch (InvalidQuizFormatException e) {System.out.println("Error");}
        return quiz;
    }

    public void start(Stage stage1) throws Exception {

        stage = stage1;
        for (int plus = 0; plus < 1000; plus++) {radioButtons.add(new RadioButton());}
          if (f1 == 0) {mediaPlayer.play();}
          if (f1 == question.size()) {mediaPlayer.stop();}

        /////////////////////////////////////////////////////////

        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(7777);
                for (; f < question.size(); f++) {
                    for (int hyp = 0; hyp < 4; hyp++) {fradio++;}
                    Image image = null;
                    try {image = new Image(new FileInputStream("resources/img/logo.png"));} catch (FileNotFoundException e) {e.printStackTrace();}
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(300);
                    imageView.setFitHeight(200);
                    Rectangle rectangle1 = new Rectangle(14, 374, 326, 93);
                    Rectangle rectangle2 = new Rectangle(14, 481, 326, 93);
                    Rectangle rectangle3 = new Rectangle(374, 374, 326, 93);
                    Rectangle rectangle4 = new Rectangle(374, 481, 326, 93);
                    rectangle1.setStyle("-fx-fill:#ff1f2d");
                    rectangle2.setStyle("-fx-fill:#e4ff1f");
                    rectangle3.setStyle("-fx-fill:#21d4ff");
                    rectangle4.setStyle("-fx-fill:#3aff1f");
                    rectangle1.setStroke(Color.BLACK);
                    rectangle2.setStroke(Color.BLACK);
                    rectangle3.setStroke(Color.BLACK);
                    rectangle4.setStroke(Color.BLACK);
                    rectangle1.setArcHeight(10.0d);
                    rectangle1.setArcWidth(10.0d);
                    rectangle2.setArcHeight(10.0d);
                    rectangle2.setArcWidth(10.0d);
                    rectangle3.setArcHeight(10.0d);
                    rectangle3.setArcWidth(10.0d);
                    rectangle4.setArcHeight(10.0d);
                    rectangle4.setArcWidth(10.0d);

                    radioButtons.get(fradio).setLayoutX(40);
                    radioButtons.get(fradio).setLayoutY(415);
                    radioButtons.get(fradio + 1).setLayoutX(40);
                    radioButtons.get(fradio + 1).setLayoutY(520);
                    radioButtons.get(fradio + 2).setLayoutX(410);
                    radioButtons.get(fradio + 2).setLayoutY(415);
                    radioButtons.get(fradio + 3).setLayoutX(410);
                    radioButtons.get(fradio + 3).setLayoutY(520);

                    buttonnext = new Button(">");
                    buttonnext.setLayoutX(687);
                    buttonnext.setLayoutY(213);
                    buttonnext.setPrefHeight(41);
                    buttonnext.setPrefWidth(26);

                    Text text = new Text();
                    text.setFill(Color.ORANGERED);
                    text.setFontSmoothingType(FontSmoothingType.LCD);
                    text.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));

                    Text text1 = new Text();
                    text1.setFill(Color.BLUEVIOLET);
                    text1.setFontSmoothingType(FontSmoothingType.LCD);
                    text1.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));

                    Image image1 = null;
                    try {image1 = new Image(new FileInputStream("resources/img/fillin.png"));} catch (FileNotFoundException e) {e.printStackTrace();}
                    ImageView imageView1 = new ImageView(image1);
                    imageView1.setFitWidth(259);
                    imageView1.setFitHeight(135);


                    if (f != hh.size()) {
                        yk = question.get(f).split("\n");
                        if (!yk[0].contains("{blank}")) {

                            showoptionTest.add(yk[0]);
                            ansradio.add(yk[1]);

                            test = (Test) hh.get(f);
                            text.setText(test.getDescription());

                            TranslateTransition rot5 = new TranslateTransition(Duration.millis(1000), text);
                            rot5.setByY(60);
                            rot5.setByX(210);
                            rot5.playFromStart();
                            TranslateTransition rot51 = new TranslateTransition(Duration.millis(1000), imageView);
                            rot51.setByY(140);
                            rot51.setByX(205);
                            rot51.playFromStart();

                            radioButtons.get(fradio).setText(test.getOptionAt(0));
                            radioButtons.get(fradio + 1).setText(test.getOptionAt(1));
                            radioButtons.get(fradio + 2).setText(test.getOptionAt(2));
                            radioButtons.get(fradio + 3).setText(test.getOptionAt(3));

                            testVariantAns[f][0]=test.getOptionAt(0);
                            testVariantAns[f][1]=test.getOptionAt(1);
                            testVariantAns[f][2]=test.getOptionAt(2);
                            testVariantAns[f][3]=test.getOptionAt(3);


                            panes[f] = new Pane();
                            panes[f].getChildren().addAll(imageView, rectangle1, rectangle2, rectangle3, rectangle4, buttonnext, text, radioButtons.get(fradio), radioButtons.get(fradio + 1), radioButtons.get(fradio + 2), radioButtons.get(fradio + 3));
                            panes[f].setStyle("-fx-background-color:#2E3348");
                            scenes[f] = new Scene(panes[f], 650, 730);
                        }
                    }



                    if (f == 0) {panes[f].getChildren().addAll(ll123());} else if (f != hh.size()) {panes[f].getChildren().addAll(ll1234());}


                    buttonnext.setOnMouseClicked(e -> {
                        f1++;
                        sec = 30;
                        peopleans=0;
                        if(f1<hh.size()) {
                            Platform.runLater(() -> {countans.setText(String.valueOf(peopleans));});
                            countans.setFill(Color.WHITE);
                            countans.setFontSmoothingType(FontSmoothingType.LCD);
                            countans.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
                            countans.setLayoutX(100);
                            countans.setLayoutY(100);
                            panes[f1].getChildren().addAll(countans);
                            stage.setScene(scenes[f1]);
                        }else if (f1==hh.size()+2){

                            try {
                                stage.setScene(new Scene(paneresult(names12,points12),650,730));
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }

                        }


                    });

                }
                while (true) {
                    try {
                        System.out.println("Waiting for connection...");

                        Socket socket = server.accept();

                        people++;
                        System.out.println(people + " Client is Connected!");
                        new Thread(() -> {
                            try {
                                DataInputStream fromClient = new DataInputStream(socket.getInputStream());
                                DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());

                                int clientPin = fromClient.readInt();
                                if (clientPin == pincode) {
                                    toClient.writeUTF("Success!");
                                }
                                String nickname = fromClient.readUTF();
                                System.out.println(nickname);
                                Text textrr = new Text(nickname);
                                textrr.setFill(Color.WHITE);
                                textrr.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));

                                Platform.runLater(() -> {
                                            vBox.getChildren().addAll(textrr);
                                            textcount.setText(String.valueOf(people));
                                        }
                                );


                                while (true) {
                                    String nickname22 = fromClient.readUTF();
                                    Integer clientChoice = fromClient.readInt();

                                    if (clientChoice < 5) {
                                        System.out.println(nickname22 + " Ответил " + clientChoice);
                                        peopleans++;
                                        Platform.runLater(() -> {
                                                    countans.setText(String.valueOf(peopleans));
                                                }
                                        );

                                        if (testVariantAns[f1][clientChoice].equals(ansradio.get(f1))) {
                                            toClient.writeUTF("YES");
                                            System.out.println("YES correct " + nickname22);
                                        } else {
                                            toClient.writeUTF("NO");
                                            System.out.println("NO correct " + nickname22);
                                        }
                                    }
                                    else {
                                        mediaPlayer.stop();
                                        try {
                                            Comparator<Robot1.Point> pcomp = new Robot1.PersonAgeComparator();
                                            TreeSet<Robot1.Point> people3 = new TreeSet(pcomp);
                                            people3.add(new Robot1.Point(fromClient.readUTF(), fromClient.readInt()));

                                            for (Robot1.Point p : people3) {
                                                names12.add(p.getName()); points12.add(p.getAge());
                                                System.out.println(p.getName() + " " + p.getAge());
                                            }

                                        } catch (Exception ex) {ex.printStackTrace();}

                                    }
                                }


                            } catch (IOException e) {}
                        }).start();
                    } catch (IOException e) {}
                }
            } catch (IOException e) {}



        }).start();


///////////////////////////////////////////////////////////////////////////
        buttonstart.setOnMouseClicked(e->{
            System.out.println(ansradio.toString());
            System.out.println(showoptionTest.toString());
            sec=30;
            countans.setFill(Color.WHITE);
            countans.setFontSmoothingType(FontSmoothingType.LCD);
            countans.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
            countans.setLayoutX(100);
            countans.setLayoutY(100);
            panes[0].getChildren().addAll(countans);
            stage.setScene(scenes[0]);
        });
/////////////////////////////////////////////////////////////////////////////////////
        stage.setScene(new Scene(pincodepane(),1000,700));
        stage.setTitle("PROJECT3");
        stage.setHeight(630);
        stage.setWidth(730);
        stage.show();
    }
    //////////////////////////////////////////////////////////////////////////////////////
    public String timeFormat(int sec) {
        sec = sec % 60;
        String strMin = min + "";
        if (min < 10) strMin = "0" + strMin;
        String strSec = sec + "";
        if (sec < 10) strSec = "0" + strSec;
        return strMin + ":" + strSec;
    }
    public Label ll123() {
        Timeline timer = new Timeline();
        Label chasi1 = new Label();
        chasi1.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.REGULAR, 22));
        chasi1.setLayoutX(330);
        chasi1.setLayoutY(100);
        chasi1.setStyle("-fx-background-color:#71dc3b");
        chasi1.setText(timeFormat(sec));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        event -> {
                            chasi1.setText(timeFormat(sec--));
                        }));
        timer.play();
        return chasi1;
    }
    public Label ll1234() {
        Timeline timer = new Timeline();
        Label chasi1 = new Label();
        chasi1.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.REGULAR, 22));
        chasi1.setLayoutX(330);
        chasi1.setLayoutY(100);
        chasi1.setStyle("-fx-background-color:#71dc3b");
        chasi1.setText(timeFormat(sec));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        event -> {
                            chasi1.setText(timeFormat(sec));
                        }));
        timer.play();
        return chasi1;
    }

    public Pane paneresult(ArrayList<String>name11,ArrayList<Integer>point11) throws Exception {
        Pane paneresult = new Pane();

        Map<String,Integer> result = new TreeMap<>();
        for (int u =0;u<people;u++){
            result.put(names12.get(u),points12.get(u));
        }

        Button buttonresult = new Button("SHOW");
        buttonresult.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
        buttonresult.setLayoutX(500);
        buttonresult.setLayoutY(100);
        buttonresult.setPrefHeight(80);
        buttonresult.setPrefWidth(150);
        buttonresult.setStyle  ("-fx-background-color:#44b424 ");

        Text textfirst = new Text();
        textfirst.setFill(Color.GREEN);
        textfirst.setFontSmoothingType(FontSmoothingType.LCD);
        textfirst.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
        textfirst.setLayoutX(360);
        textfirst.setLayoutY(260);

        Text textsecond = new Text();
        textsecond.setFill(Color.GREEN);
        textsecond.setFontSmoothingType(FontSmoothingType.LCD);
        textsecond.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
        textsecond.setLayoutX(220);
        textsecond.setLayoutY(320);

        Text textthird = new Text();
        textthird.setFill(Color.GREEN);
        textthird.setFontSmoothingType(FontSmoothingType.LCD);
        textthird.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
        textthird.setLayoutX(480);
        textthird.setLayoutY(380);

        Text textfirstcount = new Text();
        textfirstcount.setFill(Color.WHITE);
        textfirstcount.setFontSmoothingType(FontSmoothingType.LCD);
        textfirstcount.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
        textfirstcount.setLayoutX(370);
        textfirstcount.setLayoutY(450);

        Text textsecondcount = new Text();
        textsecondcount.setFill(Color.WHITE);
        textsecondcount.setFontSmoothingType(FontSmoothingType.LCD);
        textsecondcount.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
        textsecondcount.setLayoutX(230);
        textsecondcount.setLayoutY(500);

        Text textthirdcount = new Text();
        textthirdcount.setFill(Color.WHITE);
        textthirdcount.setFontSmoothingType(FontSmoothingType.LCD);
        textthirdcount.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));
        textthirdcount.setLayoutX(500);
        textthirdcount.setLayoutY(560);


        buttonresult.setOnMouseClicked(e->{
            String[]names = new String[3];
            Integer[]points = new Integer[3];
            int i=0;
            for (Map.Entry<String, Integer> pair : result.entrySet()) {
                System.out.println(String.format("Key (name) is: %s, Value (age) is : %s", pair.getKey(), pair.getValue()));
                names[i]=pair.getKey();
                points[i]=pair.getValue();
                i++;
            }
            Platform.runLater(()->{
                textfirst.setText(names[0]);
                textsecond.setText(names[1]);
                textthird.setText(names[2]);

                textfirstcount.setText(String.valueOf(points[0]));
                textsecondcount.setText(String.valueOf(points[1]));
                textthirdcount.setText(String.valueOf(points[2]));

            });


        });

        Image imageresult = new Image(new FileInputStream("resources/img/kahootresult.jpg"));
        ImageView imageViewresult = new ImageView(imageresult);
        imageViewresult.setFitHeight(730);
        imageViewresult.setFitWidth(800);
        paneresult.setStyle("-fx-background-color:#9fa938");

        paneresult.getChildren().addAll(imageViewresult,buttonresult,textfirst,textsecond,textthird,textfirstcount,textsecondcount,textthirdcount);

        return paneresult;
    }

    public Pane pincodepane (){
        Pane pane123 = new Pane();
        buttonstart.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
        buttonstart.setLayoutX(500);
        buttonstart.setLayoutY(300);
        buttonstart.setPrefHeight(80);
        buttonstart.setPrefWidth(150);
        buttonstart.setStyle  ("-fx-background-color:#44b424 ");
        Image imagepeople = null;
        try {imagepeople = new Image(new FileInputStream("resources/img/count.png"));} catch (FileNotFoundException e) {e.printStackTrace();}
        ImageView imageViewpeople = new ImageView(imagepeople);
        imageViewpeople.setLayoutX(400);
        imageViewpeople.setLayoutY(100);
        imageViewpeople.setFitWidth(70);
        imageViewpeople.setFitHeight(70);
        textcount = new Text(String.valueOf(people));
        textcount.setLayoutY(150);
        textcount.setLayoutX(515);
        textcount.setFill(Color.WHITE);
        textcount.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40));
        Text textpin= new Text("Game PINCODE: " + pincode);
        textpin.setFill(Color.WHITE);
        textpin.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
        textpin.setLayoutX(350);
        textpin.setLayoutY(70);
        vBox.setAlignment(Pos.CENTER);
        pane123.setStyle("-fx-background-color: #3e147f");
        pane123.getChildren().addAll(textpin,vBox,imageViewpeople,textcount,buttonstart);
        return pane123;
    }
}