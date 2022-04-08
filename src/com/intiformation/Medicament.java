package com.intiformation;

import java.text.DecimalFormat;

public class Medicament {
	
	protected String nom;
	protected double prix;
	protected int stock;
	protected static DecimalFormat _decf = new DecimalFormat("#.##");
	
	public Medicament() {
		super();
	}
	public Medicament(String nom, double prix, int stock) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.stock = stock;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Medicament [nom=" + nom + ", prix=" + _decf.format(prix) + ", stock=" + stock + "]";
	}
}
