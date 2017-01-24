package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetreClassement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AfficherClassementControleur implements ActionListener
{
    private FenetreClassement vue;
    private JComboBox cat;
    private Tournoi tournoi;

    public AfficherClassementControleur(FenetreClassement fenetreClassement, JComboBox categorie)
    {
            vue = fenetreClassement;
            cat = categorie;


    }
    public void actionPerformed(ActionEvent e)
    {

            this.vue.afficherJoueur((String) cat.getSelectedItem());

    }

}
