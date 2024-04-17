package fr.amu.iut.exercice5;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.Iterator;

class Personnage extends Group {
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 10;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private final Circle corps;
    private String direction;

    public Personnage(String direction, Color couleurContour, Color couleurRemplissage) {
        this.direction = direction;
        corps = new Circle(10, 10, LARGEUR_MOITIE_PERSONNAGE, couleurContour);
        corps.setFill(couleurRemplissage);
        getChildren().add(corps);
    }

    public void deplacerAGauche() {
        //    ****
        //   *    *
        //  *---   *
        //   *    *
        //    ****

        //déplacement <----
        if (getLayoutX() >= LARGEUR_PERSONNAGE) {
            double x = getLayoutX();
            setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
            if (dansObstacle()) setLayoutX(x);
        }
        if (!direction.equals("gauche")) {
            direction = "gauche";
        }
    }

    public void deplacerADroite(double largeurJeu) {
        //    ****
        //   *    *
        //  *   ---*
        //   *    *
        //    ****
        //déplacement ---->
        if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE) {
            double x = getLayoutX();
            setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
            if (dansObstacle()) setLayoutX(x);
        }
        if (!direction.equals("droite")) {
            direction = "droite";
        }
    }

    public void deplacerEnBas(double hauteurJeu) {
        //    *****
        //   *     *
        //  *   |   *
        //   *  |  *
        //    *****
    if (getLayoutY() < hauteurJeu - LARGEUR_PERSONNAGE){
        double y = getLayoutY();
        setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
        if (dansObstacle()) setLayoutY(y);
    }
    if (!direction.equals("haut")){
        direction = "haut";
    }
}

    public void deplacerEnHaut() {
        //    *****
        //   *  |  *
        //  *   |   *
        //   *     *
        //    *****
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            double y = getLayoutY();
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
            if (dansObstacle()) setLayoutY(y);
        }
        if (!direction.equals("bas")){
            direction = "bas";
        }

    }

    boolean estEnCollision(Personnage autrePersonnage) {
        return getBoundsInParent().contains(autrePersonnage.getBoundsInParent())
                || autrePersonnage.getBoundsInParent().contains(getBoundsInParent());
    }

    boolean dansObstacle(){
        Iterator<Obstacle> it = JeuMain.obstacles.iterator();
        Bounds posPerso = getBoundsInParent();
        while(it.hasNext()){
            Bounds posObstacle = it.next().getBoundsInParent();
            if (posObstacle.getMinX() <= posPerso.getMinX()
            && posPerso.getMaxX() <= posObstacle.getMaxX()
            && posObstacle.getMinY() <= posPerso.getMinY()
            && posObstacle.getMaxY() >= posPerso.getMaxY()) return true;
        }
        return false;
    }
}
