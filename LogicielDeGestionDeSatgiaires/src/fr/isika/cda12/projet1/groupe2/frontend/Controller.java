package fr.isika.cda12.projet1.groupe2.frontend;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller implements Initializable {

	Stage stage;

	double xOffset, yOffset;

	@FXML
	private TextField tfUtilisateur;

	@FXML
	private PasswordField pfPassword;

	@FXML
	private Button showpassword;

	@FXML
	private Button btnConnection;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnShowPassword;

	@FXML
	private Label erreurMessageLabel;

	private String erreurMessage = "";

	@FXML
	private String password;
	
	@FXML
	private Button btnHelp;
	

	private boolean isFielVide() {
		boolean isVide = true;
		if (tfUtilisateur.getText().isEmpty()) {
			isVide = false;
			erreurMessage = " Entrez votre Nom ";
		}
		if (pfPassword.getText().isEmpty()) {
			isVide = false;
			if (erreurMessage.isEmpty()) {
				erreurMessage = " Entrez votre mot de passe ";
			} else {
				//erreurMessage += "\nEntrez votre mot de passe ";
			}
		}
		erreurMessageLabel.setText(erreurMessage);
		return isVide;
	}

	private boolean isRempli() {
		boolean isRempli = true;
		if (tfUtilisateur.getText().equals(Main.NOMUTILISATEUR1) && pfPassword.getText().equals(Main.MOTDEPASSE1)) {
			isRempli = true;
			erreurMessage = "Bienvenu sur \n Abook";
		} else if (tfUtilisateur.getText().equals(Main.NOMUTILISATEUR2)
				&& pfPassword.getText().equals(Main.MOTDEPASSE2)) {
			isRempli = true;
			erreurMessage = "Bienvenu sur \n Abook";
		} else if (!pfPassword.getText().equals(Main.MOTDEPASSE1)
				|| !tfUtilisateur.getText().equals(Main.NOMUTILISATEUR1)) {
			isRempli = false;
			erreurMessage = "Nom d'utilisateur ou \nMot de passe invalide";

		} else if (!pfPassword.getText().equals(Main.MOTDEPASSE2)
				|| !tfUtilisateur.getText().equals(Main.NOMUTILISATEUR2)) {
			isRempli = false;
			erreurMessage = "Nom d'utilisateur ou \nMot de passe invalide";
		} else {
			erreurMessage += "\n Erreur";
		}
		erreurMessageLabel.setText(erreurMessage);
		return isRempli;
	}

	public void help() {
		if (Desktop.isDesktopSupported()) {
			try {
				File theUMFile = new File("src/ressource/manuel.pdf");
				Desktop.getDesktop().open(theUMFile);
			} catch (FileNotFoundException fnf) {

			} catch (IllegalArgumentException fnf) {

			} catch (IOException ex) {

			}
		}
	}
	
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		if(event.getSource()== btnHelp)
			help();
	}
	
	
	
	// méthode pour ouvrir la page admin
	private void startAdminWindow() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("Administrator.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();

			root.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY();
				}
			});

			root.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					stage.setX(event.getScreenX() - xOffset);
					stage.setY(event.getScreenY() - yOffset);
				}
			});

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// méthode pour ouvrir la page employé
	private void startEmployWindow() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("Employe.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();

			root.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY();
				}
			});

			root.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					stage.setX(event.getScreenX() - xOffset);
					stage.setY(event.getScreenY() - yOffset);
				}
			});

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// méthode pour savoir si utilisateur est un admin ou non
	public boolean isAdmin() {

		if (tfUtilisateur.getText().equals(Main.MOTDEPASSE1) && pfPassword.getText().equals(Main.NOMUTILISATEUR1)) {
			return true;
		} else {
			return false;
		}
	}

	// méthode pour savoir si utilisateur est un employé ou non
	public boolean isEmploy() {
		if (tfUtilisateur.getText().equals(Main.MOTDEPASSE2) && pfPassword.getText().equals(Main.NOMUTILISATEUR2)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.initStyle(StageStyle.TRANSPARENT);
				alert.initStyle(StageStyle.UNDECORATED);
				alert.setHeaderText(" Quitter Abook");
				alert.setContentText(" Voulez vous quitter Abook ? ");

				if (alert.showAndWait().get() == ButtonType.OK) {
					System.exit(0);
				}

			}

		});

		showpassword.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
			password = pfPassword.getText();
			pfPassword.clear();
			pfPassword.setPromptText(password);
		});
		showpassword.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
			pfPassword.setText(password);
			pfPassword.setPromptText("Password");
		});

		btnConnection.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				erreurMessage = "";
				if (isFielVide() && isRempli() && isAdmin()) {
					startAdminWindow();

				}
				if (isFielVide() && isRempli() && isEmploy()) {
					startEmployWindow();
				}
			}
		});

	}
}
