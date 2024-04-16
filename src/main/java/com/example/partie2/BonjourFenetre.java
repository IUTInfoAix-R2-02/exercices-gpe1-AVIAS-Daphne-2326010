package com.example.partie2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.event.EventHandler;

public class BonjourFenetre extends Application {

    // Label affichant le message de bienvenue
    private Label helloLabel;

    // Champ de saisi du nom de l'utilisateur
    private TextField nameField;

    // Bouton déclenchant la mise à jour du texte
    private Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Création d'un conteneur VBox avec ses éléments centrés
        VBox vbox = new VBox();
        vbox.setAlignment( Pos.CENTER );

        // Création et ajout du label au conteneur
        this.helloLabel = new Label("Bonjour à tous !");
        vbox.getChildren().add( helloLabel );

        // Ajout d'un champ de saisi de texte de taille 180 pixels
        this.nameField = new TextField("Veuillez saisir un nom");
        nameField.setMaxWidth(180.0d);
        nameField.setFont( Font.font("Courier", FontWeight.NORMAL, 12) );
        vbox.getChildren().add( nameField );

        // Ajout d'un bouton avec du texte
        this.button = new Button();
        vbox.getChildren().add( button );

        // Chargement de l'image
        Image image = new Image( BonjourFenetre.class.getResource("/img/silver_button.png").toString());

        // Création d'un composant avec l'image peinte à l'intérieur
        ImageView iv = new ImageView();
        iv.setImage(image);

        // Intégration de l'image dans le bouton
        button.setGraphic( iv );

        // Gestionnaire d'évènements appelé lors du clic sur le bouton
        EventHandler<MouseEvent> buttonClickHandler = actionEvent -> {
            helloLabel.setText( "Bonjour à toi, "+nameField.getText() );
        };

        // Changement du texte après un clic sur le bouton
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, buttonClickHandler);

        // Création de la scene
        Scene scene = new Scene( vbox );

        // Ajout de la scene à la fenêtre
        primaryStage.setScene( scene );
        primaryStage.setTitle("Hello application");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.show();


    }
}
