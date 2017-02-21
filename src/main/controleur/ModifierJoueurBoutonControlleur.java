package main.controleur;

import main.vue.FenetreModifierJoueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ModifierJoueurBoutonControlleur implements ActionListener {

	private FenetreModifierJoueur fmj;
	private int id;
	
	public ModifierJoueurBoutonControlleur(FenetreModifierJoueur fmj, int id)
	{
		this.fmj = fmj;
		this.id = id;
	}
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		LocalDate datej = null;
		if(!fmj.getDateIndefinie().isSelected()) {
			datej = datej.of((Integer) fmj.getAnnee().getSelectedItem(), fmj.getMois().getSelectedIndex() + 1, (Integer) fmj.getJour().getSelectedItem()) ;
		}
		String nom = fmj.getNom().getText(), prenom = fmj.getPrenom().getText();
		boolean sexe = !fmj.getFem().isSelected();
		boolean nouve = fmj.getNouv().isSelected();
		int niveau = fmj.getNiveau().getSelectedIndex();
		fmj.getTournoi().modifierJoueur(id, nom, prenom, datej, sexe, nouve, niveau);
		if(fmj.getPresent().isSelected()) {
			this.fmj.getTournoi().getJoueur(id).setPeutJouer(true);
		} else {
			this.fmj.getTournoi().getJoueur(id).setPeutJouer(false);
		}
		fmj.getfenetrePrincipale().actualiserJoueurs();
		fmj.dispose();
	}
}