package fr.amu.iut.exercice15;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane {
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button okBtn;

    public void initialize() {createBindings();}

    private void createBindings() {
        BooleanProperty idOk = new SimpleBooleanProperty();
        BooleanProperty cancelPossible = new SimpleBooleanProperty();
        BooleanProperty okPossible = new SimpleBooleanProperty();

        idOk.bind(
                Bindings.createBooleanBinding(
                        () -> userId.getText().length()>5,
                        userId.textProperty()
                )
        );

        cancelPossible.bind(
                Bindings.createBooleanBinding(
                        () -> userId.getText().length()==0 && pwd.getText().length()==0,
                        userId.textProperty(), pwd.textProperty()
                )
        );

        okPossible.bind(
                Bindings.createBooleanBinding(
                        () -> {
                            if (pwd.getLength()<7) return true;
                            boolean hasUppercase = pwd.getText().chars().anyMatch(Character::isUpperCase);
                            boolean hasDigit = pwd.getText().chars().anyMatch(Character::isDigit);
                            return !(hasUppercase && hasDigit); // renvoie vrai si pas de majuscule & pas de chiffre (bonton inaccessible)
                        },                                      // renvoie faux si maj & chiffre (bouton accessible)
                        pwd.textProperty()
                )
        );

        okBtn.disableProperty().bind(okPossible);
        cancelBtn.disableProperty().bind(cancelPossible);
        pwd.editableProperty().bind(idOk);
    }

    @FXML
    private void okClicked() {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray()) {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked() {
        userId.setText("");
        pwd.setText("");
    }
}