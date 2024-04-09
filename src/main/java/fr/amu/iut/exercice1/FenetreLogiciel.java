package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.*;


public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création conteneur principal
        BorderPane root = new BorderPane();

        // Création conteneur : ligne de contrôle du haut
            // sous-items de file
        MenuItem menuItem1 = new MenuItem("New");
        MenuItem menuItem2 = new MenuItem("Open");
        MenuItem menuItem3 = new MenuItem("Save");
        MenuItem menuItem4 = new MenuItem("Close");
            // sous-items de edit
        MenuItem menuItem5 = new MenuItem("Cut");
        MenuItem menuItem6 = new MenuItem("Copy");
        MenuItem menuItem7 = new MenuItem("Paste");
            // la bar de menu
        Menu menu1 = new Menu("File");
        menu1.getItems().addAll(menuItem1,menuItem2,menuItem3,menuItem4);
        Menu menu2 = new Menu("Edit");
        menu2.getItems().addAll(menuItem5,menuItem6,menuItem7);
        Menu menu3 = new Menu("Help");
        MenuBar menuBar = new MenuBar(menu1,menu2,menu3);

        root.setTop(menuBar);

        // Création conteneur : bas
        HBox lowMenu = new HBox();
        lowMenu.setAlignment(Pos.CENTER);
        Label text = new Label("Ceci est un label de bas de page");
        lowMenu.getChildren().addAll(text);

        root.setBottom(lowMenu);

        // Création conteneur : gauche
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20,0,20,20));
        Label btn = new Label("Boutons :");
        Button btn1 = new Button("Bouton 1");
        Button btn2 = new Button("Bouton 2");
        Button btn3 = new Button("Bouton 3");

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setAlignment(Pos.CENTER);
        VBox.setVgrow(vbButtons,Priority.ALWAYS);
        vbButtons.getChildren().addAll(btn,btn1,btn2,btn3);

        HBox sep = new HBox();
        sep.getChildren().addAll(vbButtons, new Separator(Orientation.VERTICAL));

        root.setLeft(sep);

        // Création conteneur central
            // FORMULAIRE
        GridPane formulaire = new GridPane();
        Label name = new Label("Name: ");
        Label email = new Label("Email: ");
        Label passwd = new Label("Password: ");
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField passwdField = new TextField();

        GridPane.setConstraints(name,0,0);
        GridPane.setConstraints(email,0, 1);
        GridPane.setConstraints(passwd, 0, 2);
        GridPane.setConstraints(nameField, 1, 0);
        GridPane.setConstraints(emailField, 1, 1);
        GridPane.setConstraints(passwdField, 1, 2);

        formulaire.getChildren().addAll(name,email,passwd,nameField,emailField,passwdField);
        //VBox.setVgrow(formulaire,Priority.ALWAYS);

        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        HBox btnFormulaire = new HBox();
        btnFormulaire.getChildren().addAll(submit,cancel);

        VBox centre = new VBox();
        centre.getChildren().addAll(formulaire,btnFormulaire);


        btnFormulaire.setAlignment(Pos.CENTER);
        formulaire.setAlignment(Pos.CENTER);
        centre.setAlignment(Pos.CENTER);
        formulaire.setVgap(10);
        centre.setSpacing(10);

        root.setCenter(centre);

        // Séparator
        Separator separator = new Separator();

        // Ajout conteneur à la scene
        Scene scene = new Scene(root);

        // Ajout scene
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");

        // Affichage fenêtre
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}

