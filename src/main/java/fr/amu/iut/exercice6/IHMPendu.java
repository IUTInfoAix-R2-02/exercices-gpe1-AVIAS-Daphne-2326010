package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;


public class IHMPendu extends Application {
    private String mot;
    private String motCache;
    private Label motADeviner;
    private int vie;
    private Label nbVie;
    private Label img;
    private ArrayList<Button> btnChoisi;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: #c9ffff");
        //image
        img = new Label();
        ImageView p7 = new ImageView("/exercice6/pendu7.png");
        img.setGraphic(p7);

        // champ de saisie
        //TextField lettre = new TextField();

        // nombre de vie
        vie = 7;
        nbVie = new Label("Nombre de vies : " + vie);
        nbVie.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");

        // mot à deviner
        Dico dico = new Dico();

        mot = dico.getMot();
        //System.out.println(mot);
        motCache = "";
        for(int i = 0; i<mot.length(); ++i){
            motCache += '*';
        }
        motADeviner = new Label(motCache);
        motADeviner.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-font-size: 30px;");

        // lignes du clavier
        HBox voyelles = new HBox();
        HBox consonnes1 = new HBox();
        HBox consonnes2 = new HBox();

        HBox[] lignesLettres = {voyelles, consonnes1, consonnes2};
        String[] strLettres = {"aeiouy", "bcdfghjklm", "npqrstvwxz"};


        for(int i = 0; i<3 ; i++){
            for (char l : strLettres[i].toCharArray()) {
                Rectangle rect = new Rectangle(30,30);
                rect.setArcHeight(15);
                rect.setArcWidth(15);
                Button btn = new Button(l+"");
                btn.setShape(rect);
                btn.setMinSize(48,48);
                btn.setStyle(
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 20px;" +
                        "-fx-text-fill: darkturquoise;" +
                        "-fx-border-color: orange;" +
                        "-fx-background-color: transparent;");
                btn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> lettreDansMot(btn));
                lignesLettres[i].getChildren().add(btn);
            }
            lignesLettres[i].setAlignment(Pos.CENTER);
        }

        // clavier
        VBox clavier = new VBox();
        clavier.getChildren().addAll(lignesLettres);
        clavier.setAlignment(Pos.CENTER);

        // rejouer
        btnChoisi = new ArrayList<>();
        Rectangle rectRejoue = new Rectangle(40,35);
        rectRejoue.setArcHeight(35);
        rectRejoue.setArcWidth(20);
        Button rejouer = new Button("Rejouer");
        rejouer.setShape(rectRejoue);
        rejouer.setMinSize(40,35);
        rejouer.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-border-width: 2;" +
                "-fx-font-size: 20px;" +
                "-fx-text-fill: orange;" +
                "-fx-border-color: darkturquoise;" +
                "-fx-background-color: transparent;");
        rejouer.setAlignment(Pos.CENTER);
        rejouer.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            img.setGraphic(p7);
            vie = 7;
            nbVie.setText("Nombre de vies : " + vie);
            mot = dico.getMot();
            motCache = "";
            for(int i = 0; i<mot.length(); ++i){
                motCache += '*';
            }
            motADeviner.setText(motCache);
            for (Button btn : btnChoisi) {
                btn.setStyle(
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 20px;" +
                        "-fx-text-fill: darkturquoise;" +
                        "-fx-border-color: orange;" +
                        "-fx-background-color: transparent;");
            }
            btnChoisi.clear();
        });

        vbox.getChildren().addAll(img,nbVie,motADeviner,clavier,rejouer);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        root.setCenter(vbox);

        // ajout scene
        Scene scene = new Scene(root);

        // affichage fenetre
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(550);
        primaryStage.show();
    }

    // vérifie si la lettre choisie est dans le mot
    public void lettreDansMot(Button btn){
        if (vie == 0 || btnChoisi.contains(btn)) return;
        char l = btn.getText().charAt(0);       //on récupère la lettre choisie dans une variable
        char[] lettresDuMot = mot.toCharArray();        //on transforme le mot en chaine de lettres
        char[] lettresDuMotCache = motCache.toCharArray();
        // on dévoile la lettre du mot caché si la lettre choisie est séléctionnée
        for(int i=0; i<lettresDuMot.length; ++i){
            if(lettresDuMot[i] == l){
                lettresDuMotCache[i] = l;
            }
        }
        String nouveauMotCache = new String(lettresDuMotCache);

        // si rien n'a été changé, la lettre n'est pas dans le mot alors on enlève une vie
        if (motCache.equals(nouveauMotCache)) --vie;
        else motCache = nouveauMotCache;

        // on rajoute le bouton utilisé dans la liste des boutons choisis
        btnChoisi.add(btn);

        // le bouton choisi devient légèrement transparent
        btn.setStyle(
                "-fx-opacity: 0.5;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20px;" +
                "-fx-text-fill: darkturquoise;" +
                "-fx-border-color: orange;" +
                "-fx-background-color: transparent;");
        //System.out.println(motCache);
        maj();
    }

    public void maj(){
        motADeviner.setText(motCache);
        if (motCache.equals(mot)) {
            img.setGraphic(new ImageView("/exercice6/penduWin.png"));
            return;
        }
        nbVie.setText("Nombre de vies : " + vie);
        img.setGraphic(new ImageView("/exercice6/pendu" + vie + ".png"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
