package com.intiformation;

import java.util.ArrayList;
import java.util.Scanner;

import com.intiformation.exception.nbMedicamentNegException;
import com.intiformation.exception.notEnoughStockException;

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

	public void supprimer() {
		Scanner sc = new Scanner(System.in);
		int key, c;
		do {
			System.out.println("Souhaitez-vous supprimer un client (1) ou un médicament (2)?");
			key = sc.nextInt();
			switch (key) {
			case 1:
				supprimerClient();
				break;
			case 2:
				supprimerMedicament();
				break;
			default:
				break;
			}
			System.out.println("Souhaitez-vous supprimer autre chose ? oui(1)");
			c = sc.nextInt();
		} while (c == 1);
	}

	public void supprimerClient() {
		Client client = lireClient();
		listeClient.remove(client);
	}

	public void supprimerMedicament() {
		Medicament medicament = lireMedicament();
		listeMedicament.remove(medicament);
	}

	public void modifPrix() {
		Scanner sc = new Scanner(System.in);
		double prix = 0;
		Medicament medicament = lireMedicament();
		do {
			System.out.println("A quel prix souhaitez-vous mettre le médicament ?");
			prix = sc.nextDouble();
		} while (prix <= 0);
		medicament.setPrix(prix);
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
		Medicament medicament = lireMedicament();
		System.out.println("Quelle quantité souhaitez-vous ajouter ?");
		int nbAjout = sc.nextInt();
		try {
			ajoutStock(nbAjout, medicament);
		} catch (nbMedicamentNegException e) {

		}
	}

	public void achat() {
		Scanner sc = new Scanner(System.in);
		int choix;
		Medicament medicament = lireMedicament();
		Client client = lireClient();
		System.out.println("Quelle quantité souhaite-t-il acheter?");
		int nbEnlever = sc.nextInt();
		try {
			enleverStock(nbEnlever, medicament);
		} catch (notEnoughStockException e) {
			System.out.println("Stock restant : " + medicament.getStock());
			System.out.println("Voulez-vous vendre le stock restant ? oui (1)");
			choix = sc.nextInt();
			if (choix == 1) {
				nbEnlever = medicament.getStock();
				try {
					enleverStock(nbEnlever, medicament);
				} catch (notEnoughStockException e2) {

				}
				System.out.println("Quelle montant à verser le client?");
				double montant = sc.nextDouble();
				paiement(client, medicament, nbEnlever, montant);
			} else {
				nbEnlever = 0;
			}
		}
	}

	public void quitter() {
		System.out.println("Fin de l'application !");
	}

	public int verifMedicament(String nom) {
		int id = -1;
		for (int i = 0; i < listeMedicament.size(); i++) {
			if (listeMedicament.get(i).getNom().equals(nom)) {
				id = i;
			}
		}
		if (id == -1) {
			System.err.println("Le nom de médicament rentré n'est pas connu");
		}
		return id;
	}

	public Medicament lireMedicament() {
		String nom;
		Scanner sc = new Scanner(System.in);
		do {
			affichage("medicament");
			System.out.println("Quel médicament ?");
			nom = sc.next();
		} while (verifMedicament(nom) == -1);
		return listeMedicament.get(verifMedicament(nom));
	}

	public int verifClient(String nom) {
		int id = -1;
		for (int i = 0; i < listeClient.size(); i++) {
			if (listeClient.get(i).getNom().equals(nom)) {
				id = i;
			}
		}
		if (id == -1) {
			System.err.println("Le nom de client rentré n'est pas connu");
		}
		return id;
	}

	public Client lireClient() {
		String nom;
		Scanner sc = new Scanner(System.in);
		do {
			affichage("client");
			System.out.println("Quel client ?");
			nom = sc.next();
		} while (verifClient(nom) == -1);
		return listeClient.get(verifClient(nom));
	}

	public void ajoutStock(int stock, Medicament medicament) throws nbMedicamentNegException {
		if (stock < 0) {
			throw new nbMedicamentNegException();
		} else {
			medicament.stock = medicament.getStock() + stock;
		}
	}

	public void enleverStock(int stock, Medicament medicament) throws notEnoughStockException {
		if (stock > medicament.getStock()) {
			throw new notEnoughStockException();
		} else {
			medicament.stock = medicament.getStock() - stock;
		}
	}

	public void paiement(Client client, Medicament medicament, int stock, double montant) {
		client.credit = client.getCredit() + (medicament.getPrix() * stock - montant);
	}

	public void fonctionnement() throws nbMedicamentNegException, notEnoughStockException {
		System.out.println("Bonjour !");
		Scanner sc = new Scanner(System.in);
		String choix;
		boolean fin = true;
		int key;
		do {
			System.out.println("Que souhaitez-vous faire ? créer (1), approvisionner (2), achat (3), afficher (4)");
			System.out.println("modifier prix (5), supprimer (6) ou quitter(7)");
			key = sc.nextInt();
			switch (key) {
			case 1:
				creer();
				break;
			case 2:
				approvisionner();
				break;
			case 3:
				achat();
				break;
			case 4:
				System.out.println("Que souhaitez-vous afficher ? medicament/client/tout");
				choix = sc.next();
				if (choix.equals("tout")) {
					print();
				} else if (choix.equals("client")) {
					affichage(choix);
				} else if (choix.equals("medicament")) {
					affichage(choix);
				} else {
					System.err.println("Votre choix n'est pas le bon");
				}
				break;
			case 5:
				modifPrix();
				break;
			case 6:
				supprimer();
				break;
			case 7:
				quitter();
				fin = false;
				break;
			default:
				break;
			}
		} while (fin);
	}

}
