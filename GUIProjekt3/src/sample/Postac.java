package sample;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Postac extends Label {
    private Image img;
    private ImageView view;
    Postac(){
        img = new Image("/goralewa.jpg");
        view = new ImageView(img);
        view.setFitHeight(350);
        view.setPreserveRatio(true);
        this.setGraphic(view);
    }

    void zmiana(String path){
        view.setPreserveRatio(false);
        img = new Image(path);
        view = new ImageView(img);
        view.setFitHeight(350);
        view.setPreserveRatio(true);
        this.setGraphic(view);
    }
    void zmianaGoraLewa(){
        zmiana("/goralewa.jpg");

    }
    void zmianaGoraPrawa(){
        zmiana("/goraprawa.jpg");
    }
    void zmianaDolLewa(){
        zmiana("/dollewa.jpg");
    }
    void zmianaDolPrawa(){
        zmiana("/dolprawa.jpg");
    }
}
