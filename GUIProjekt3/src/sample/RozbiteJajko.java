package sample;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RozbiteJajko extends Label{

    private Image img;
    private ImageView view;
    RozbiteJajko(){
        img = new Image("/rozbite.jpg");
        view = new ImageView(img);
        view.setFitHeight(65);
        view.setPreserveRatio(true);
        this.setGraphic(view);
    }

}
