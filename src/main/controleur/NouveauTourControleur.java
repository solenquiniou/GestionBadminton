package main.controleur;

import main.tournoi.Tournoi;
import main.vue.FenetrePrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controleur du nouveau Tour. Enregistre le tour précédent et lqance un nouveau toiur.
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Léa, MARTINEAU Lucas
 * @version 1.1
 */
public class NouveauTourControleur implements ActionListener {
    private FenetrePrincipale vue;
    private Tournoi tournoi;

    /**
     * Constructeur du controlleur nouveau tour
     * @param vue la vue principale du tournoi
     */
    public NouveauTourControleur(FenetrePrincipale vue){
        this.vue = vue;
        this.tournoi = vue.getTournoi();
    }

    /**
     * Enregistre le tour précédent et lance le suivant
     * @param e evènement du clique sur le boutton "valider" d'un terrain
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(tournoi.getNbTour()>0){
                if(!tournoi.tourFini()){
                    Object[] options = { "OUI", "ANNULER" };
                    int choix = JOptionPane.showOptionDialog(null, "Certains matches n'ont pas été validés, ils ne seront pas enregistrés. Êtes vous sur de vouloir valider?", "Vérification",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                    if(choix == 0) {
                        this.tournoi.supprMatchEncours();
                        this.tournoi.finirTour();//enregistre le tour et nettoye les paires
                        this.vue.ajouterTourListe();
                    }

                }


            }

            this.tournoi.nouveauTour();
            this.vue.actualiserTerrains();


            //this.main.vue.afficherTournoi();

        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

}



