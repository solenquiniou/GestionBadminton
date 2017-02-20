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
public class ImporterTournoiControlleur implements ActionListener
{
    private Tournoi tournoi;
    private FenetrePrincipale fenetre;

    /** Constructeur de la classe ImporterTournoiControlleur
     *
     * @param fen la fenêtre principale où insérer les tours joués
     */
    public ImporterTournoiControlleur(FenetrePrincipale fen)
    {
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
        if (dial.getFile() != null)
        {
            try
            {
                String xmlFile = dial.getDirectory().concat(dial.getFile());

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

                DocumentBuilder builder = factory.newDocumentBuilder();

                Document document = builder.parse(new File(xmlFile));

                Element tournoi = document.getDocumentElement();

                NodeList tournoiNoeuds = tournoi.getChildNodes(); // Récupère listeJoueurs et listeTours
                int nbTournoiNoeuds = tournoiNoeuds.getLength(); // compte le nombre de noeuds de la racine (ici 2)
                ArrayList<Node> listesJoueursMatchesNodes= new ArrayList<Node>();
                for (int i = 0; i<nbTournoiNoeuds; i++)
                {
                    if(tournoiNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE)
                    {
                        listesJoueursMatchesNodes.add(tournoiNoeuds.item(i));
                    }
                }

                NodeList listeJoueursNoeuds = listesJoueursMatchesNodes.get(0).getChildNodes(); // Récupère tous les "joueur" du XML
                int nbJoueurs = listeJoueursNoeuds.getLength(); // compte le nombre de "joueur" dans le XML
                ArrayList<Node> listesJoueursNodes= new ArrayList<Node>();
                for (int i = 0; i<nbJoueurs; i++)
                {
                    if(listeJoueursNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE)
                    {
                        listesJoueursNodes.add(listeJoueursNoeuds.item(i));
                    }
                }

                ArrayList<String> infosDuJoueur = new ArrayList<String>();
                for(Node joueurNode : listesJoueursNodes)
                {
                    NodeList listeInfoNode = joueurNode.getChildNodes(); // Récupère toutes les informations d'un joueur du XML
                    int nbInfo = listeInfoNode.getLength(); // compte le nombre d'informations sur le joueur dans le XML
                    for (int i = 0; i < nbInfo; i++)
                    {
                        if(listeInfoNode.item(i).getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element info = (Element) listeInfoNode.item(i);
                            infosDuJoueur.add(info.getTextContent());
                        }
                    }
                    ajouterJoueur(infosDuJoueur);
                    infosDuJoueur.clear();
                }

                NodeList listeMatchesNoeuds = listesJoueursMatchesNodes.get(1).getChildNodes(); // Récupère tous les "tour" du XML
                int nbTour = listeMatchesNoeuds.getLength(); // compte le nombre de "tour" dans le XML
                ArrayList<Node> listesTourNodes= new ArrayList<Node>();
                for (int i = 0; i < nbTour; i++)
                {
                    if(listeMatchesNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE)
                    {
                        listesTourNodes.add(listeMatchesNoeuds.item(i));
                    }
                }

                ArrayList<String> terrains = new ArrayList<>();
                for(Node tourNode : listesTourNodes)
                {
                    NodeList terrainsNodes = tourNode.getChildNodes(); // Récupère les terrains d'un tour
                    int nbTerrain = terrainsNodes.getLength(); // compte le nombre de "terrain" dans le XML
                    for (int i = 0; i < nbTerrain; i++)
                    {
                        if (terrainsNodes.item(i).getNodeType() == Node.ELEMENT_NODE)
                        {
                            System.out.println("               Début");
                            NodeList listeInfoTerrainNode = terrainsNodes.item(i).getChildNodes(); // Récupère toutes les informations d'un terrain du XML
                            int nbInfoTerrain = listeInfoTerrainNode.getLength(); // compte le nombre d'informations sur le terrain dans le XML
                            for (int z = 0; z < nbInfoTerrain; z++)
                            {
                                if(listeInfoTerrainNode.item(z).getNodeType() == Node.ELEMENT_NODE)
                                {
                                    Element info3 = (Element) listeInfoTerrainNode.item(z);
                                    terrains.add(info3.getTextContent());
                                }
                            }
                            System.out.println(terrains);
                            terrains.clear();
                            System.out.println("               Fin");
                            System.out.println("\n\n\n\n");
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }



    public void ajouterJoueur(ArrayList<String> lesInfosDuJoueur)
    {
        String nom = lesInfosDuJoueur.get(1), prenom = lesInfosDuJoueur.get(0),  age = lesInfosDuJoueur.get(4),  sexe = lesInfosDuJoueur.get(2),  nouveau = lesInfosDuJoueur.get(3),  niveau = lesInfosDuJoueur.get(5);


        int ageJoueur = 0, niveauJoueur = 0;
        boolean sexeJoueur, ancienneteJoueur;

        if (age.equals("-18 ans"))
            ageJoueur = 1;
        else if (age.equals("18-35 ans"))
            ageJoueur = 2;
        else if (age.equals("35+ ans"))
            ageJoueur = 3;


        if (niveau.equals("Débutant"))
            niveauJoueur = 1;
        else if (niveau.equals("Intermédiaire"))
            niveauJoueur = 2;
        else if (niveau.equals("Confirmé"))
            niveauJoueur = 3;

        sexeJoueur = (sexe.equals("Homme"));

        ancienneteJoueur = (nouveau.equals("Nouveau"));

        Joueur j = new Joueur(Joueur.nbJoueursCrees, nom, prenom, ageJoueur, sexeJoueur, ancienneteJoueur, niveauJoueur, true);

        if (!tournoi.getAnciensJoueurs().contains(j) && !tournoi.getNouveauxJoueurs().contains(j))
        {
            tournoi.ajouterJoueur(j);
            fenetre.ajouterJoueurTable();
        }
    }

}