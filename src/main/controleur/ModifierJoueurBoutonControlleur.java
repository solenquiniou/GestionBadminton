package main.controleur;

import main.vue.FenetreModifierJoueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
/**
 * Controleur de la validation de la modification d'un joueur
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class ModifierJoueurBoutonControlleur implements ActionListener {

	private FenetreModifierJoueur fmj;
	private int id;
	/**
	 * Constructeur de la classe ModifierJoueurBoutonControlleur
	 * @param id l'id du joueur qui était en cours de modificatin
	 * @param fmj la fenètre de modification du joueur (pour acceder au champs)
	 */
	public ModifierJoueurBoutonControlleur(FenetreModifierJoueur fmj, int id)
	{
		this.fmj = fmj;
		this.id = id;
	}

	/**
	 * Valide les modifications entrées sur le joueur et les envoie dans le modèle puis actualise la vue des joueurs.
	 * @param e evènement du clique sur le boutton "valider" de la vue de modification des joueurs
	 */
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