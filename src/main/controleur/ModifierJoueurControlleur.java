package main.controleur;

import main.tournoi.Joueur;
import main.vue.FenetreModifierJoueur;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class ModifierJoueurControlleur implements MouseListener {
	

	private FenetrePrincipale fp;
	private JTable jt;
	private int id;
	
	public ModifierJoueurControlleur(FenetrePrincipale fd, JTable jt){
		this.fp = fd;
		this.jt = jt;
	}

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
