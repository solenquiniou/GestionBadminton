package main.app;

import main.vue.*;

public class App {

	public static void main(String[] args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		FenetrePrincipale fen = new FenetrePrincipale("Nouveau Tournoi");
		NouveauTournoi tourn = new NouveauTournoi(fen);
	}

}
