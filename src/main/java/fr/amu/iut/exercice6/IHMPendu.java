package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class IHMPendu extends Application {
    private String mot;
    private String motCache;
    private Label motADeviner;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        //conteneur du centre
        VBox vbox = new VBox();
            //image
        Label img = new Label();
        ImageView pendu7 = new ImageView (IHMPendu.class.getResource("/exercice6/pendu7.png").toString());
        img.setGraphic(pendu7);

            //text de saisie
        //TextField lettre = new TextField();

            //nombre de vie
        int vie = 7;
        Label nbVie = new Label("Nombre de vies : " + vie);

            //mot à deviner
        Dico dico = new Dico();

        mot = dico.getMot();
        System.out.println(mot);
        motCache = "";
        for(int i = 0; i<mot.length(); ++i){
            motCache += '*';
        }
        motADeviner = new Label(motCache);


        HBox voyelles = new HBox();
        HBox consonnes1 = new HBox();
        HBox consonnes2 = new HBox();

        HBox[] lignesLettres = {voyelles, consonnes1, consonnes2};
        String[] strLettres = {"aeiouy", "bcdfghjklm", "npqrstvwxz"};

        for(int i = 0; i<3 ; i++){
            for (char l : strLettres[i].toCharArray()) {
                Button btn = new Button(l+"");
                btn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    lettreDansMot(btn);
                });
                lignesLettres[i].getChildren().add(btn);
            }
            lignesLettres[i].setAlignment(Pos.CENTER);
        }

        VBox clavier = new VBox();
        clavier.getChildren().addAll(lignesLettres);
        clavier.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(img,nbVie,motADeviner,clavier);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        root.setCenter(vbox);

        //changement du motCache apres tentative


        //ajout scene
        Scene scene = new Scene(root);

        // affichage fenetre
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(550);
        primaryStage.show();
    }

    //vérifie si la lettre choisi est dans le mot
    public void lettreDansMot(Button btn){
        char l = btn.getText().charAt(0);       //on récupère la lettre choisie dans une variable
        char[] lettresDuMot = mot.toCharArray();        //on transforme le mot en chaine de lettres
        char[] lettresDuMotCache = motCache.toCharArray();
        for(int i=0; i<lettresDuMot.length; ++i){
            if(lettresDuMot[i] == l){
                lettresDuMotCache[i] = l;
            }
        }
        motCache = new String(lettresDuMotCache);
        System.out.println(motCache);
        maj();
    }

    public void maj(){
        motADeviner.setText(motCache);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
