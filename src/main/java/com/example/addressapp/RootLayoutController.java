package com.example.addressapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;



public class RootLayoutController {
    public void setAddressApp(AddressApp addressapp) {
        this.addressapp = addressapp;
    }
    @FXML
    public void exit() {
        System.exit(0);
    }
    @FXML
    public void openFile(){
        File arxiu = this.mostrarDialeg("open");
    if (arxiu != null) {
            this.addressapp.loadContactDataFromFile(arxiu);
        }
    }
    @FXML
    public void saveFile(){
        File arxiu = this.addressapp.getContactesFilePath();
        if (arxiu != null) {
            this.addressapp.saveContactDataToFile(arxiu);
        } else {
            this.saveAsFile();
        }
    }
    @FXML
    public void saveAsFile(){
        File arxiu = this.mostrarDialeg("save");
        if (arxiu != null) {
            if (!arxiu.getPath().endsWith(".txt")) {
                arxiu = new File(arxiu.getPath() + ".txt");
            }
            this.addressapp.saveContactDataToFile(arxiu);
        }
    }

    @FXML
    public void sobreMi() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sobre mi");
        alert.setHeaderText("Informació del Autor");
        alert.setContentText("Nom y Cognom: Aaron Primo Almarche");
        alert.showAndWait();
    }

    private File mostrarDialeg(String tipus) {
        File arxiu;
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de Texto", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        if (tipus.equals("save")) {
            return arxiu = fileChooser.showOpenDialog(addressapp.getPrimaryStage());
        } else {
            return arxiu = fileChooser.showSaveDialog(addressapp.getPrimaryStage());
        }
    }


    private AddressApp addressapp;
}


