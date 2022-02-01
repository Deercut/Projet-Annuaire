package fr.isika.cda12.projet1.groupe2.backend;

import java.io.BufferedWriter;
import java.io.IOException;

public class Etudiant {

	Etudiant left;
	Etudiant right;
	String nom;
	String prenom;
	String numero;

	public Etudiant(String nom, String prenom, String numero) {
		left = null;
		right = null;
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
	}

	public Etudiant getLeft() {
		return left;
	}

	public void setLeft(Etudiant left) {
		this.left = left;
	}

	public Etudiant getRight() {
		return right;
	}

	public void setRight(Etudiant right) {
		this.right = right;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	void modifGauche(Etudiant etudiant) {
		left = etudiant;
	}

	void modifDroit(Etudiant etudiant) {
		right = etudiant;
	}

	void modifValeur(String nom, String prenom, String numero) {
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Etudiant " + ", nom=" + nom + ", prenom=" + prenom + ", numero=" + numero + "]";
	}

	public void WriteNode(BufferedWriter bufferedWriter) throws IOException {
		bufferedWriter.write(this.nom + " " + this.prenom + " " + this.numero);
		bufferedWriter.newLine();
		if (this.left != null)
			this.left.WriteNode(bufferedWriter);
		if (this.right != null)
			this.right.WriteNode(bufferedWriter);
	}

}