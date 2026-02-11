package Controllers;

import Entites.User;
import Services.ServiceUser;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifierUserController {

    @FXML private TextField tfNomModif;
    @FXML private TextField tfprenomModif;
    @FXML private TextField tfemailModif;
    @FXML private PasswordField tfmotDePasseModif;
    @FXML private PasswordField pfConfirmMotDePasseModif;
    @FXML private TextField tfTelephoneModif;
    @FXML private TextField tfAddresseModif;
    @FXML private ComboBox<String> tfroleeeeModif;

    private User user; // user to modify
    private ServiceUser serviceUser = new ServiceUser();

    public void setUser(User user) {
        this.user = user;
        populateFields();
    }

    @FXML
    public void initialize() {
        tfroleeeeModif.setItems(FXCollections.observableArrayList(
                "ouvrier agricole",
                "agriculteur client",
                "admin"
        ));
    }

    private void populateFields() {
        if (user != null) {
            tfNomModif.setText(user.getNom_user());
            tfprenomModif.setText(user.getPrenom_user());
            tfemailModif.setText(user.getEmail_user());
            tfmotDePasseModif.setText(user.getPassword());
            pfConfirmMotDePasseModif.setText(user.getPassword());
            tfTelephoneModif.setText(String.valueOf(user.getNum_user()));
            tfAddresseModif.setText(user.getAdresse_user());
            tfroleeeeModif.setValue(user.getRole_user());
        }
    }

    @FXML
    private void ModifAction() {
        // Validation
        if (tfNomModif.getText().isEmpty() || tfprenomModif.getText().isEmpty() ||
                tfemailModif.getText().isEmpty() || tfmotDePasseModif.getText().isEmpty() ||
                pfConfirmMotDePasseModif.getText().isEmpty() || tfTelephoneModif.getText().isEmpty() ||
                tfAddresseModif.getText().isEmpty() || tfroleeeeModif.getValue() == null) {
            alert("Remplir tous les champs");
            return;
        }

        if (!tfmotDePasseModif.getText().equals(pfConfirmMotDePasseModif.getText())) {
            alert("Mot de passe et confirmation ne correspondent pas !");
            return;
        }

        if (!tfemailModif.getText().contains("@")) {
            alert("Email invalide !");
            return;
        }

        int tel;
        try {
            tel = Integer.parseInt(tfTelephoneModif.getText());
        } catch (NumberFormatException e) {
            alert("Téléphone doit contenir uniquement des chiffres !");
            return;
        }

        // Update user object
        user.setNom_user(tfNomModif.getText());
        user.setPrenom_user(tfprenomModif.getText());
        user.setEmail_user(tfemailModif.getText());
        user.setPassword(tfmotDePasseModif.getText());
        user.setNum_user(tel);
        user.setAdresse_user(tfAddresseModif.getText());
        user.setRole_user(tfroleeeeModif.getValue());

        // Save in DB
        serviceUser.modifier(user);

        alert("Utilisateur modifié avec succès !");
        closeWindow();
    }

    private void alert(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(msg);
        a.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) tfNomModif.getScene().getWindow();
        stage.close();
    }

    public void pickImageAction(ActionEvent actionEvent) {
    }

    public void tfroleeeeModif(ActionEvent actionEvent) {
    }

    public void takePictureAction(ActionEvent actionEvent) {
    }
}
