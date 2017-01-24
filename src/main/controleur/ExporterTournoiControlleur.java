package main.controleur;

import main.tournoi.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import main.vue.FenetrePrincipale;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;



/** La classe ExporterTournoiControlleur permet d'exporter un main.tournoi
 * @author DROUARD Antoine, DERNONCOURT Cyril, LE BERT Lea, MARTINEAU Lucas
 */
public class ExporterTournoiControlleur implements ActionListener {
    
    private Tournoi tournoi;
    private FenetrePrincipale fenetre;

    /** Constructeur de la classe ExporterTournoiControlleur
     *
     * @param fen la fenêtre principale où trouver les tours joués
     */
    public ExporterTournoiControlleur(FenetrePrincipale fen) {
        this.fenetre = fen; this.tournoi = fen.getTournoi();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Ouverture de la fenetre "enregistrer sous"
        Frame fr = new Frame("Choississez un répertoire");
        FileDialog dial = new FileDialog(fr, "Enregistrer sous", FileDialog.SAVE);
        dial.setFile("a.xml"); //Pré-écrit l'extension .xml dans la fenêtre de dialogue
        dial.setVisible(true);
        fr.setVisible(false);

        Element joueur, prenom, nom, sexe, anciennete, age, niveau, joueursDejaJoues, joueurPart, prenomPart, nomPart;
        int score;

        try
        {
            if (dial.getDirectory() != null && dial.getFile() != null)
            {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.newDocument();
                Element racine = document.createElement("tournoi");
                document.appendChild(racine);
                Element joueurs = document.createElement("listeJoueurs");
                racine.appendChild(joueurs);


                for (Joueur j : tournoi.getAllJoueurs())
                {
                    joueur = document.createElement("joueur");
                    joueurs.appendChild(joueur);
                    prenom = document.createElement("prenom");
                    nom = document.createElement("nom");
                    sexe = document.createElement("sexe");
                    anciennete = document.createElement("anciennete");
                    age = document.createElement("age");
                    niveau = document.createElement("niveau");
                    prenom.appendChild(document.createTextNode(j.getPrenom()));
                    nom.appendChild(document.createTextNode(j.getNom()));

                    if(j.getSexe())
                    {
                        sexe.appendChild(document.createTextNode("Homme"));
                    }
                    else
                    {
                        sexe.appendChild(document.createTextNode("Femme"));
                    }

                    if (j.getNouveau())
                    {
                        anciennete.appendChild(document.createTextNode("Nouveau"));
                    }
                    else
                    {
                        anciennete.appendChild(document.createTextNode("Ancien"));
                    }

                    switch (j.getAge()) {
                        case 1:  age.appendChild(document.createTextNode("-18 ans"));
                        case 2:  age.appendChild(document.createTextNode("18-35 ans"));
                        case 3:  age.appendChild(document.createTextNode("35+ ans"));
                            break;
                        default: age.appendChild(document.createTextNode(""));
                            break;
                    }

                    switch (j.getNiveau()) {
                        case 1:  niveau.appendChild(document.createTextNode("Débutant"));
                        case 2:  niveau.appendChild(document.createTextNode("Intermédiaire"));
                        case 3:  niveau.appendChild(document.createTextNode("Confirmé"));
                            break;
                        default: niveau.appendChild(document.createTextNode(""));
                            break;
                    }


                    joueursDejaJoues = document.createElement("joueursDejaJoues");

                    for (Joueur part : j.getAnciensPart()) {
                        joueurPart = document.createElement("joueurPart");
                        prenomPart = document.createElement("prenomPart");
                        prenomPart.appendChild(document.createTextNode(part.getPrenom()));

                        nomPart = document.createElement("nomPart");
                        nomPart.appendChild(document.createTextNode(part.getNom()));

                        joueurPart.appendChild(prenomPart);
                        joueurPart.appendChild(nomPart);
                        joueursDejaJoues.appendChild(joueurPart);
                    }

                    joueur.appendChild(prenom);
                    joueur.appendChild(nom);
                    joueur.appendChild(sexe);
                    joueur.appendChild(anciennete);
                    joueur.appendChild(age);
                    joueur.appendChild(niveau);
                    joueur.appendChild(joueursDejaJoues);
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult sortie = new StreamResult(new File(dial.getDirectory().concat(dial.getFile())));

                //prologue
                transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

                //formatage
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                //sortie
                transformer.transform(source, sortie);
            }
        }
        catch (final Exception exp)
        {
            exp.printStackTrace();
        }

    }






}
