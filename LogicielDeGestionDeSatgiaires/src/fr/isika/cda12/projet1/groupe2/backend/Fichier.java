package fr.isika.cda12.projet1.groupe2.backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// d�claration de la classe Fichier qui a une seule propri�t�: path (chemin)
public class Fichier {
	private String path;

	// Constructeur de la classe Fichier
	public Fichier(String path) {
		super();
		this.path = path;
	}

	// Getters et Setters de la classe Path
	private String getPath() {
		return path;
	}

	private void setPath(String path) {
		this.path = path;
	}

	// Méthode CreateFile qui cree un fichier � partir du chemin du fichier
	public File CreateFile() {
		File file = new File(this.path);
		return file;
	}

	// M�thode BufferReader qui cree un BufferReader � travers une variable File 
	public BufferedReader LireFichier(File file) throws IOException {
		BufferedReader bufferedReader = null;
		try {
			FileReader fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

		} catch (FileNotFoundException e) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Impossible de trouver le fichier");
			errorAlert.showAndWait();
		} catch (IOException e) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Impossible d'ouvrir le fichier");
			errorAlert.showAndWait();
		}
		return (bufferedReader);
	}

	public ArrayList<Etudiant> AfficherFichier() throws IOException {
		File file = CreateFile();
		BufferedReader bufferedReader = LireFichier(file);
		String line;
		ArrayList<Etudiant> list = new ArrayList<Etudiant>();
		while ((line = bufferedReader.readLine()) != null) {
			String[] words = line.split("\\s");
			String nom = words[0];
			nom = nom.replace("/", " ");
			String prenom = words[1];
			String numero = words[2];
			Etudiant etud = new Etudiant(nom, prenom, numero);
			list.add(etud);
		}
		FermerFichier(bufferedReader);
		return list;
	}

	public void FermerFichier(BufferedReader bufferedReader) {
		try {
			bufferedReader.close();
		} catch (IOException e) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Impossible de fermer le fichier");
			errorAlert.showAndWait();
		}
	}
	
	// Transformer le fichier en annuaire
	public Annuaire FichierEnAnnuaire(File file) throws IOException {
		BufferedReader bufferedReader = this.LireFichier(file);
		Annuaire annuaire = new Annuaire();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String[] words = line.split("\\s");
			annuaire.creation(words[0], words[1], words[2]);
		}
		return (annuaire);
	}

	// Transformer l'annuaire en fichier
	public void AnnuaireEnFichier(Annuaire annuaire, File file) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		annuaire.root.WriteNode(bufferedWriter);
		bufferedWriter.close();
	}

	// Méthode d'ajout d'un �tudiant
	public boolean AjouterEtudiant(String nom, String prenom, String numero) throws IOException {
		boolean result;
		File file = CreateFile();
		Annuaire annuaire = FichierEnAnnuaire(file);
		result=annuaire.creation(nom, prenom, numero);
		AnnuaireEnFichier(annuaire, file);
		return result;
	}
	
	// Méthode de suppression
	public void SupprimerEtudiant(String nom, String prenom, String numero) throws IOException {
		File newFile = new File("src/ressource/test.DON");
		File file = CreateFile();
		BufferedReader bufferedReader = this.LireFichier(file);
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			String[] words = line.split("\\s");
			String nomReader = words[0];
			String prenomReader = words[1];
			String numeroReader = words[2];
			nom = nom.replace(" ", "/");
			
			if ((nomReader.equals(nom)) && (prenomReader.equals(prenom)) && (numeroReader.equals(numero))) {
				
			} else {
				bufferedWriter.write(nomReader + " " + prenomReader + " " + numeroReader);
				bufferedWriter.newLine();
			}
		}
		bufferedReader.close();
		bufferedWriter.close();
		newFile.renameTo(file);

	}

	// Méthode de recherche par nom
	public ArrayList<Etudiant> rechercherParNom(String nom) throws IOException {
		File file = CreateFile();
		Annuaire annuaire = FichierEnAnnuaire(file);
		ArrayList<Etudiant> liste = annuaire.searchByName(nom);
		return liste;
	}

	// Méthode de recherche par prenom
	public ArrayList<Etudiant> rechercherParPrenom(String prenom) throws IOException {
		File file = CreateFile();
		Annuaire annuaire = FichierEnAnnuaire(file);
		ArrayList<Etudiant> liste = annuaire.searchByPrenom(prenom);
		return liste;
	}

	// Méthode de recherche par numero De telephone
	public ArrayList<Etudiant> rechercherParNumero(String numero) throws IOException {
		File file = CreateFile();
		Annuaire annuaire = FichierEnAnnuaire(file);
		ArrayList<Etudiant> liste = annuaire.searchByNumero(numero);
		return liste;
	}

	// Méthode de recherche par nom et prenom
	public ArrayList<Etudiant> rechercherParNomEtPrenom(String nom, String prenom) throws IOException {
		File file = CreateFile();
		Annuaire annuaire = FichierEnAnnuaire(file);
		ArrayList<Etudiant> liste = annuaire.searchByNomAndPrenom(nom, prenom);
		return liste;
	}
	
	// Méthode de recherche par nom et numero
	public ArrayList<Etudiant> rechercherParNomNumero(String nom, String numero) throws IOException {
		File file = CreateFile();
		Annuaire annuaire = FichierEnAnnuaire(file);
		ArrayList<Etudiant> liste = annuaire.searchByNomAndNumero(nom, numero);
		return liste;
	}
	
	// Méthode de recherche par nom et numero
	public ArrayList<Etudiant> rechercherParPrenomEtNumero(String prenom, String numero) throws IOException {
		File file = CreateFile();
		Annuaire annuaire = FichierEnAnnuaire(file);
		ArrayList<Etudiant> liste = annuaire.searchByPrenomAndNumero(prenom, numero);
		return liste;
	}
	
	// Méthode de recherche par nom et prenom et  numero
	public ArrayList<Etudiant> rechercherParNomEtPrenomEtNumero(String nom, String prenom, String numero) throws IOException {
		File file = CreateFile();
		Annuaire annuaire = FichierEnAnnuaire(file);
		ArrayList<Etudiant> liste = annuaire.searchByNomAndPrenomAndNumero(nom, prenom, numero);
		return liste;
	}
	
	// Méthode de modifier d'un �tudiant
	public void ModifierEtudiant(String ancienNom, String ancienPrenom, String ancienNumero, String nouveauNom,
		String nouveauPrenom, String nouveauNumero) throws IOException {
		File file = CreateFile();
		Annuaire annuaire = FichierEnAnnuaire(file);
		annuaire.update(ancienNom, ancienPrenom, ancienNumero, nouveauNom, nouveauPrenom, nouveauNumero);
		AnnuaireEnFichier(annuaire, file);
	}
}
