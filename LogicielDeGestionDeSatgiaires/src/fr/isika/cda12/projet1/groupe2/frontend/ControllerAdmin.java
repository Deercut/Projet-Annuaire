package fr.isika.cda12.projet1.groupe2.frontend;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import fr.isika.cda12.projet1.groupe2.backend.Etudiant;
import fr.isika.cda12.projet1.groupe2.backend.Fichier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerAdmin implements Initializable {

	@FXML
	private TextField tfPrenom;// use

	@FXML
	private TextField tfNom;// use

	@FXML
	private TextField tfTelephone;// use

	@FXML
	private Button btnAjouter;// use

	@FXML
	private Button btnModifier;// use

	@FXML
	private Button btnSupprimer;// use

	@FXML
	private Button btnImprimer;// use

	@FXML
	private Button btnSearch;// USE

	@FXML
	private Button btActualisation;

	@FXML
	private TableView<Etudiant> tbTableau;

	@FXML
	private TableColumn<Etudiant, String> coNom;

	@FXML
	private TableColumn<Etudiant, String> coPrenom;

	@FXML
	private TableColumn<Etudiant, String> coTelephone;

	@FXML
	private Button btnClose;// USE

	@FXML
	private ScrollBar scBar;

	@FXML
	private Label lbErreur;

	@FXML
	private AnchorPane pane;
	
	@FXML
	private Button btnHelp;

	Fichier fichier = new Fichier("src/ressource/nom.DON");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			afficherEtudiant();
		} catch (IOException e) {
			e.printStackTrace();
		}

		pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				lbErreur.setText(null);
			}
		});
		
		tbTableau.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				ajouterEtudiantClick();
//				pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
//					public void handle(MouseEvent e) {
//						clean();
//					}
//				});
			}
		});

		btnClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.initStyle(StageStyle.TRANSPARENT);
				alert.initStyle(StageStyle.UNDECORATED);
				alert.setHeaderText(" Quitter Abook");
				alert.setContentText(" Voulez vous quitter Abook ? ");

				if (alert.showAndWait().get() == ButtonType.OK) {
					System.exit(0);
				}
			}
		});

		btnSupprimer.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Alert alert2 = new Alert(AlertType.CONFIRMATION);
				alert2.initStyle(StageStyle.TRANSPARENT);
				alert2.initStyle(StageStyle.UNDECORATED);
				alert2.setHeaderText("Supprimer ");
				alert2.setContentText("Voulez vous supprimer ces informations ?");
				if (alert2.showAndWait().get() == ButtonType.OK) {
					alert2.close();
					try {
						ajouterSuppression();
					} catch (IOException e) {
						e.printStackTrace();
					}
					clean();
				}
			}

		});
	}

	public void alertSupprimer() {
		Alert alert2 = new Alert(AlertType.CONFIRMATION);
		alert2.initStyle(StageStyle.TRANSPARENT);
		alert2.initStyle(StageStyle.UNDECORATED);
		alert2.setHeaderText("Supprimer ");
		alert2.setContentText("Voulez vous supprimer ces informations ?");
		if (alert2.showAndWait().get() == ButtonType.OK) {
			alert2.close();
			try {
				ajouterSuppression();
			} catch (IOException e) {
				e.printStackTrace();
			}
			clean();
		}
	}

	// Méthode pour actualiser
	public void clean() {
		tfPrenom.setText("");
		tfNom.setText("");
		tfTelephone.setText("");
		tfNom.requestFocus();
		//lbErreur.setText(null);
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

	// Méthode pour vider les espaces après avoir ajouter supprimer ou modifier.
	public void ajouterActualisation() throws IOException {
		clean();
		afficherEtudiant();
	}

	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		// afficherEtudiant();
		if (event.getSource() == btnAjouter)
			ajouterEnregistrement();
		else if (event.getSource() == btnModifier)
			ajouterModifier();
		else if (event.getSource() == btnSupprimer)
			alertSupprimer();
		else if (event.getSource() == btnSearch)
			ajouterSearch();
		else if (event.getSource() == btActualisation)
			ajouterActualisation();
		else if (event.getSource() == btnImprimer)
			ajouterImpression();
		else if (event.getSource() == btnHelp)
			help();
	}

	// méthode pour ajouter un étudiant
	private void ajouterEnregistrement() throws IOException {
		String nom = tfNom.getText();
		nom = nom.toUpperCase(); // transformation du nom en majuscule
		nom = nom.replace(" ", "/"); // remplacement des espaces par des / pour le fichier
		String prenom = tfPrenom.getText();
		prenom = prenom.replace(" ", "/"); // on remplace les espaces par des / pour le fichier
		prenom = prenom.toLowerCase();
		String numero = tfTelephone.getText();
		String Newligne = System.getProperty("line.separator"); // pour sauter une ligne
		if (numero.length() == 10) { // condition pour le numero pour avoir 10 chiffres
			if(!nom.isEmpty() && !prenom.isEmpty() ) {
				
			
			boolean result = fichier.AjouterEtudiant(nom, prenom, numero);
			if (result == true) {
				afficherEtudiant();

				lbErreur.setText("L'étudiant/e " + prenom + " " + nom + " " + Newligne + "a été ajouté/e");
				clean();
			} else {
				lbErreur.setText("L'étudiant/e " + prenom + " " + nom + " " + Newligne + "existe déja");
			}
			} else {
				lbErreur.setText("Tous les champs doivent être remplis");
			}
		} else {
			lbErreur.setText("Le numéro de téléphone doit contenir" + Newligne + " 10 chiffres");
		}
	}

	// Méthode pour supprimer un etudiant
	private void ajouterSuppression() throws IOException {
		Etudiant etudiantSelectionne = tbTableau.getSelectionModel().getSelectedItem();
		if (etudiantSelectionne != null) {
			String nom = etudiantSelectionne.getNom();
			nom = nom.replace(" ", "/");
			String prenom = etudiantSelectionne.getPrenom();
			prenom = prenom.replace(" ", "/");
			String numero = etudiantSelectionne.getNumero();
			String Newligne = System.getProperty("line.separator");
			try {
				fichier.SupprimerEtudiant(nom, prenom, numero);
				lbErreur.setText("L'étudiant/e " + prenom + " " + nom + " " + Newligne + "a été supprimé/e");
				clean();
			} catch (IOException e) {
				lbErreur.setText("L'étudiant/e " + prenom + " " + nom + " " + Newligne + "n'a pas été supprimé/e");
				e.printStackTrace();
			}
		}
		afficherEtudiant();
	}

	// Méthode pour afficher l'étudiant cliqué sur le tableau sur les textFields
	private void ajouterEtudiantClick() {
		Etudiant etudiantSelectionne = tbTableau.getSelectionModel().getSelectedItem();
		String nomSelectionne = "";
		String prenomSelectionne = "";
		String telephoneSelectionne = "";
		if (etudiantSelectionne != null) {
			nomSelectionne = etudiantSelectionne.getNom();
			prenomSelectionne = etudiantSelectionne.getPrenom();
			telephoneSelectionne = etudiantSelectionne.getNumero();
		}
		tfPrenom.setText(prenomSelectionne);
		tfNom.setText(nomSelectionne);
		tfTelephone.setText(telephoneSelectionne);
	}

	// Methode pour modifier un étudiant
	private void ajouterModifier() throws IOException {
		Etudiant etudiantSelectionne = tbTableau.getSelectionModel().getSelectedItem();
		String Newligne = System.getProperty("line.separator");
		if (etudiantSelectionne != null) {
			String ancienNom = etudiantSelectionne.getNom();
			ancienNom = ancienNom.toUpperCase();
			ancienNom = ancienNom.replace(" ", "/");

			String ancienPrenom = etudiantSelectionne.getPrenom();
			ancienPrenom = ancienPrenom.toLowerCase();
			ancienPrenom = ancienPrenom.replace(" ", "/");

			String ancienNumero = etudiantSelectionne.getNumero();
			// System.out.println("ancien" +ancienNom + ancienPrenom + ancienNumero );

			String nouveauNom = tfNom.getText();
			nouveauNom = nouveauNom.replace(" ", "/");
			nouveauNom = nouveauNom.toUpperCase();

			String nouveauPrenom = tfPrenom.getText();
			nouveauPrenom = nouveauPrenom.toLowerCase();
			nouveauPrenom = nouveauPrenom.replace(" ", "/");

			String nouveauNumero = tfTelephone.getText();
			// System.out.println("nouveau" +nouveauNom + nouveauPrenom + nouveauNumero );

			if ((tfNom.getText().isEmpty()) && (tfPrenom.getText().isEmpty()) && (tfTelephone.getText().isEmpty())) {
				lbErreur.setText("Veuillez ajouter les nouvelles données" + Newligne + "de l'étudiant/e");
			} else if (nouveauNumero.length() != 10) {
				lbErreur.setText("Le numero de téléphone doit contenir" + Newligne + "10 chiffres"); // message erreur
				// pour le
				// numéro
			} else {

				try {
					fichier.ModifierEtudiant(ancienNom, ancienPrenom, ancienNumero, nouveauNom, nouveauPrenom,
							nouveauNumero);
					lbErreur.setText("L'étudiant/e " + ancienPrenom + " " + ancienNom + Newligne  + "a été modifié/e");
				} catch (IOException e) {
					lbErreur.setText(
							"L'étudiant/e " + ancienPrenom + " " + ancienNom + Newligne  + "n'a pas pu être modifié/e");
					e.printStackTrace();
				}
				clean();
			}
		}
		afficherEtudiant();
	}

	//méthode pour imprimer
	@FXML
	public void ajouterImpression() {
		PrinterJob  printerJob = PrinterJob.createPrinterJob();
        Stage stage = (Stage) tbTableau.getScene().getWindow();
       boolean openPrintDialog = printerJob.showPrintDialog(stage);
        if(openPrintDialog){
        	boolean proceed2= printerJob.showPageSetupDialog(stage);

    	    // Si l'utilisateur clique sur imprimer dans la boite de dialogue
    	    if (proceed2) {
        	printerJob.jobStatusProperty().addListener((observable, oldValue, newValue) -> {

  		      if (newValue == PrinterJob.JobStatus.PRINTING)
  		       lbErreur.setText("Impression en cours...");

  		      if (newValue == PrinterJob.JobStatus.DONE)
  		       lbErreur.setText("Impression terminée !");

  		      if (newValue == PrinterJob.JobStatus.ERROR)
  		    	lbErreur.setText("Erreur lors de l'impression");

  		     });
        	
        	tbTableau.setScaleX(0.8);
        	tbTableau.setScaleY(0.8);
        	tbTableau.setTranslateX(-70);
        	tbTableau.setTranslateY(-50);
            ObservableList<Etudiant> allPrintItems = tbTableau.getItems();
            ObservableList <Etudiant> pageList = FXCollections.observableArrayList();
           boolean printing = false;
            for(int i=0; i<allPrintItems.size(); i++) {
                Etudiant oneRow = allPrintItems.get(i);
                pageList.add(oneRow);
                if(i!=0 && (i%24==0 || i == (allPrintItems.size()-1))){
                	tbTableau.setItems(pageList);
                    printing = printerJob.printPage(tbTableau);
                    pageList.clear();
                }
            }
            tbTableau.setItems(allPrintItems);
            if(printing)printerJob.endJob();
            tbTableau.setScaleX(1.0);
            tbTableau.setScaleY(1.0);
            tbTableau.setTranslateX(0);
            tbTableau.setTranslateY(0);}}}
		

	private void ajouterSearch() throws IOException {
		String nomToSearch = tfNom.getText();
		nomToSearch = nomToSearch.toUpperCase();
		nomToSearch = nomToSearch.replace(" ", "/");
		String prenomToSearch = tfPrenom.getText();
		prenomToSearch = prenomToSearch.toLowerCase();
		prenomToSearch = prenomToSearch.replace(" ", "/");
		String numeroToSearch = tfTelephone.getText();

		if (!nomToSearch.isEmpty() && !prenomToSearch.isEmpty() && !numeroToSearch.isEmpty()) { // recherche par
			// nom, prenom et numero
			ArrayList<Etudiant> list = fichier.rechercherParNomEtPrenomEtNumero(nomToSearch, prenomToSearch,
					numeroToSearch);
			afficherEtudiant(list);
			clean();
		} else if (!nomToSearch.isEmpty() && !prenomToSearch.isEmpty()) { // recherche par nom et prenom
			ArrayList<Etudiant> list = fichier.rechercherParNomEtPrenom(nomToSearch, prenomToSearch);
			afficherEtudiant(list);
			clean();
		} else if (!nomToSearch.isEmpty() && !numeroToSearch.isEmpty()) { // recherche par nom et numero
			ArrayList<Etudiant> list = fichier.rechercherParNomNumero(nomToSearch, numeroToSearch);
			afficherEtudiant(list);
			clean();
		} else if (!prenomToSearch.isEmpty() && !numeroToSearch.isEmpty()) { // recherche par prenom et numero
			ArrayList<Etudiant> list = fichier.rechercherParPrenomEtNumero(prenomToSearch, numeroToSearch);
			afficherEtudiant(list);
			clean();
		} else if (!nomToSearch.isEmpty()) { // recherhce par nom uniquement
			ArrayList<Etudiant> list = fichier.rechercherParNom(nomToSearch);
			afficherEtudiant(list);
			clean();

		} else if (!prenomToSearch.isEmpty()) { // recherhce par prenom uniquement
			ArrayList<Etudiant> list = fichier.rechercherParPrenom(prenomToSearch);
			afficherEtudiant(list);
			clean();
		} else if (!numeroToSearch.isEmpty()) { // recherche par numero uniquement
			ArrayList<Etudiant> list = fichier.rechercherParNumero(numeroToSearch);
			afficherEtudiant(list);
			clean();
		} else if (numeroToSearch.isEmpty() && numeroToSearch.isEmpty() && nomToSearch.isEmpty()) { // recherhce vide de
			// champs
			afficherEtudiant();
			clean();
		}

	}

	// méthode permettant d'afficher TOUS les etudiants du fichier dans le tableview
	public void afficherEtudiant() throws IOException {
		ArrayList<Etudiant> listEtudiant = new ArrayList<Etudiant>();
		listEtudiant = fichier.AfficherFichier();
		try {

			coNom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("Nom"));
			coPrenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("Prenom"));
			coTelephone.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("Numero"));
			tbTableau.getItems().clear();
			tbTableau.getItems().addAll(listEtudiant);

		} catch (NullPointerException e) {
			System.err.println("Erreur" + e);
		}

	}

	// méthode d'affichage des étudiants RECHERCHES sur le tableView (apres une
	// recherche)
	public void afficherEtudiant(ArrayList<Etudiant> list) throws IOException {
		try {
			coNom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("Nom"));
			coPrenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("Prenom"));
			coTelephone.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("Numero"));

			tbTableau.getItems().clear();
			tbTableau.getItems().addAll(list);
		} catch (NullPointerException e) {
			System.err.println("Erreur" + e);
		}
	}

}
