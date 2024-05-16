package fr.amu.iut.exercice7;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CounterController implements Initializable {

    int counter = 0;
    @FXML
    private Label counterLabel = new Label("0");
    @FXML
    private Button decrementButton;
    @FXML
    private Button incrementButton;

    public void increment() {
        counter += 1;
        counterLabel.setText(counter+"");
    }

    public void decrement() {
        counter -= 1;
        counterLabel.setText(counter+"");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing CounterController...");
   }
}
