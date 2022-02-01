package fr.isika.cda12.projet1.groupe2.backend;

import java.util.ArrayList;

public class Annuaire {

	public static Etudiant root;
	String nom;
	String prenom;
	String numero;
	int ch;

	Annuaire() {
		root = null;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////// METHODES DE MODIFICATION//////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void update(String ancienNom, String ancienPrenom, String ancienNumero, String nouveauNom,
			String nouveauPrenom, String nouveauNumero) {
		int flag = 0;
		Etudiant ptr;
		ptr = root;
		while (ptr != null) {
			if ((ptr.nom).compareTo(ancienNom) == 0) {
				if ((ptr.prenom).compareTo(ancienPrenom) == 0) {
					if ((ptr.numero).compareTo(ancienNumero) == 0) {
						flag = 1;
						break;
					}
					if ((ptr.numero).compareTo(ancienNumero) > 0) {
						ptr = ptr.left;
					} else {
						ptr = ptr.right;
					}
				}
				if ((ptr.prenom).compareTo(ancienPrenom) > 0) {
					ptr = ptr.left;
				} else {
					ptr = ptr.right;
				}
			}
			if ((ptr.nom).compareTo(ancienNom) > 0) {
				ptr = ptr.left;
			} else {
				ptr = ptr.right;
			}
		}
		if (flag == 1) {
			ptr.nom = nouveauNom;
			ptr.prenom = nouveauPrenom;
			ptr.numero = nouveauNumero;
		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////// METHODES DE RECHERCHE//////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/////////// méthode de recherche par PRENOM
	public ArrayList<Etudiant> searchByPrenom(String str) {
		ArrayList<Etudiant> maListe = new ArrayList<Etudiant>();

		return maListe = searchByPrenom(root, str, maListe);
	}

	private ArrayList<Etudiant> searchByPrenom(Etudiant e, String str, ArrayList<Etudiant> maListe) {
		if (e != null) {
			searchByPrenom(e.left, str, maListe);

			if (e.prenom.contains(str)) {
				maListe.add(e);
			}

			searchByPrenom(e.right, str, maListe);
		}
		for (Etudiant etud : maListe) {
			etud.nom = etud.nom.replace("/", " ");
			etud.prenom = etud.prenom.replace("/", " ");
		}
		return (maListe);
	}

	///////////////////// methode de recherche par NUMERO DE TELEPHONE
	public ArrayList<Etudiant> searchByNumero(String str) {
		ArrayList<Etudiant> maListe = new ArrayList<Etudiant>();

		return maListe = searchByNumero(root, str, maListe);
	}

	private ArrayList<Etudiant> searchByNumero(Etudiant e, String str, ArrayList<Etudiant> maListe) {
		if (e != null) {
			searchByNumero(e.left, str, maListe);

			if (e.numero.contains(str)) {
				maListe.add(e);
			}

			searchByNumero(e.right, str, maListe);
		}
		for (Etudiant etud : maListe) {
			etud.nom = etud.nom.replace("/", " ");
			etud.prenom = etud.prenom.replace("/", " ");
		}
		return (maListe);
	}

	///////////// methodes de recherche par NOM
	public ArrayList<Etudiant> searchByName(String str) {
		ArrayList<Etudiant> maListe = new ArrayList<Etudiant>();

		return maListe = searchByName(root, str, maListe);
	}

	private ArrayList<Etudiant> searchByName(Etudiant e, String str, ArrayList<Etudiant> maListe) {
		if (e != null) {
			searchByName(e.left, str, maListe);

			if (e.nom.contains(str)) {
				maListe.add(e);
			}

			searchByName(e.right, str, maListe);
		}
		for (Etudiant etud : maListe) {
			etud.nom = etud.nom.replace("/", " ");
			etud.prenom = etud.prenom.replace("/", " ");
		}
		return (maListe);
	}

	///////////// methodes de recherche par NOM et PRENOM
	public ArrayList<Etudiant> searchByNomAndPrenom(String nomToSearch, String prenomToSearch) {
		ArrayList<Etudiant> maListe = new ArrayList<Etudiant>();

		return maListe = searchByNomAndPrenom(root, nomToSearch, prenomToSearch, maListe);
	}

	private ArrayList<Etudiant> searchByNomAndPrenom(Etudiant e, String nomToSearch, String prenomToSearch,
			ArrayList<Etudiant> maListe) {
		ArrayList<Etudiant> maListResult = new ArrayList<Etudiant>();

		if (e != null) {
			maListe = searchByName(nomToSearch);
			maListResult = removeByPrenom(root, prenomToSearch, maListe);
		}
		for (Etudiant etud : maListResult) {
			etud.nom = etud.nom.replace("/", " ");
			etud.prenom = etud.prenom.replace("/", " ");
		}
		return (maListResult);
	}

	///////////// methodes de recherche par NOM et NUMERO
	public ArrayList<Etudiant> searchByNomAndNumero(String nomToSearch, String numeroToSearch) {
		ArrayList<Etudiant> maListe = new ArrayList<Etudiant>();
		return maListe = searchByNomAndNumero(root, nomToSearch, numeroToSearch, maListe);
	}

	private ArrayList<Etudiant> searchByNomAndNumero(Etudiant e, String nomToSearch, String numeroToSearch,
			ArrayList<Etudiant> maListe) {
		ArrayList<Etudiant> maListResult = new ArrayList<Etudiant>();

		if (e != null) {
			maListe = searchByName(nomToSearch);
			maListResult = removeByNumero(root, numeroToSearch, maListe);
		}
		for (Etudiant etud : maListResult) {
			etud.nom = etud.nom.replace("/", " ");
			etud.prenom = etud.prenom.replace("/", " ");
		}
		return (maListResult);
	}

	///////////// methodes de recherche par PRENOM et NUMERO
	public ArrayList<Etudiant> searchByPrenomAndNumero(String prenomToSearch, String numeroToSearch) {
		ArrayList<Etudiant> maListe = new ArrayList<Etudiant>();
		return maListe = searchByPrenomAndNumero(root, prenomToSearch, numeroToSearch, maListe);
	}

	private ArrayList<Etudiant> searchByPrenomAndNumero(Etudiant e, String prenomToSearch, String numeroToSearch,
			ArrayList<Etudiant> maListe) {
		ArrayList<Etudiant> maListResult = new ArrayList<Etudiant>();
		if (e != null) {
			maListe = searchByPrenom(prenomToSearch);
			maListResult = removeByNumero(root, numeroToSearch, maListe);
		}
		for (Etudiant etud : maListResult) {
			etud.nom = etud.nom.replace("/", " ");
			etud.prenom = etud.prenom.replace("/", " ");
		}
		return (maListResult);
	}

	///////////// methodes de recherche par NOM et PRENOM et NUMERO
	public ArrayList<Etudiant> searchByNomAndPrenomAndNumero(String nomToSearch, String prenomToSearch,
			String numeroToSearch) {
		ArrayList<Etudiant> maListe = new ArrayList<Etudiant>();
		return maListe = searchByNomAndPrenomAndNumero(root, nomToSearch, prenomToSearch, numeroToSearch, maListe);
	}

	private ArrayList<Etudiant> searchByNomAndPrenomAndNumero(Etudiant e, String nomToSearch, String prenomToSearch,
			String numeroToSearch, ArrayList<Etudiant> maListe) {
		ArrayList<Etudiant> maListResult = new ArrayList<Etudiant>();
		if (e != null) {
			maListe = searchByName(nomToSearch);
			maListResult = removeByPrenom(root, prenomToSearch, maListe);
			maListResult = removeByNumero(root, numeroToSearch, maListe);
		}
		for (Etudiant etud : maListResult) {
			etud.nom = etud.nom.replace("/", " ");
			etud.prenom = etud.prenom.replace("/", " ");
		}
		return (maListResult);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////// METHODES DE SUPPRESION//////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	public void supprimer(String nom, String prenom, String numero) {
//		// L'élément à supprimer ne peut pas être null.
//		Etudiant element = new Etudiant(nom, prenom, numero);
//		// if (element == null) {
//		// throw new NullPointerException();
//		// }
//		root = supprimer(root, element);
//	}
//
//	private Etudiant supprimer(Etudiant courant, Etudiant element) {
//		Etudiant result = courant;
//		int test = element.nom.compareTo(courant.nom);
//		int test2 = element.prenom.compareTo(courant.prenom);
//		int test3 = element.numero.compareTo(courant.numero);
//
//		if (test == 0) {
//				if (test2 == 0) {
//					if (test3 == 0) {
//						if (courant.left == null) {
//							result = courant.right;
//						} else if (courant.right == null) {
//							result = courant.left;
//						} else {
//							courant.modifValeur(plusPetiteValeurDuSousArbre(courant.right).nom,
//									plusPetiteValeurDuSousArbre(courant.right).prenom,
//									plusPetiteValeurDuSousArbre(courant.right).numero);
//							courant.modifDroit(supprimer(courant.right, element));
//						}
//					} else if (test3 < 0) {
//						courant.modifGauche(supprimer(courant.left, element));
//					} else {
//						courant.modifDroit(supprimer(courant.right, element));
//					}
//				} else if (test2 < 0) {
//					courant.modifGauche(supprimer(courant.left, element));
//				} else {
//					courant.modifDroit(supprimer(courant.right, element));
//				}
//
//		} else if (test < 0) {
//			courant.modifGauche(supprimer(courant.left, element));
//		} else {
//			courant.modifDroit(supprimer(courant.right, element));
//		}
//		return result;
//	}
//
//	private Etudiant plusPetiteValeurDuSousArbre(Etudiant courant) {
//		if (courant == null)
//			throw new NullPointerException();
//		if (courant.left == null) {
//			return courant;
//		}
//		return plusPetiteValeurDuSousArbre(courant.left);
//	}
//


	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////// METHODE AFFICHAGE//////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void inorder(Etudiant root) {
		if (root != null) {
			inorder(root.left);
			System.out.println("" + root.nom + "\t    " + root.prenom + "\t      " + root.numero);
			inorder(root.right);
		}
	}

	public void display() {
		inorder(root);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////// METHODES AJOUT
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean creation(String nom, String prenom, String numero) {
		boolean result=true;
		int flag = 0;
		Etudiant ptr;
		Etudiant etud = new Etudiant(nom, prenom, numero);
		// dans le cas ou c'est le premier etudiant rentrer dans l'annuaire
		if (root == null) {
			root = etud;
		} else {
			ptr = root;
			while (flag == 0) {
				if ((etud.nom).compareTo(ptr.nom) == 0) {
					if ((etud.prenom).compareTo(ptr.prenom) == 0) {
						if ((etud.numero).compareTo(ptr.numero) == 0) {
							result=false;
							break;
						}

						if ((etud.numero).compareTo(ptr.numero) > 0) {
							if (ptr.right != null) {
								ptr = ptr.right;
							} else {
								ptr.right = etud;
								flag = 1;
							}
						}
						if ((etud.numero).compareTo(ptr.numero) < 0) {
							if (ptr.left != null) {
								ptr = ptr.left;
							} else {
								ptr.left = etud;
								flag = 1;
							}
						}
					}

					if ((etud.prenom).compareTo(ptr.prenom) > 0) {
						if (ptr.right != null) {
							ptr = ptr.right;
						} else {
							ptr.right = etud;
							flag = 1;
						}
					}
					if ((etud.prenom).compareTo(ptr.prenom) < 0) {
						if (ptr.left != null) {
							ptr = ptr.left;
						} else {
							ptr.left = etud;
							flag = 1;
						}
					}
				}
				if ((etud.nom).compareTo(ptr.nom) > 0) {
					if (ptr.right != null) {
						ptr = ptr.right;
					} else {
						ptr.right = etud;
						flag = 1;
					}
				}
				if ((etud.nom).compareTo(ptr.nom) < 0) {
					if (ptr.left != null) {
						ptr = ptr.left;
					} else {
						ptr.left = etud;
						flag = 1;
					}
				}
			}
		}
		flag = 0;
		return result;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////// METHODES ANNEXES
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/////////// méthode de suppresion par PRENOM
	public ArrayList<Etudiant> removeByPrenom(String str) {
		ArrayList<Etudiant> maListe = new ArrayList<Etudiant>();

		return maListe = removeByPrenom(root, str, maListe);
	}

	private ArrayList<Etudiant> removeByPrenom(Etudiant e, String str, ArrayList<Etudiant> maListe) {
		if (e != null) {
			removeByPrenom(e.left, str, maListe);

			if (!(e.prenom.contains(str))) {
				maListe.remove(e);
			}

			removeByPrenom(e.right, str, maListe);
		}
		return (maListe);
	}

	/////////// méthode de suppresion par NOM
	public ArrayList<Etudiant> removeByNom(String str) {
		ArrayList<Etudiant> maListe = new ArrayList<Etudiant>();

		return maListe = removeByNom(root, str, maListe);
	}

	private ArrayList<Etudiant> removeByNom(Etudiant e, String str, ArrayList<Etudiant> maListe) {
		if (e != null) {
			removeByNom(e.left, str, maListe);

			if (!(e.nom.contains(str))) {
				maListe.remove(e);
			}

			removeByNom(e.right, str, maListe);
		}
		return (maListe);
	}

	/////////// méthode de suppresion par NUMERO
	public ArrayList<Etudiant> removeByNumero(String str) {
		ArrayList<Etudiant> maListe = new ArrayList<Etudiant>();

		return maListe = removeByNumero(root, str, maListe);
	}

	private ArrayList<Etudiant> removeByNumero(Etudiant e, String str, ArrayList<Etudiant> maListe) {
		if (e != null) {
			removeByNumero(e.left, str, maListe);

			if (!(e.numero.contains(str))) {
				maListe.remove(e);
			}

			removeByNumero(e.right, str, maListe);
		}
		return (maListe);
	}

}