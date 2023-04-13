package com.example.addressapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Contact;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    @FXML
    private TableView<Contact> contactTable;
    @FXML
    private TableColumn<Contact, String> nomColumn;
    @FXML
    private TableColumn<Contact, String> cognomsColumn;
    @FXML
    private Label nomLabel;
    @FXML
    private Label cognomsLabel;
    @FXML
    private Label domiciliLabel;
    @FXML
    private Label ciutatLabel;
    @FXML
    private Label codiPostalLabel;
    @FXML
    private Label dataNaixementLabel;

    @FXML
    private void deleteContact () {
        Alert alert;
        int selectedIndex = contactTable.getSelectionModel().getSelectedIndex();

        if (!contactTable.getItems().isEmpty() && selectedIndex >= -1){
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar el esborrat");
            alert.setHeaderText("Esta opció es no es pot desfer");
            alert.setContentText("Estas segur que vols esborrar el contacte?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                contactTable.getItems().remove(selectedIndex);
            }
        }else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No hi ha contactes seleccionats");
            alert.setContentText("Selecciona un contacte per poder esborrar-lo");
            alert.showAndWait();
        }
    }


    private AddressApp addressApp;

    @Override
    public void initialize (URL url, ResourceBundle rb){
        nomColumn.setCellValueFactory(cellData -> cellData.getValue().getNom());
        cognomsColumn.setCellValueFactory(cellData -> cellData.getValue().getCognoms());
        showContactDetails(null);
        contactTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContactDetails(newValue));
    }

    public void setAddressApp (AddressApp addressApp) {
        this.addressApp = addressApp;
        contactTable.setItems(addressApp.getContactes());
    }

    public void showContactDetails (Contact contact) {
        if (contact != null) {
            nomLabel.setText(contact.getNom().get());
            cognomsLabel.setText(contact.getCognoms().get());
            domiciliLabel.setText(contact.getDomicili().get());
            ciutatLabel.setText(contact.getCiutat().get());
            codiPostalLabel.setText(Integer.toString(contact.getCodiPostal().get()));
            dataNaixementLabel.setText(DateUtil.format(contact.getDataNaixement().get()));
        } else {
            nomLabel.setText("");
            cognomsLabel.setText("");
            domiciliLabel.setText("");
            ciutatLabel.setText("");
            codiPostalLabel.setText("");
            dataNaixementLabel.setText("");
        }
    }
}
