package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {
        // creation conteneur principale
        GridPane grille = new GridPane();
        grille.setGridLinesVisible(true);       // affichage des lignes de la grille

        // generation d'un nombre aléatoire
        Random random = new Random();
        // pour chaque case on crée ce qu'elle contient
        for (int i=0; i<9; ++i){
            switch (random.nextInt(3)){
                case 0:
                    grille.add(new Label(
                            "",
                            new ImageView("exercice2/Croix.png")),i/3,i%3);
                    break;
                case 1:
                    grille.add(new Label(
                            "",
                            new ImageView("exercice2/Rond.png")),i/3,i%3);
                    break;
                case 2:
                    grille.add(new Label(
                            "",
                            new ImageView("exercice2/Vide.png")),i/3,i%3);
                    break;
            }
        }

        Scene scene = new Scene(grille);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}

