package main.controleur;

import main.tournoi.Joueur;
import main.vue.FenetreModifierJoueur;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * Controleur d' ouverture de la fenètre de modification d'un joueur en double cliquant dessus
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class ModifierJoueurControlleur implements MouseListener {
	private FenetrePrincipale fp;
	private JTable jt;

	/**
	 * Constructeur du controlleur de modification des joueurs
	 * @param jt la table contenant les joueurs
	 * @param fd la vue principale du tournoi
	 */
	public ModifierJoueurControlleur(FenetrePrincipale fd, JTable jt){
		this.fp = fd;
		this.jt = jt;
	}

	/**
	 * Ouvre la fenètre de modification du joueur sur le quel on double clique
	 * @param e evènement du doubleclique sur un joueur de la table
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && !e.isConsumed()) {
			e.consume();

			int row = jt.getSelectedRow();
			Joueur j = fp.getTournoi().chercherJoueur( jt.getValueAt(row, 0)+" "+jt.getValueAt(row, 1));
			new FenetreModifierJoueur("Modifier Joueur",this.fp.getTournoi(),this.fp,j);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
