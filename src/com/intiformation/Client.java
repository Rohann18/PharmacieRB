package com.intiformation;

import java.text.DecimalFormat;

public class Client {

		protected String nom;
		protected double credit = 0;
		protected static DecimalFormat _decf = new DecimalFormat("#.##");
		public Client() {
			super();
		}
		public Client(String nom) {
			super();
			this.nom = nom;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public double getCredit() {
			return credit;
		}
		public void setCredit(double credit) {
			this.credit = credit;
		}
		@Override
		public String toString() {
			return "Client [nom=" + nom + ", credit=" + _decf.format(credit) + "]";
		}
}
