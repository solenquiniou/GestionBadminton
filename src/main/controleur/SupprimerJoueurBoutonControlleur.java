package main.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import main.vue.FenetreModifierJoueur;


public class SupprimerJoueurBoutonControlleur implements ActionListener
{
    private FenetreModifierJoueur fmj;
    private int id;

    public SupprimerJoueurBoutonControlleur(FenetreModifierJoueur fmj, int id)
    {
        this.fmj = fmj;
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       fmj.supprimerJoueur(this.id);
    }
}
