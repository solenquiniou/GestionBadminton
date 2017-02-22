package main.controleur;

import main.exception.DateParsingExeption;
import main.tournoi.*;
import main.vue.FenetrePrincipale;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

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

                int terrainCourant = 1;
                ArrayList<String> terrain = new ArrayList<>();
                for(Node tourNode : listesTourNodes)
                {
                    Tour tour = new Tour();
                    NodeList terrainsNodes = tourNode.getChildNodes(); // Récupère les terrains d'un tour
                    int nbTerrain = terrainsNodes.getLength(); // compte le nombre de "terrain" dans le XML
                    for (int i = 0; i < nbTerrain; i++)
                    {
                        if (terrainsNodes.item(i).getNodeType() == Node.ELEMENT_NODE)
                        {
                            NodeList listeInfoTerrainNode = terrainsNodes.item(i).getChildNodes(); // Récupère toutes les informations d'un terrain du XML
                            int nbInfoTerrain = listeInfoTerrainNode.getLength(); // compte le nombre d'informations sur le terrain dans le XML
                            for (int z = 0; z < nbInfoTerrain; z++)
                            {
                                if(listeInfoTerrainNode.item(z).getNodeType() == Node.ELEMENT_NODE)
                                {
                                    Element info3 = (Element) listeInfoTerrainNode.item(z);
                                    terrain.add(info3.getTextContent());
                                }
                            }
                            ajouterTerrain(tour,terrain, terrainCourant);
                            terrain.clear();
                        }
                    }
                    terrainCourant = 1;
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        fenetre.actualiserJoueurs();
    }



    public void ajouterJoueur(ArrayList<String> lesInfosDuJoueur) throws DateParsingExeption {
        String nom = lesInfosDuJoueur.get(1), prenom = lesInfosDuJoueur.get(0),  datestr = lesInfosDuJoueur.get(4),  sexe = lesInfosDuJoueur.get(2),  nouveau = lesInfosDuJoueur.get(3),  niveau = lesInfosDuJoueur.get(5);
        LocalDate date = null;

        int  niveauJoueur = 0;
        boolean sexeJoueur, ancienneteJoueur;

        if(datestr.equals(""))
            date = null;
        else
            date = tournoi.parseDate(datestr);

        if (niveau.equals("Débutant"))
            niveauJoueur = 1;
        else if (niveau.equals("Intermédiaire"))
            niveauJoueur = 2;
        else if (niveau.equals("Confirmé"))
            niveauJoueur = 3;

        sexeJoueur = (sexe.equals("Homme"));

        ancienneteJoueur = (nouveau.equals("Nouveau"));

        Joueur j = new Joueur(Joueur.nbJoueursCrees, nom, prenom, date, sexeJoueur, ancienneteJoueur, niveauJoueur, true);
        if (!tournoi.getAnciensJoueurs().contains(j) && !tournoi.getNouveauxJoueurs().contains(j))
        {
            tournoi.ajouterJoueur(j);
            fenetre.ajouterJoueurTable();
        }
    }


    public void ajouterTerrain(Tour t, ArrayList<String> terr, int numTerrain)
    {
        String nomPrenomJ1 = terr.get(0)+" "+terr.get(1);
        String nomPrenomJ2 = terr.get(2)+" "+terr.get(3);
        String nomPrenomJ3 = terr.get(5)+" "+terr.get(6);
        String nomPrenomJ4 = terr.get(7)+" "+terr.get(8);
        Joueur j1 = tournoi.chercherJoueur(nomPrenomJ1);
        Joueur j2 = tournoi.chercherJoueur(nomPrenomJ2);
        Joueur j3 = tournoi.chercherJoueur(nomPrenomJ3);
        Joueur j4 = tournoi.chercherJoueur(nomPrenomJ4);

        j1.ajouterAnciensPart(j2);
        j2.ajouterAnciensPart(j1);
        j3.ajouterAnciensPart(j4);
        j4.ajouterAnciensPart(j3);

        Paire p1 = new Paire(j1, j2);
        Paire p2 = new Paire(j3, j4);

        Match m = new Match(p1,p2);
        m.modifierScores(Integer.parseInt(terr.get(4)),Integer.parseInt(terr.get(9)));
        Terrain te = new Terrain(numTerrain,m);

        t.addTerr(te);
        fenetre.actualiserJoueurs();
    }

}