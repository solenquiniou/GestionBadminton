package main.controleur;

import main.vue.GestionJoueur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe DateIndefinieControlleur permet de ne pas renseigner sa dâte de naissance si on ne le souhaite pas.
 * @author DERNONCOURT Cyril , DROUARD Antoine, LE BERT Lea, MARTINEAU Lucas
 */
public class DateIndefinieControlleur implements ActionListener {
    private JCheckBox box;
    private GestionJoueur vue;

    /**
     * Le Controlleur de la classe DateIndefinieControlleur
     * @param box Le JCheckBox sur lequel le controlleur est appliqué, il
     * @param vue La vue contenant les 3 champs à désactivé en fonction de la valeur du JCheckBox
     */
    public DateIndefinieControlleur(JCheckBox box, GestionJoueur vue) {
        this.box = box;
        this.vue = vue;
    }

    /**
     * Désactive ou active les champs lié à la dâte de naissance en fonciton de l'état du JCheckBox
     * @param e Un changement d'état du JCheckBox
     */
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
