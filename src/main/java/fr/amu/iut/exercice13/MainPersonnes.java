package fr.amu.iut.exercice13;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@SuppressWarnings("Duplicates")
public class MainPersonnes  {

    private static ObservableList<Personne> lesPersonnes;

    private static ListChangeListener<Personne> unChangementListener;

    public static void main(String[] args) {

        lesPersonnes = FXCollections.observableArrayList(personne -> new Observable[] {personne.ageProperty()});

        unChangementListener = new ListChangeListener<Personne>(){
            @Override
            public void onChanged(Change<? extends Personne> change) {
                if(change.next()) {
                    if (change.wasAdded()) System.out.println(change.getAddedSubList().get(0).getNom());
                    if (change.wasRemoved()) System.out.println(change.getRemoved().get(0).getNom());
                    if (change.wasUpdated()) {
                        Personne personne = change.getList().get(change.getFrom());
                        System.out.println(personne.getNom() + " a maintenant " + personne.getAge());
                    }
                }
            }
        };
        ListChangeListener<Personne> plusieursChangementsListener = new ListChangeListener<Personne>() {
            @Override
            public void onChanged(Change<? extends Personne> change) {
                if(change.next()) {
                    if (change.wasAdded()){
                        for(Personne personne : change.getAddedSubList()) System.out.println(personne.getNom());
                    }
                    if (change.wasRemoved()){
                        for(Personne personne : change.getRemoved()) System.out.println(personne.getNom());
                    }
                    if (change.wasUpdated()) {
                        for(int i = change.getFrom(); i< change.getTo(); ++i) {
                            Personne personne = change.getList().get(i);
                            System.out.println(personne.getNom() + " a maintenant " + personne.getAge());
                        }
                    }
                }
            }
        };

        //lesPersonnes.addListener(unChangementListener);
        lesPersonnes.addListener(plusieursChangementsListener);
        //question1();
        //question2();
        //question3();
        question5();
    }

    public static void question1() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.remove(paul);
    }

    public static void question3() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        paul.setAge(5);
    }

    public static void question5() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.addAll(pierre, paul, jacques);
        for (Personne p : lesPersonnes)
            p.setAge(p.getAge()+10);
        lesPersonnes.removeAll(paul, pierre);
    }
}

