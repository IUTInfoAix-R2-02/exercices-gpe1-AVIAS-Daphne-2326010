package fr.amu.iut.exercice5;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.TimeUnit.SECONDS;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;
    
    public static ArrayList<Obstacle> obstacles;

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();
        // on positionne le fantôme 20 positions vers la droite
        fantome.setLayoutX(62 * 10);
        pacman.setLayoutY(46 * 10);

        //Obstacles
        obstacles = new ArrayList<Obstacle>();
        Obstacle mur1 = new Obstacle(19 * 20, 7 * 20, 60, 260);
        Obstacle mur2 = new Obstacle(6 * 20, 3 * 20, 60, 200);
        obstacles.add(mur1);
        obstacles.add(mur2);

        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        jeu.getChildren().addAll(obstacles);
        root.setCenter(jeu);

        Label chrono = new Label("Temps restant : 10");
        root.setTop(chrono);

        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);

        //creation minuterie

        /*
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable runnable = new Runnable() {
            int countdownStarter = 20;

            public void run() {
                chrono.setText("Time left: " + countdownStarter);
                countdownStarter--;

                if(countdownStarter<0){
                    root.setTop(new Label("Time Over!"));
                    scheduler.shutdown();
                }
            }
        }; scheduler.scheduleAtFixedRate(runnable,0,1,SECONDS);
*/

        /*
        Timer chrono = new Timer();
        chrono.scheduleAtFixedRate(new TimerTask(){
            int i = 10;
            public void run() {
                    root.setTop(new Label("Time left: " + i));
                    i--;

                    if (i<0){
                        chrono.cancel();
                        root.setTop(new Label("Time over"));
                };
            }
        }, 0, 10000);
         */

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome, obstacles);

        primaryStage.setTitle("... Pac Man ...");

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1
     * @param j2
     */
    private void deplacer(Personnage j1, Personnage j2, ArrayList<Obstacle> obstacles) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    j1.deplacerAGauche();
                    break;
                case RIGHT:
                    j1.deplacerADroite(scene.getWidth());
                    break;
                case DOWN:
                    j1.deplacerEnBas(scene.getHeight());
                    break;
                case UP:
                    j1.deplacerEnHaut();
                    break;
                case Q:
                    j2.deplacerAGauche();
                    break;
                case Z:
                    j2.deplacerEnHaut();
                    break;
                case D:
                    j2.deplacerADroite(scene.getWidth());
                    break;
                case S:
                    j2.deplacerEnBas(scene.getHeight());
                    break;
            }

            if (j1.estEnCollision(j2))
                System.exit(0);

        });
    }


}
