package main.controleur;

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
		int row = jt.getSelectedRow();
		this.id = (int) jt.getValueAt(row, 0);
		new FenetreModifierJoueur("Modifier Joueur",this.fp.getTournoi(),this.fp,this.id);
		
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
