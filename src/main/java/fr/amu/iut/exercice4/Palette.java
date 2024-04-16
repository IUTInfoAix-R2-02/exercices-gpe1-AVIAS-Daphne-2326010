package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        // Conteneur haut
        HBox hbox = new HBox();
        Label label = new Label("Choisis une de ces couleurs");
        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER);
        root.setTop(hbox);

        // conteneur millieu
        Pane panneau = new Pane();
        root.setCenter(panneau);

        // Conteneur bas
        HBox bas = new HBox();
        bas.setAlignment(Pos.CENTER);
        bas.setPadding(new Insets(12,12,12,12));
        bas.setSpacing(12);

        Button vert = new Button("Vert");
        Button rouge = new Button("Rouge");
        Button blue = new Button("Bleu");

        bas.getChildren().addAll(vert,rouge,blue);
        root.setBottom(bas);

        // Changement couleur après clic sur bouton
        vert.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            panneau.setStyle("-fx-background-color: green");
            ++ nbVert;
            label.setText("Vert choisi " + nbVert + " fois");
        });

        rouge.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            panneau.setStyle("-fx-background-color: red");
            ++ nbRouge;
            label.setText("Rouge choisi " + nbRouge + " fois");
        });

        blue.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            panneau.setStyle("-fx-background-color: blue");
            ++ nbBleu;
            label.setText("Bleu choisi " + nbBleu + " fois");
        });

        // Ajout conteneur à la scene
        Scene scene = new Scene(root);

        // Affichage fenêtre
        primaryStage.setScene(scene);
        primaryStage.setWidth(400);
        primaryStage.setHeight(450);
        primaryStage.show();
    }
}

