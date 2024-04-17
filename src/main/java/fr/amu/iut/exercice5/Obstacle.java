package fr.amu.iut.exercice5;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

public class Obstacle extends Rectangle {

    public Obstacle(double x, double y, double l, double h) {
        super(x, y, l, h);
        super.setFill(Color.ROSYBROWN);
    }
}
