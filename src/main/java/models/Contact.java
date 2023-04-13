package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Contact {
    private SimpleStringProperty nom;
    private SimpleStringProperty cognoms;
    private SimpleStringProperty domicili;
    private SimpleStringProperty ciutat;
    private SimpleIntegerProperty codiPostal;
    private SimpleObjectProperty<LocalDate> dataNaixement;

    public Contact (String nom, String cognoms, String domicili, String ciutat, int codiPostal, int dia, int mes, int any) {
        this.nom = new SimpleStringProperty(nom);
        this.cognoms = new SimpleStringProperty(cognoms);
        this.domicili = new SimpleStringProperty(domicili);
        this.ciutat = new SimpleStringProperty(ciutat);
        this.codiPostal = new SimpleIntegerProperty(codiPostal);
        this.dataNaixement = new SimpleObjectProperty<>(
                LocalDate.of(any, mes, dia));
    }

    public Contact(){
        this.nom = new SimpleStringProperty("");
        this.cognoms = new SimpleStringProperty("");
        this.domicili = new SimpleStringProperty("");
        this.ciutat = new SimpleStringProperty("");
        this.codiPostal = new SimpleIntegerProperty(-1);
        this.dataNaixement = new SimpleObjectProperty<>(null);
    }

    public SimpleStringProperty getNom() {
        return nom;
    }

    public void setNom (SimpleStringProperty nom) {
        this.nom = nom;
    }

    public SimpleStringProperty getCognoms() {
        return cognoms;
    }

    public void setCognoms (SimpleStringProperty cognoms) {
        this.cognoms = cognoms;
    }

    public SimpleStringProperty getDomicili() {
        return domicili;
    }

    public void setDomicili (SimpleStringProperty domicili) {
        this.domicili = domicili;
    }

    public SimpleStringProperty getCiutat() {
        return ciutat;
    }

    public void setCiutat (SimpleStringProperty ciutat) {
        this.ciutat = ciutat;
    }

    public SimpleIntegerProperty getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal (SimpleIntegerProperty codiPostal) {
        this.codiPostal = codiPostal;
    }

    public SimpleObjectProperty<LocalDate> getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement (SimpleObjectProperty<LocalDate> dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

}
