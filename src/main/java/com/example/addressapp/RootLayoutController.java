package com.example.addressapp;

import javafx.fxml.FXML;
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
