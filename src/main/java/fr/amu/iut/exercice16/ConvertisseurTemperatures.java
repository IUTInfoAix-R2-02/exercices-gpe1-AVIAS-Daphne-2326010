package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.NumberFormat;

public class ConvertisseurTemperatures extends Application {

    private Slider celsius;
    private Slider fahrenheit;
    private TextField celsiusTextField;
    private TextField fahrenheitTextField;
    @Override
    public void start(Stage primaryStage) {
        VBox panneauCelsius = new VBox(30);
        VBox panneauFahrenheit = new VBox(30);

        panneauCelsius.setAlignment(Pos.CENTER);
        panneauFahrenheit.setAlignment(Pos.CENTER);


        Label celsiusLabel = new Label("°C");
        Label fahrenheitLabel = new Label("°F");

        celsiusLabel.setStyle("-fx-font-weight: bold");
        fahrenheitLabel.setStyle("-fx-font-weight: bold");


        celsius = new Slider(0,100,0.00);
        fahrenheit = new Slider(0,212,32.00);

        celsius.setOrientation(Orientation.VERTICAL);
        celsius.setShowTickLabels(true);
        celsius.setShowTickMarks(true);
        celsius.setMajorTickUnit(10f);
        celsius.setPrefHeight(800);

        fahrenheit.setOrientation(Orientation.VERTICAL);
        fahrenheit.setShowTickLabels(true);
        fahrenheit.setShowTickMarks(true);
        fahrenheit.setMajorTickUnit(10f);
        fahrenheit.setPrefHeight(800);


        TextField celsiusTextField = new TextField("0,00");
        TextField fahrenheitTextField = new TextField("32,00");

        celsiusTextField.setMaxWidth(55);
        fahrenheitTextField.setMaxWidth(55);

        panneauCelsius.getChildren().addAll(celsiusLabel,celsius,celsiusTextField);
        panneauFahrenheit.getChildren().addAll(fahrenheitLabel, fahrenheit, fahrenheitTextField);

        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        createBindings();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void createBindings() {
        /*
        DoubleBinding celsiusToFahrenheit = new DoubleBinding() {
            {
                this.bind(celsius.valueProperty());
            }
            @Override
            protected double computeValue() {
                return (celsius.getValue()*9)/5 + 32;
            }
        };

        DoubleBinding fahrenheitToCelsius = new DoubleBinding() {
            {
                this.bind(fahrenheit.valueProperty());
            }
            @Override
            protected double computeValue() {
                return (fahrenheit.getValue()-32)*(5/9);
            }
        };

        fahrenheit.valueProperty().bind(Bindings.bindBidirectional(celsiusToFahrenheit, fahrenheitToCelsius));
    */

        celsius.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                fahrenheit.adjustValue(celsius.getValue()*1.8+32);
            }
        });
        fahrenheit.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                celsius.adjustValue((fahrenheit.getValue()-32)/1.8);
            }
        });

    }


}