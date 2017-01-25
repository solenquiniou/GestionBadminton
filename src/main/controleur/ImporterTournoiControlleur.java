package main.controleur;

import main.tournoi.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import main.vue.FenetrePrincipale;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/** La classe ImporterTournoiControlleur permet d'importer un tournoi
 * @author DROUARD Antoine, DERNONCOURT Cyril, LE BERT Lea, MARTINEAU Lucas
 */
public class ImporterTournoiControlleur implements ActionListener {
}

    private Tournoi tournoi;
    private FenetrePrincipale fenetre;

    /** Constructeur de la classe ImporterTournoiControlleur
     *
     * @param fen la fenêtre principale où insérer les tours joués
     */
    public ImporterTournoiControlleur(FenetrePrincipale fen) {
        this.fenetre = fen;
        this.tournoi = fen.getTournoi();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {


        Frame fr = new Frame("Choississez un répertoire");
        FileDialog dial = new FileDialog(fr, "Importer un tournoi", FileDialog.LOAD);
        dial.setFile("*.xml");
        dial.setVisible(true);
        fr.setVisible(false);
        if (dial.getFile() != null) {
            String xmlFile = dial.getDirectory().concat(dial.getFile());
            System.out.println(xmlFile);
            /*
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            try {
                DocumentBuilder builder = factory.newDocumentBuilder();


                Document document = builder.parse(new File("repertoire.xml"));


                Element racine = document.getDocumentElement();

                //Affichage de l'élément racine
                System.out.println("\n*************RACINE************");
                System.out.println(racine.getNodeName());


                NodeList racineNoeuds = racine.getChildNodes();
                int nbRacineNoeuds = racineNoeuds.getLength();

                for (int i = 0; i < nbRacineNoeuds; i++) {
                    if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        Element personne = (Element) racineNoeuds.item(i);

                        //Affichage d'une personne
                        System.out.println("\n*************PERSONNE************");
                        System.out.println("sexe : " + personne.getAttribute("sexe"));


                        Element nom = (Element) personne.getElementsByTagName("nom").item(0);
                        Element prenom = (Element) personne.getElementsByTagName("prenom").item(0);

                        //Affichage du nom et du prénom
                        System.out.println("nom : " + nom.getTextContent());
                        System.out.println("prénom : " + prenom.getTextContent());


                        NodeList telephones = personne.getElementsByTagName("telephone");
                        int nbTelephonesElements = telephones.getLength();

                        for (int j = 0; j < nbTelephonesElements; j++) {
                            Element telephone = (Element) telephones.item(j);

                            //Affichage du téléphone
                            System.out.println(telephone.getAttribute("type") + " : " + telephone.getTextContent());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        }
    }
}