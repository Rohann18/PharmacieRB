package com.intiformation;

import java.util.ArrayList;
import java.util.Scanner;

public class Pharmacie {

	private ArrayList<Client> listeClient = new ArrayList<Client>();
	private ArrayList<Medicament> listeMedicament = new ArrayList<Medicament>();

	public Pharmacie() {

	}

	public void creer() {
		Scanner sc = new Scanner(System.in);
		int key, c;
		do {
			System.out.println("Souhaitez-vous créer un client (1) ou un médicament (2)?");
			key = sc.nextInt();
			switch (key) {
			case 1:
				creerClient();
				break;
			case 2:
				creerMedicament();
				break;
			default:
				break;
			}
			System.out.println("Souhaitez-vous créer autre chose ? oui(1)");
			c = sc.nextInt();
		} while (c == 1);
	}

	public void creerClient() {
		String nom;
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel est le nom du client ?");
		nom = sc.next();
		listeClient.add(new Client(nom));
	}

	public void creerMedicament() {
		String nom;
		double prix;
		int stock;
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel est le nom du medicament ?");
		nom = sc.next();
		System.out.println("Quel est le prix du medicament ?");
		prix = sc.nextDouble();
		System.out.println("Quel est le stock initial du medicament ?");
		stock = sc.nextInt();
		listeMedicament.add(new Medicament(nom, prix, stock));
	}
	
	public void print() {
		System.out.println(listeClient);
		System.out.println(listeMedicament);
	}
	public void affichage(String categorie) {
		if (categorie.equals("client")) {
			System.out.println(listeClient);
		} else if (categorie.equals("medicament")) {
			System.out.println(listeMedicament);
		} else {
			System.err.println("Vous n'avez choisi de bonne catégorie");
		}
	}

	public void approvisionner() {
		Scanner sc = new Scanner(System.in);
		String med;
		int id;
		affichage("medicament");
		do {
			System.out.println("Quel médicament souhaitez-vous approvisionner?");
			med = sc.next();
		} while (lireMedicament(med) == -1);
		id = lireMedicament(med);
		System.out.println("Quelle quantité souhaitez-vous ajouter ?");
		int nbAjout = sc.nextInt();
		ajoutStock(nbAjout, id);
	}

	public void achat() {
		Scanner sc = new Scanner(System.in);
		String med, nom;
		int idm, idc;
		affichage("medicament");
		do {
			System.out.println("Quel médicament souhaitez-vous acheter?");
			med = sc.next();
		} while (lireMedicament(med) == -1);
		idm = lireMedicament(med);
		affichage("client");
		do {
			System.out.println("Quel client achète?");
			nom = sc.next();
		} while (lireClient(nom) == -1);
		idc = lireClient(nom);
		System.out.println("Quelle quantité souhaitez-vous acheter?");
		int nbEnlever = sc.nextInt();
		enleverStock(nbEnlever, idm);
		listeMedicament.get(idm).stock = listeMedicament.get(idm).getStock() - nbEnlever;
		System.out.println("Quelle montant à verser le client?");
		double montant = sc.nextDouble();
		paiement(idc, idm, nbEnlever, montant);
	}

	public void quitter() {
		System.out.println("Fin de l'application !");
	}

	public int lireMedicament(String nom) {
		int id = -1;
		for (int i = 0; i < listeMedicament.size(); i++) {
			if (listeMedicament.get(i).getNom().equals(nom)) {
				id = i;
			}
		}
		return id;
	}

	public int lireClient(String nom) {
		int id = -1;
		for (int i = 0; i < listeClient.size(); i++) {
			if (listeClient.get(i).getNom().equals(nom)) {
				id = i;
			}
		}
		return id;
	}

	public void ajoutStock(int stock, int id) {
		listeMedicament.get(id).stock = listeMedicament.get(id).getStock() + stock;
	}

	public void enleverStock(int stock, int id) {
		listeMedicament.get(id).stock = listeMedicament.get(id).getStock() - stock;
	}

	public void paiement(int idc, int idm, int stock, double montant) {
		listeClient.get(idc).credit = listeClient.get(idc).getCredit()
				+ (listeMedicament.get(idm).getPrix() * stock - montant);
	}

	public void fonctionnement() {
		System.out.println("Bonjour !");
		Scanner sc = new Scanner(System.in);
		boolean fin = true;
		int key;
		do {
			System.out.println("Que souhaitez-vous faire ? créer (1), approvisionner (2), achat (3), afficher (4) quitter(5)");
			key = sc.nextInt();
			switch (key) {
			case 1 :
				creer();
				break;
			case 2 :
				approvisionner();
				break;
			case 3 :
				achat();
				break;
			case 4 :
				print();
				break;
			case 5 :
				quitter();
				fin = false;
				break;
			default :
				break;
			}
		} while (fin);
	}

}
