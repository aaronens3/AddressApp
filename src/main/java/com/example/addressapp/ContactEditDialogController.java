package com.example.addressapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Contact;

public class ContactEditDialogController {

    @FXML
    private TextField nomField;
    @FXML
    private TextField cognomsField;
    @FXML
    private TextField domiciliField;
    @FXML
    private TextField ciutatField;
    @FXML
    private TextField codiPostalField;
    @FXML
    private TextField dataNaixementField;






    private AddressApp addressApp;
    private Stage dialogStage;
    private boolean okClicked;






    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }

    public void setContacte(Contact contacte) {
        this.nomField.setText(String.valueOf(contacte.getNom()));
        this.cognomsField.setText(String.valueOf(contacte.getCognoms()));
        this.domiciliField.setText(String.valueOf(contacte.getDomicili()));
        this.ciutatField.setText(String.valueOf(contacte.getCiutat()));
        this.codiPostalField.setText(String.valueOf(contacte.getCodiPostal()));
        this.dataNaixementField.setText(String.valueOf(contacte.getDataNaixement()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }
}

