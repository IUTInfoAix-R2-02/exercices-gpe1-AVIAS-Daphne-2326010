package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class LoginControl extends GridPane {
    @FXML
    PasswordField pwd;
    @FXML
    TextField ide;

    @FXML
    private void okClicked() {
        String mdp = "";
        for (int i=0; i<pwd.getLength(); ++i){
            mdp += "*";
        }
        System.out.println(ide.getText());
        System.out.println(mdp);
    }

    @FXML
    private void cancelClicked() {
        ide.clear();
        pwd.clear();
    }
}