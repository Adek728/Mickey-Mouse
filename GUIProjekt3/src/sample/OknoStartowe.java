package sample;


import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Insets;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;



public class OknoStartowe extends Application {



    private int miejsce = 1;
    private Postac p1;
    private Gracz g1;
    private Scene oknoStartowe;
    private Scene gra;
    private Scene przegrana;
    private Scene rankingKoncowy;
    private Ranking rank;

    @Override
    public void start(Stage stage) throws Exception {
        //okno startowe
        rank = new Ranking();
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("gridPane");
        oknoStartowe = new Scene(gridPane, 420, 270);;
        oknoStartowe.getStylesheets().add("style.css");
        stage.setScene(oknoStartowe);
        stage.show();


        //calość związana z rankingiemn
        GridPane rankingPane = new GridPane();
        Button wyjscieZRanking = new Button("Wyjscie");
        ListView<String> lista = new ListView<>();
        rankingPane.add(lista,0,0);
        rankingPane.add(wyjscieZRanking,0,1);
        rankingKoncowy = new Scene(rankingPane,300,300);
        rankingKoncowy.getStylesheets().add("style.css");
        wyjscieZRanking.setOnAction(g -> {
            stage.setScene(oknoStartowe);
        });


        //przyciski na oknie startowym
        Button start = new Button("Nowa gra");
        start.getStyleClass().add("start");
        Button ranking = new Button("Ranking");
        ranking.getStyleClass().add("ranking");
        Button wyjscie = new Button("Wyjście");
        wyjscie.getStyleClass().add("wyjscie");

        //okno po straceniu wszystkich żyć
        GridPane gridPrzegrana = new GridPane();
        gridPrzegrana.getStyleClass().add("gridPane");
        Label labelPrzegrana = new Label("Podaj nick: ");
        TextField textPrzeprana = new TextField();
        Label info = new Label();
        Button buttonPrzegrana = new Button("OK");
        gridPrzegrana.add(labelPrzegrana,0,0);
        gridPrzegrana.add(textPrzeprana,0,1);
        gridPrzegrana.add(buttonPrzegrana,0,2);
        gridPrzegrana.add(info,0,3);
        przegrana = new Scene(gridPrzegrana,420,270);
        przegrana.getStylesheets().add("style.css");

        buttonPrzegrana.setOnAction(t->{
            boolean mozna = false;

            info.setText("");
            if(textPrzeprana.getText().equals(""))
                info.setText("Nie podales nicku");
            else
                mozna = true;
            if(mozna){
                String nick = textPrzeprana.getText();
                rank.dodajRanking(nick, Gracz.getTo().getPunkt());
                textPrzeprana.setText("");
                stage.setScene(oknoStartowe);
            }
        });

        //przyciski na oknie startowym
        start.setOnAction(e -> {
            g1 = new Gracz();
            p1 = new Postac();

            Circle circle1 = new Circle(25, 25, 25, Color.YELLOW);
            Circle circle2 = new Circle(25, 25, 25, Color.YELLOW);
            Circle circle3 = new Circle(25, 25, 25, Color.YELLOW);
            Circle circle4 = new Circle(25, 25, 25, Color.YELLOW);


            Button b1 = new Button("Gora Lewa");
            Button b2 = new Button("Gora Prawa");
            Button b3 = new Button("Dol Lewa");
            Button b4 = new Button("Dol Prawa");

            RozbiteJajko j1 = new RozbiteJajko();
            RozbiteJajko j2 = new RozbiteJajko();


            Label label = new Label("Twoje zycia: " + Gracz.getTo().getZycia());
            Label label2 = new Label("Twoje punkty: "+Gracz.getTo().getPunkt());
            GridPane gridPane2 = new GridPane();

            gridPane2.getStyleClass().add("gridPane2");
            gridPane2.add(b1, 0, 0);
            gridPane2.add(b2, 1, 0);
            gridPane2.add(b3, 0, 1);
            gridPane2.add(b4, 1, 1);
            gridPane2.add(p1, 19, 27);
            gridPane2.add(circle1, 7, 15);
            gridPane2.add(circle3, 26, 15);
            gridPane2.add(circle2, 7, 27);
            gridPane2.add(circle4, 26, 27);
            gridPane2.add(label, 25, 0);
            gridPane2.add(label2,25,1);
            gridPane2.add(j1,20,35);
            gridPane2.add(j2,19,35);
            gridPane2.setPadding(new Insets(10, 10, 10, 10));
            gridPane2.setVgap(8);
            gridPane2.setHgap(8);

            gra = new Scene(gridPane2, 1100, 1025);
            gra.getStylesheets().add("gra.css");
            stage.setScene(gra);

            b1.setOnAction(x -> {
                p1.zmianaGoraLewa();
                miejsce = 1;
            });
            b2.setOnAction(x -> {
                p1.zmianaGoraPrawa();
                miejsce = 3;
            });
            b3.setOnAction(x -> {
                p1.zmianaDolLewa();
                miejsce = 2;
            });
            b4.setOnAction(x -> {
                p1.zmianaDolPrawa();
                miejsce = 4;
            });


            KeyCombination keyCombinationWin = new KeyCodeCombination(KeyCode.Q,KeyCombination.CONTROL_DOWN, KeyCombination.CONTROL_DOWN);
            gra.setOnKeyPressed(keyEvent -> {
                if(keyEvent.getCode() == KeyCode.Q) {
                    p1.zmianaGoraLewa();
                    miejsce = 1;
                }

                if(keyEvent.getCode() == KeyCode.A) {
                    p1.zmianaDolLewa();
                    miejsce = 2;
                }

                if(keyEvent.getCode() == KeyCode.W) {
                    p1.zmianaGoraPrawa();
                    miejsce = 3;
                }

                if(keyEvent.getCode() == KeyCode.S) {
                    p1.zmianaDolPrawa();
                    miejsce = 4;
                }

                if(keyCombinationWin.match(keyEvent))
                    stage.setScene(oknoStartowe);

            });

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(1), circle1);
            fadeTransition1.setFromValue(0.0);
            fadeTransition1.setToValue(1.0);

            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2), circle2);
            fadeTransition2.setFromValue(0.0);
            fadeTransition2.setToValue(1.0);

            FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(1), circle3);
            fadeTransition3.setFromValue(0.0);
            fadeTransition3.setToValue(1.0);

            FadeTransition fadeTransition4 = new FadeTransition(Duration.seconds(2), circle4);
            fadeTransition4.setFromValue(0.0);
            fadeTransition4.setToValue(1.0);

            FadeTransition fadeTransition5 = new FadeTransition(Duration.seconds(0.1), circle1);
            fadeTransition5.setFromValue(1.0);
            fadeTransition5.setToValue(0.0);

            FadeTransition fadeTransition6 = new FadeTransition(Duration.seconds(0.1), circle2);
            fadeTransition6.setFromValue(1.0);
            fadeTransition6.setToValue(0.0);

            FadeTransition fadeTransition7 = new FadeTransition(Duration.seconds(0.1), circle3);
            fadeTransition7.setFromValue(1.0);
            fadeTransition7.setToValue(0.0);

            FadeTransition fadeTransition8 = new FadeTransition(Duration.seconds(0.1), circle4);
            fadeTransition8.setFromValue(1.0);
            fadeTransition8.setToValue(0.0);

            FadeTransition fadeTransition9 = new FadeTransition(Duration.seconds(0.01), j1);
            fadeTransition9.setFromValue(1.0);
            fadeTransition9.setToValue(0.0);

            FadeTransition fadeTransition10 = new FadeTransition(Duration.seconds(0.01), j2);
            fadeTransition10.setFromValue(1.0);
            fadeTransition10.setToValue(0.0);

            FadeTransition fadeTransition11 = new FadeTransition(Duration.seconds(1), j1);
            fadeTransition11.setFromValue(0.0);
            fadeTransition11.setToValue(1.0);

            FadeTransition fadeTransition12 = new FadeTransition(Duration.seconds(1), j2);
            fadeTransition12.setFromValue(0.0);
            fadeTransition12.setToValue(1.0);



            fadeTransition9.play();
            fadeTransition10.play();

            PathTransition pathTransition1 = new PathTransition(Duration.seconds(2.2), circle1);
            Path path1 = new Path();
            path1.getElements().add(new MoveTo(0, 0));
            path1.getElements().add(new LineTo(175, 155));

            pathTransition1.setNode(circle1);
            pathTransition1.setPath(path1);

            PathTransition pathTransition2 = new PathTransition(Duration.seconds(2.6), circle2);
            Path path2 = new Path();
            path2.getElements().add(new MoveTo(0, 0));
            path2.getElements().add(new LineTo(175, 135));

            pathTransition2.setNode(circle2);
            pathTransition2.setPath(path2);

            PathTransition pathTransition3 = new PathTransition(Duration.seconds(2.1), circle3);
            Path path3 = new Path();
            path3.getElements().add(new MoveTo(0, 0));
            path3.getElements().add(new LineTo(-260, 175));

            pathTransition3.setNode(circle3);
            pathTransition3.setPath(path3);

            PathTransition pathTransition4 = new PathTransition(Duration.seconds(2.8), circle4);
            Path path4 = new Path();
            path4.getElements().add(new MoveTo(0, 0));
            path4.getElements().add(new LineTo(-240, 135));

            pathTransition4.setNode(circle4);
            pathTransition4.setPath(path4);

            SequentialTransition sequentialTransition = new SequentialTransition();
            sequentialTransition.getChildren().addAll(
                    fadeTransition1, pathTransition1, fadeTransition5
                    , fadeTransition3, pathTransition3, fadeTransition7
            );



            SequentialTransition sequentialTransition2 = new SequentialTransition();
            sequentialTransition2.getChildren().addAll(
                    fadeTransition4, pathTransition4, fadeTransition8
                    , fadeTransition2, pathTransition2, fadeTransition6
            );

            sequentialTransition2.setCycleCount(Animation.INDEFINITE);
            sequentialTransition.setCycleCount(Animation.INDEFINITE);
            sequentialTransition2.play();
            sequentialTransition.play();


            pathTransition1.setOnFinished(z -> {
                if (!(miejsce == 1)) {
                    fadeTransition12.play();

                    Gracz.getTo().utrataZycia();
                    label.setText("Twoje zycia: " + Gracz.getTo().getZycia());
                    if(Gracz.getTo().getZycia() == 0){
                        sequentialTransition.stop();
                        sequentialTransition2.stop();
                        stage.setScene(przegrana);
                    }
                }else {
                    fadeTransition10.play();
                    Gracz.getTo().dodajPunkt();
                    label2.setText("Twoje punkty: "+Gracz.getTo().getPunkt());
                }
            });


            pathTransition2.setOnFinished(z -> {
                if (!(miejsce == 2)) {
                    fadeTransition12.play();

                    Gracz.getTo().utrataZycia();
                    label.setText("Twoje zycia: " + Gracz.getTo().getZycia());
                    if(Gracz.getTo().getZycia() == 0){
                        sequentialTransition.stop();
                        sequentialTransition2.stop();
                        stage.setScene(przegrana);
                    }
                }else {
                    fadeTransition10.play();
                    Gracz.getTo().dodajPunkt();
                    label2.setText("Twoje punkty: "+Gracz.getTo().getPunkt());
                }
            });


            pathTransition3.setOnFinished(z -> {
                if (!(miejsce == 3)) {
                    fadeTransition11.play();

                    Gracz.getTo().utrataZycia();
                    label.setText("Twoje zycia: " + Gracz.getTo().getZycia());
                    if(Gracz.getTo().getZycia() == 0){
                        sequentialTransition.stop();
                        sequentialTransition2.stop();
                        stage.setScene(przegrana);
                    }
                }else {
                    fadeTransition9.play();
                    Gracz.getTo().dodajPunkt();
                    label2.setText("Twoje punkty: "+Gracz.getTo().getPunkt());
                }
            });


            pathTransition4.setOnFinished(z -> {
                if (!(miejsce == 4)) {
                    fadeTransition11.play();
                    Gracz.getTo().utrataZycia();
                    label.setText("Twoje zycia: " + Gracz.getTo().getZycia());
                    if(Gracz.getTo().getZycia() == 0){
                        sequentialTransition.stop();
                        sequentialTransition2.stop();
                        stage.setScene(przegrana);
                    }
                }else {
                    fadeTransition9.play();
                    Gracz.getTo().dodajPunkt();
                    label2.setText("Twoje punkty: "+Gracz.getTo().getPunkt());
                }
            });

        });

        ranking.setOnAction(e -> {
            lista.getItems().clear();
            lista.getItems().addAll(rank.getLista());
            stage.setScene(rankingKoncowy);
        });

        wyjscie.setOnAction(e -> {
            rank.zapis();
            System.exit(0);
        });

        gridPane.add(start, 0, 0);
        gridPane.add(ranking, 1, 0);
        gridPane.add(wyjscie, 2, 0);
    }

}
