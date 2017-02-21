package main.controleur;

import main.vue.GestionJoueur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Antoine Drouard, Cyril Dernoncourt,MArtinneau Lucas, Le BErt Léa in 2017
 */
public class DateInfefinieControlleur implements ActionListener {
    private JCheckBox box;
    private GestionJoueur vue;

    public DateInfefinieControlleur(JCheckBox box,GestionJoueur vue) {
        this.box = box;
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (box.isSelected()){
            vue.getAnnee().setEnabled(false);
            vue.getJour().setEnabled(false);
            vue.getMois().setEnabled(false);
        }else{
            vue.getAnnee().setEnabled(true);
            vue.getJour().setEnabled(true);
            vue.getMois().setEnabled(true);
        }

    }
}
