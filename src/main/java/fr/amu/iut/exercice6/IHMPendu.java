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
        vbox.setStyle("-fx-background-color: #c9ffff");          // couleur du fond

        //image
        img = new Label();
        ImageView p7 = new ImageView("/exercice6/pendu7.png");
        img.setGraphic(p7);                                     // l'image par défaut sera l'image pendu7.png

        // nombre de vie
        vie = 7;                                                // le nombre de vies de départ est de 7
        nbVie = new Label("Nombre de vies : " + vie);        // affichage du nombre de vies
        nbVie.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20px;");

        // mot à deviner
        Dico dico = new Dico();                                 // création d'un dico
        mot = dico.getMot();                                    // récupération d'un mot

        motCache = "";
        for(int i = 0; i<mot.length(); ++i){                    // le mot caché prend un nombre d'étoile égale au
            motCache += '*';                                    // nombre de lettre dans le mot
        }

        motADeviner = new Label(motCache);                      // affichage du mot à deviner
        motADeviner.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-font-size: 30px;");

        // lignes du clavier
        HBox voyelles = new HBox();
        HBox consonnes1 = new HBox();
        HBox consonnes2 = new HBox();

        HBox[] lignesLettres = {voyelles, consonnes1, consonnes2};
        String[] strLettres = {"aeiouy", "bcdfghjklm", "npqrstvwxz"};

        for(int i = 0; i<3 ; i++){                              // création des boutons pour chaque lettre
            for (char l : strLettres[i].toCharArray()) {
                Rectangle rect = new Rectangle(30,30);
                rect.setArcHeight(15);
                rect.setArcWidth(15);

                Button btn = new Button(l+"");              // affichage des boutons
                btn.setShape(rect);
                btn.setMinSize(48,48);
                btn.setStyle(
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 20px;" +
                        "-fx-text-fill: darkturquoise;" +
                        "-fx-border-color: orange;" +
                        "-fx-background-color: transparent;");
                btn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> lettreDansMot(btn));
                lignesLettres[i].getChildren().add(btn);        // chaque chaîne de bouton est ajoutée à une hbox
            }
            lignesLettres[i].setAlignment(Pos.CENTER);
        }

        // clavier
        VBox clavier = new VBox();
        clavier.getChildren().addAll(lignesLettres);            // récupération des hbox pour former le clavier
        clavier.setAlignment(Pos.CENTER);

        // rejouer
        btnChoisi = new ArrayList<>();

        Rectangle rectRejoue = new Rectangle(40,35);
        rectRejoue.setArcHeight(35);
        rectRejoue.setArcWidth(20);

        Button rejouer = new Button("Rejouer");             // affichage du bouton Rejouer
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
            img.setGraphic(p7);                               // l'image de départ est remise
            vie = 7;                                          // le nombre de vie est remis à 7
            nbVie.setText("Nombre de vies : " + vie);         // l'affichage du nombre de vie également
            mot = dico.getMot();                              // récupération d'un nouveau mot

            motCache = "";                                    // on recache le nouveau mot
            for(int i = 0; i<mot.length(); ++i){
                motCache += '*';
            }
            motADeviner.setText(motCache);

            for (Button btn : btnChoisi) {                    // l'affichage des boutons choisis est remis à l'origine
                btn.setStyle(
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 20px;" +
                        "-fx-text-fill: darkturquoise;" +
                        "-fx-border-color: orange;" +
                        "-fx-background-color: transparent;");
            }
            btnChoisi.clear();                                // la liste des boutons choisis est vidé
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
        if (vie == 0 || btnChoisi.contains(btn)) return;    // si le nombre de vie est à 0 et que le bouton choisi
                                                            // est déjà dans la liste, on ne fait rien

        char l = btn.getText().charAt(0);                   // on récupère la lettre choisie dans une variable
        char[] lettresDuMot = mot.toCharArray();            // on transforme le mot en chaine de lettres
        char[] lettresDuMotCache = motCache.toCharArray();  // on transforme le mot caché également

        for(int i=0; i<lettresDuMot.length; ++i){           // on dévoile la ou les lettre(s) du mot caché si la lettre
            if(lettresDuMot[i] == l){                       // choisi correspond
                lettresDuMotCache[i] = l;
            }
        }

        String nouveauMotCache = new String(lettresDuMotCache);
        if (motCache.equals(nouveauMotCache)) --vie;        // si rien de nouveau n'a été dévoilé, c'est-à-dire que la
        else motCache = nouveauMotCache;                    // lettre n'est pas dans le mot, on enlève donc une vie

        btnChoisi.add(btn);                                 // rajout du bouton dans la liste des boutons choisis

        btn.setStyle(                                       // le bouton choisi devient légèrement transparent
                "-fx-opacity: 0.5;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20px;" +
                "-fx-text-fill: darkturquoise;" +
                "-fx-border-color: orange;" +
                "-fx-background-color: transparent;");
        maj();
    }

    // fait la mise à jour d'éléments de la fenêtre
    public void maj(){
        motADeviner.setText(motCache);                      // affichage du mot à deviner lorsqu'une lettre est dévoilée
                                                            // ou non

        if (motCache.equals(mot)) {                         // si toute les lettres sont dévoilées, la partie est gagnée
            img.setGraphic(new ImageView("/exercice6/penduWin.png"));
            return;
        }
        nbVie.setText("Nombre de vies : " + vie);           // affichage du nombre de vie lorsqu'une vie est perdue ou
                                                            // non

        img.setGraphic(new ImageView("/exercice6/pendu" + vie + ".png"));   // l'image dépend du nombre de vie
    }

    public static void main(String[] args) {
        launch(args);
    }
}
