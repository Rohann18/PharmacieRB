package com.intiformation;

import com.intiformation.exception.nbMedicamentNegException;
import com.intiformation.exception.notEnoughStockException;

public class Main {

	public static void main(String[] args) throws nbMedicamentNegException, notEnoughStockException {
		// TODO Auto-generated method stub
		
		Pharmacie pharma = new Pharmacie();
		
		pharma.fonctionnement();
	}

}
