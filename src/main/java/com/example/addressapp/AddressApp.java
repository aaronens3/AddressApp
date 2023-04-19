package com.example.addressapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Contact;
import java.util.prefs.Preferences;

import java.io.*;

public class AddressApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Stage dialogStage;


    private ObservableList<Contact> contactes = FXCollections.observableArrayList();

    public AddressApp() {
        this.contactes.add(new Contact("Guillermo", "Garrido Portes", "C/Albacete", "Valencia", 47002, 11, 01, 1995));
        this.contactes.add(new Contact("Pepe", "Antonio", "C/Albacete", "Valencia", 47002, 11, 01, 2002));
        this.contactes.add(new Contact("Jose", "Genis Portes", "C/Andaluz", "Madrid", 47002, 11, 01, 2004));
        this.contactes.add(new Contact("Veronica", "Primo Parra", "C/Albacete", "Valencia", 47180, 29, 11, 2001));
        this.contactes.add(new Contact("Guillermo", "Garrido Portes", "C/Albacete", "Valencia", 47002, 11, 01, 1995));
    }

    public ObservableList<Contact> getContactes() {
        return this.contactes;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Activitat Avaluable 2 - APrimo");
        Image icona = new Image("file:resources/images/icona.png");
        this.primaryStage.getIcons().add(icona);

        initRootLayout();
        showIndex();
    }



    private void showIndex() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("Index.fxml"));
            AnchorPane index = (AnchorPane) loader.load();
            this.rootLayout.setCenter(index);
            IndexController controller = loader.getController();
            controller.setAddressApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("RootLayout.fxml"));
            this.rootLayout = loader.load();
            Scene scene = new Scene(this.rootLayout);
            this.primaryStage.setScene(scene);
            RootLayoutController controller = loader.getController();
            controller.setAddressApp(this);
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public boolean showContactEditDialog(Contact contacte) {
        try {
            // Carrega el fitxer fxml i crea un nou stage per al diàleg
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("ContactEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            // Crea el diàleg Stage
            this.dialogStage = new Stage();
            this.dialogStage.setTitle("Editar contacte");
            if (contacte.getNom().get() != null) {
                this.dialogStage.setTitle("Editar contacte: " + contacte.getNom().get());
            }
            // Inicialitza el diàleg
            dialogStage.initOwner(this.primaryStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            this.dialogStage.setScene(scene);

            // Passa el contacte al controlador
            ContactEditDialogController controller = loader.getController();
            controller.setDialogStage(this.dialogStage);
            controller.setContacte(contacte);

            // Mostra el diàleg i espera a que l'usuari el tanqui
            this.dialogStage.showAndWait();

            // Retorna true si l'usuari ha fet click a OK, false en cas contrari
            return controller.getOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Window getPrimaryStage() {
        return this.primaryStage;
    }



    public void saveContactDataToFile(File arxiu) {
        try {
            FileWriter fitxer = new FileWriter(arxiu);
            fitxer.write("");
            fitxer.close();
            fitxer = new FileWriter(arxiu, true);
            for (Contact contacte : this.contactes) {
                String str = contacte.getNom().get() + ","
                        + contacte.getCognoms().get() + ","
                        + contacte.getDomicili().get() + ","
                        + contacte.getCiutat().get() + ","
                        + String.valueOf(contacte.getCodiPostal().get()) + ","
                        + DateUtil.format(contacte.getDataNaixement().get());
                fitxer.write(str);
                fitxer.write(System.lineSeparator());
            }
            fitxer.close();
            this.setContactesFilePath(arxiu);
        } catch (Exception ex){
            System.err.println("Error al guardar els contactes en el arxiu: " + arxiu.getName());
        }
    }




    public void setContactesFilePath(File arxiu) {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        if (arxiu != null) {
            prefs.put("ruta_arxiu", arxiu.getPath());
        } else {
            prefs.remove("ruta_arxiu");
        }
    }

    public File getContactesFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        String ruta = prefs.get("ruta_arxiu", null);
        if (ruta != null) {
            return new File(ruta);
        } else {
            return null;
        }
    }

    public void loadContactDataFromFile(File arxiu) {
        this.contactes.clear();
        try {
            FileReader fr = new FileReader(arxiu);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] contacte = linea.split(",");
                String[] fecha = contacte[5].split("-");
                this.contactes.add (new Contact(
                        contacte[0],
                        contacte[1],
                        contacte[2],
                        contacte[3],
                        Integer.parseInt(contacte[4]),
                        Integer.parseInt(fecha[0]),
                        Integer.parseInt(fecha[1]),
                        Integer.parseInt(fecha[2])));
        }
            fr.close();
            this.setContactesFilePath(arxiu);
    }catch (Exception ex){
            System.err.println("No s'ha trobat l'arxiu:  " + arxiu.getName());
        }
    }


}