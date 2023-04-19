package com.example.addressapp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Contact;

import java.time.LocalDate;

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

    @FXML
    private void cancel() {
        this.dialogStage.close();
    }

    @FXML
    private void ok() {
        if (areFormInputsValid()) {
            contacte.setNom(contacte.getNom());
            contacte.setCognoms(contacte.getCognoms());
            contacte.setDomicili(contacte.getDomicili());
            contacte.setCiutat(contacte.getCiutat());
            contacte.setCodiPostal(contacte.getCodiPostal());
            contacte.setDataNaixement(contacte.getDataNaixement());

            okClicked = true;
            dialogStage.close();
        }
    }


    private boolean areFormInputsValid(){
        String errorMessage = "";

        if (nomField.getText() == null || nomField.getText().length() == 0) {
            errorMessage += "No es valid el nom\n";
        }
        if (cognomsField.getText() == null || cognomsField.getText().length() == 0) {
            errorMessage += "El Cognom no es valid!\n";
        }
        if (domiciliField.getText() == null || domiciliField.getText().length() == 0) {
            errorMessage += "No es un carrer correcte!\n";
        }
        if (ciutatField.getText() == null || ciutatField.getText().length() == 0) {
            errorMessage += "No es una Ciudad valida!\n";
        }
        if (codiPostalField.getText() == null || codiPostalField.getText().length() == 0) {
            errorMessage += "No es un codic postal correcte!\n";
        } else {
            try {
                Integer.parseInt(codiPostalField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No es un codic postal correcte (Te que ser un numero)!\n";
            }
        }
        if (dataNaixementField.getText() == null || dataNaixementField.getText().length() == 0) {
            errorMessage += "No es una data apropiada!\n";
        } else {
            if (!DateUtil.validData(dataNaixementField.getText())) {
                errorMessage += "No es una data apropiada  dd.mm.yyyy!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Camps invalids");
            alert.setHeaderText("Perfavor corregi els camps invalids");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void newContacte() {
        Contact tempContacte = new Contact();
        boolean okClicked = this.addressApp.showContactEditDialog(tempContacte);
        if (okClicked) {
            addressApp.getContactes().add(tempContacte);
        }
    }

    public void editContacte(Contact contacte) {
        boolean okClicked = this.addressApp.showContactEditDialog(contacte);
        if (okClicked) {
            this.loadContacte(contacte);
        }
    }





    private AddressApp addressApp;
    private Stage dialogStage;
    private boolean okClicked = false;

    private Contact contacte;






    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }

    public void setContacte(Contact contacte) {

        this.nomField.setText(contacte.getNom().get());
        this.cognomsField.setText(contacte.getCognoms().get());
        this.domiciliField.setText(contacte.getDomicili().get());
        this.ciutatField.setText(contacte.getCiutat().get());
        this.codiPostalField.setText(Integer.toString(contacte.getCodiPostal().get()));
        this.dataNaixementField.setText(DateUtil.format(contacte.getDataNaixement().get()));
    }

    public boolean getOkClicked() {
        return this.okClicked;
    }

    public void loadContacte(Contact contacte) {
        this.contacte = contacte;
        nomField.setText(contacte.getNom().get());
        cognomsField.setText(contacte.getCognoms().get());
        domiciliField.setText(contacte.getDomicili().get());
        ciutatField.setText(contacte.getCiutat().get());
        codiPostalField.setText(Integer.toString(contacte.getCodiPostal().get()));
        dataNaixementField.setText(DateUtil.format(contacte.getDataNaixement().get()));
    }
}

