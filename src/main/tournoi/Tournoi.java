package main.tournoi;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import main.exception.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Tournoi est la classe représentant un Tournoi.
 * @author OUAKRIM Yanis, RICHARD Nicolas, ORHON Paul, RIALET Yohann, NIVELAIS Quentin
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 * @version 1.1
 */

public class Tournoi {

	private ArrayList<Joueur> nouveauxJoueurs;
	private ArrayList<Joueur> anciensJoueurs;
	private ArrayList<Terrain> terrains;
	private ArrayList<Paire> paires;
	private ArrayList<Match> matchs;
	private int nbrTerrains;
	private String nom;
	private ArrayList<Tour> tour;
	private int nbTour;

	/**
	 * Constructeur d'un tournoi
	 * @param nbrTerrains le nompbre de terrains disponibles pour le tournoi (peut être modifié par la suite)
	 * @param leNom       le nom du tournoi
	 * @throws NomVideException s'il n'y a pas de nom
	 * @throws NbTerrainNeg     si le nombre des terrains est <= 0
	 */
	public Tournoi(int nbrTerrains, String leNom) throws NomVideException, NbTerrainNeg {

		if (leNom.equals(""))
			throw new NomVideException("Nom vide");
		else if (nbrTerrains < 1)
			throw new NbTerrainNeg("Nombre de terrain négatif");

		this.nouveauxJoueurs = new ArrayList<>();
		this.anciensJoueurs = new ArrayList<>();
		this.terrains = new ArrayList<>();
		this.paires = new ArrayList<>();
		this.matchs = new ArrayList<>();
		this.nbrTerrains = nbrTerrains;
		this.nom = leNom;
		initialiserTerrains();
		this.tour = new ArrayList<>();
		nbTour = 0;
		Joueur.resetNbJoueur();
	}

	/**
	 *
	 * @param nbrTerrains le nompbre de terrains disponibles pour le tournoi (peut être modifié par la suite)
	 * @throws NomVideException s'il n'y a pas de nom
	 * @throws NbTerrainNeg si le nombre des terrains est <= 0
     */
	public Tournoi(int nbrTerrains) throws NomVideException, NbTerrainNeg {
		this(nbrTerrains, "Sans titre");
	}

	/**
	 * Redéfinit l'attribut "nbrTerrains"
	 * @param nbrTerrains le nouveau nombre de terrains du tournoi
	 */
	public void setNbrTerrains(int nbrTerrains) {
		this.nbrTerrains = nbrTerrains;
	}

	/**
	 * Renvoie si le tournoi est vide, c'est à dire s'il n'y a pas de joueurs dans le tournoi
	 * @return true s'il n'y a pas de joueurs dans le tournoi, false s'il y a des joueurs
     */
	public Boolean tournoisVide(){
		return this.nbJoueur() == 0;
	}

	/**
	 * Trouve un joueur à l'aide de son id, ou null si on ne trouve pas de joueur avec cet id
	 * @return le joueur, ou null
	 */
	public Joueur getJoueur(int id) {

		Joueur j = null;

		for (Joueur joueur : getAllJoueurs())
			if (id == joueur.getId())
				j = joueur;

		return j;
	}

	/**
	 * Retourne la liste de nouveaux joueurs
	 * @return la liste des nouveaux joueurs adhérents
	 */
	public ArrayList<Joueur> getNouveauxJoueurs() {
		return this.nouveauxJoueurs;
	}

	/**
	 * Retourne la liste des anciens joueurs
	 * @return la liste des anciens joueurs adhérents
	 */
	public ArrayList<Joueur> getAnciensJoueurs() {
		return this.anciensJoueurs;
	}

	/**
	 * Retourne la liste de tous les joueurs
	 * @return la liste de tous les joueurs adhérents
	 */
	public ArrayList<Joueur> getAllJoueurs() {
		ArrayList<Joueur> allJoueurs = new ArrayList<>();

		allJoueurs.addAll(this.nouveauxJoueurs);
		allJoueurs.addAll(this.anciensJoueurs);
		return allJoueurs;
	}

	/**
	 * Retourne le nombre de joueur du tournoi
	 * @return le nombre de joueur du tournoi
     */
	public int nbJoueur(){
		return getAllJoueurs().size();
	}

	/**
	 * Retourne la liste des terrains
	 * @return la liste des terrains
	 */
	public ArrayList<Terrain> getTerrains() {
		return this.terrains;
	}

	/**
	 * Retourne le terrain à un index voulu
	 * @param i l'index du terrain voulu
	 * @return un terrain à l'index voulu
	 */
	public Terrain getTerrain(int i) {
		return this.terrains.get(i);
	}

	/**
	 * Retourne la liste des tours
	 * @return la liste des tours
	 */
	public ArrayList<Tour> getTours() { return this.tour; }

	/**
	 * Initialise les terrains disponibles
	 */
	public void initialiserTerrains() {
		for (int i = 0; i < this.nbrTerrains; i++) {
			this.terrains.add(new Terrain(i + 1));
		}
	}

	/**
	 * Retourne le nombre de terrains
	 * @return le nombre de terrains disponibles
	 */
	public int getNbrTerrains() {
		return this.nbrTerrains;
	}

	/**
	 * Ajoute un joueur à la liste des joueurs du tournoi
	 * @param joueur Le joueur que l'on souhaite ajouter
	 */
	public void ajouterJoueur(Joueur joueur) {
		if (joueur.getNouveau()) {
			this.nouveauxJoueurs.add(joueur);
		} else {
			this.anciensJoueurs.add(joueur);
		}
	}

	/**
	 * Supprime un joueur de la liste des joueurs du tournoi
	 * @param joueur Le joueur que l'on souhaite supprimer
     */
	public void supprimerJoueur(Joueur joueur) {
		if (joueur.getNouveau()) {
			this.nouveauxJoueurs.remove(joueur);
		} else {
			this.anciensJoueurs.remove(joueur);
		}
	}

	/**
	 * Tri des nouveaux joueurs par scores
	 */
	private void trierNouveauxJoueurs() {
		Collections.sort(this.nouveauxJoueurs, new ComparateurJoueurScore());
	}

	/**
	 * Tri des Anciens joueurs en fonction du score
	 */
	private void trierAnciensJoueurs() {
		Collections.sort(this.anciensJoueurs,new ComparateurJoueurScore());
	}

	/**
	 * Appelée pour créer la liste des paires d'un tournoi
	 * On attribue un partenaire uniquement aux joueurs actifs (ceux qui sont disponibles pour jouer)
	 * On cherche d'abord à faire jouer ceux qui ont le moins participé
	 * On cherche ensuite à faire jouer les joueurs qui n'ont pas joué au tour d'avant (les prios)
	 * On fait ensuite jouer les autres joueurs
	 * @throws TournoiVideException s'il n'y a pas de joueurs
	 */
	private void creerPaires() throws TournoiVideException {
		//On met tout les joueurs comme n'appartenant pas a une paire
		this.viderGetDansPaire();
		this.paires.clear(); //Vide la liste des paires

		if (this.getAllJoueurs().size() == 0) {
			throw new TournoiVideException("Il n'y a pas de joueurs dans le tournoi");
		}

		ArrayList<Paire> pairesCrees = MethodeTournoi.creerPaire(this.getAllJoueurs());

		for (Paire paire: pairesCrees) {
			this.paires.add(paire);
			paire.getJoueur1().setDansPaire(true);
			paire.getJoueur2().setDansPaire(true);
		}
	}

	/**
	 * Tris des paires par ordre de pref
	 */
	private void trierPaires() {
		Collections.sort(this.paires, new ComparateurPairePrio());
	}

	/**
	 * On attribue les match à des paires
	 * Ce qui se passe : les paires les plus nulles sont les premières à avoir un terrain d'attribué.
	 * Ce qu'on veut faire : chaque paire a la même probabilité de se voir attribuer un terrain.
	 * Pour cela on créé une liste de matchs avec les paires rangées par niveau, on mélange la liste de matchs,
	 * on attribue les terrains aux matchs prio, puis on attribue les terrains aux autres matchs
	 */
	private void attribuerMatchs() {
		trierPaires();

		//On créer une liste de matchs avec les paires couplées par niveau
		int i;
		this.matchs.clear();
		// prise en compte de qui a deja jouer avec qui
		for(Paire paire1 : this.paires)
		{
			for(Paire paire2 : this.paires)
			{
				//les paires sont differentes, compatibles et libres
				if(paire1!=paire2 && paire1.estCompatible(paire2) && !paire1.isDansMatch() && !paire2.isDansMatch()){
					matchs.add(new Match(paire1, paire2));
					paire1.setDansMatch(true);
					paire2.setDansMatch(true);
				}
			}
		}
		//on reparcours pour associer les dernières paires meme si elles ne tsont pas compatibles
		for(Paire paire1 : this.paires)
		{
			for(Paire paire2 : this.paires)
			{
				//les paires sont differentes, compatibles et libres
				if(paire1!=paire2 && !paire1.isDansMatch() && !paire2.isDansMatch()){
					matchs.add(new Match(paire1, paire2));
					paire1.setDansMatch(true);
					paire2.setDansMatch(true);

				}
			}
		}
		//range les match en untilisant prio comme comparateur
		Collections.sort(matchs,new ComparateurMatchPrio());

		//on vide les terrains
		terrains.clear();
		//On parcourt les matchs et on leur attribue les terrains restants
		for( i=0;i<matchs.size()&&i<this.nbrTerrains;i++ ){
			terrains.add(i, new Terrain(i, matchs.get(i)));
			matchs.get(i).getPaire1().getJoueur1().setPrio(false);
			matchs.get(i).getPaire1().getJoueur2().setPrio(false);
			matchs.get(i).getPaire2().getJoueur1().setPrio(false);
			matchs.get(i).getPaire2().getJoueur2().setPrio(false);
		}

		if(PersonnePrio()){
			ArrayList<Joueur> listJ = getAllJoueurs();
			for (Joueur j :listJ)
				j.setPrio(true);
		}
	}

	/**
	 * Regarde si aucun des joueurs n'est prio
	 * @return true si aucun joueur n'est prio, false sinon
     */
	public boolean PersonnePrio() {
		for (Joueur j : getAllJoueurs())
			if(j.getPrio())
				return false;
		return true;
	}

	/**
	 * Lance les methodes de créations de paires
	 * @throws TournoiVideException s'il n'y a pas de joueurs
     */
	public void nouveauTour() throws TournoiVideException {
		this.creerPaires();
		this.attribuerMatchs();
		this.nbTour++;
	}

	/**
	 * Retourne le nombre de tour qui ont étés joués dans le tournoi
	 * @return le nombre de tour
     */
	public int getNbTour() {
		return this.nbTour;
	}

	/**
	 * Appelée pour finir un tour et mettre à jour les scores
	 */
	public void finirTour() {
		Tour newTour = new Tour();
		//On vérifie le score des équipes pour chaque terrain
		for (int i = 0; i < this.terrains.size(); i++) {
			//Il faut vérifier qu'un match a bien eu lieu dur le terrain
			if (this.terrains.get(i).getMatch() != null) {
				newTour.addTerr(this.terrains.get(i));
			}

		}
		this.tour.add(newTour);//enregistre

		//On remet tous les joueurs en attente d'une paire
		for (int i = 0; i < this.anciensJoueurs.size(); i++) {
			(this.anciensJoueurs.get(i)).setDansPaire(false);
			(this.anciensJoueurs.get(i)).setJoue(false);
		}
		for (int i = 0; i < this.nouveauxJoueurs.size(); i++) {
			(this.nouveauxJoueurs.get(i)).setDansPaire(false);
			(this.nouveauxJoueurs.get(i)).setJoue(false);
		}
	}


	/**
	 * pour rentrer les scores d'un match lorsqu'il est crée de manière automatique
	 * @param numTerrain le terrain sur lequel s'est déroulé le match
	 * @param scoreP1    le score de la première paire
	 * @param scoreP2    le score de la seconde paire
	 */
	public void setScore(int numTerrain, int scoreP1, int scoreP2) {
		this.terrains.get(numTerrain).getMatch().modifierScores(scoreP1, scoreP2);

		//enregistrement des anciens partenaires
		this.terrains.get(numTerrain).getMatch().getPaire1().getJoueur1().ajouterAnciensPart(this.terrains.get(numTerrain).getMatch().getPaire1().getJoueur2());
		this.terrains.get(numTerrain).getMatch().getPaire1().getJoueur2().ajouterAnciensPart(this.terrains.get(numTerrain).getMatch().getPaire1().getJoueur1());
		this.terrains.get(numTerrain).getMatch().getPaire2().getJoueur1().ajouterAnciensPart(this.terrains.get(numTerrain).getMatch().getPaire2().getJoueur2());
		this.terrains.get(numTerrain).getMatch().getPaire2().getJoueur2().ajouterAnciensPart(this.terrains.get(numTerrain).getMatch().getPaire2().getJoueur1());
		this.terrains.get(numTerrain).getMatch().getPaire2().ajouterMatchJoue();
		this.terrains.get(numTerrain).getMatch().getPaire1().ajouterMatchJoue();
	}

	/**
	 * pour rentrer les scores d'un match lorsqu'il est ajouté de manière manuelle
	 * @param paire   la première paire (celle de droite dans la vue)
	 * @param paire2  la deuxième paire (celle de gauche dans la vue)
	 * @param scoreP1 le score de la première paire
	 * @param scoreP2 le score de la seconde paire
	 */
	public void ajouterMatch(Paire paire, Paire paire2, int scoreP1, int scoreP2) {
		this.paires.add(paire);
		this.paires.add(paire2);

		//comme les joueurs ont joués ils ne sont pas prioritaires
		paire.getJoueur1().setPrio(false);
		paire.getJoueur2().setPrio(false);
		paire2.getJoueur1().setPrio(false);
		paire2.getJoueur2().setPrio(false);
		Match match = new Match(paire, paire2);
		match.modifierScores(scoreP1, scoreP2);
	}

	/**
	 * Retourne s'il y a des joueurs sur le terrain numéro i
	 * @param i le terrain dont on veut savoir s'il est vide
	 * @return true si le terrain est associé a un match (si il y a des joueurs dedans), faux sinon
     */
	public boolean terrainVide(int i) {
		return this.terrains.get(i).getMatch() == null;
	}

	/**
	 * Renvoie le classement des anciens joueurs
	 * @return le classement des anciens joueurs adhérents
	 */
	public ArrayList getClassementAnciens() {
		this.trierAnciensJoueurs();
		return this.anciensJoueurs;
	}

	/**
	 * Renvoie le classement des nouveaux joueurs
	 * @return le classement des nouveaux adhérents joueurs
	 */
	public ArrayList getClassementNouveaux() {
		trierNouveauxJoueurs();
		return this.nouveauxJoueurs;
	}

	/**
	 * Redéfinition la méthode public toString
	 * @return L'état de toutes les paires à un moment donné
	 */
	@Override
	public String toString() {
		String res = "";
		for (int i = 0; i < this.paires.size(); i++) {
			res += this.paires.get(i).toString() + "\n";
		}
		return res;
	}

	/**
	 * Retourne le terrain auquel un joueur est associé
	 * @param j le  joueur
	 * @return le terrain ou null si le joueur ne joue pas a ce tour
	 */
	public Terrain getTerrainJoueur(Joueur j){
		Terrain ret = null;
		for (Terrain t : this.terrains)
			if(t.j1()==j||t.j2()==j||t.j3()==j||t.j4()==j)
				ret = t;
		return ret;
	}

	/**
	 * pour intervertir facilement deux joueurs qui jouent déjà
	 * On selectionne le premier Joueur et on souhaite le remplacer par le deuxième. On regarde si le deuxième joue ou pas
	 * @param jprec le joueur précedent
	 * @param jnouv le nouveau joueur
	 * @return true si l'opération est un succès, false sinon
	 */
	public Boolean changerJoueurs(Joueur jprec, Joueur jnouv) {
		if(jprec.equals(jnouv))
			return false;

		Paire pprec = this.getPaireContenant(jprec);
		Paire pnouv = this.getPaireContenant(jnouv);
		//si le nouveau joueur est J1 dans sa paire
			if(pnouv.getJoueur1()==jnouv){
				//Si l'ancien joueur est j1
				if(pprec.getJoueur1() == jprec){
					pprec.setJoueur1(jnouv);
					pnouv.setJoueur1(jprec);
				}
				else{
					pprec.setJoueur2(jnouv);
					pnouv.setJoueur1(jprec);
				}
			} else { //si  le nouveau joueur est j2
				if (pprec.getJoueur1() == jprec) {
					pprec.setJoueur1(jnouv);
					pnouv.setJoueur2(jprec);
				} else {
					pprec.setJoueur2(jnouv);
					pnouv.setJoueur2(jprec);
				}
			}
		return true;
	}

	/**
	 * renvoie la paire contenant un joueur donné, ou null si le joueur n'est pas dans une paire
	 * @param j le joueur
	 * @return la paire dans lequel est le joueur, ou null
	 */
	public Paire getPaireContenant(Joueur j){
		Paire ret = null;
		for (Paire paire : this.paires)
			if (j == paire.getJoueur1() || j == paire.getJoueur2())
				ret = paire;
		return ret;
	}

	/**
	 * pour obtenir un joueur à partir de son nom et de son prénom
	 * @param nomPrenom le nom plus un espace plus le prénom du joueur recherché
	 * @return le joueur recherché ou null si non trouvé
	 */
	public Joueur chercherJoueur(String nomPrenom) {
		Joueur ret = null;
		for (Joueur j : getAllJoueurs())
			if ((j.getNom() + " " + j.getPrenom()).equals(nomPrenom))
				ret = j;
		return ret;
	}

	/**
	 * Retour le nom du tournoi
	 * @return le nom du tournoi
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * redéfini le nom du tournoi
	 * @param nom le nouveau nom du tournoi
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Trouve un joueur avec un id et lui change ses informations
	 * @param id l'id du joueur recherché
	 * @param nom le nouveau nom du joueur
	 * @param prenom le nouveau prenom du joueur
	 * @param date la nouvelle date de naissance du joueur
	 * @param sexe le nouveau sexe du joueur
	 * @param nouveau la nouvelle ancienneté du joueur
     * @param niveau la nouvelle catégorie de niveau du joueur
     */
	public void modifierJoueur(int id, String nom, String prenom, LocalDate date, boolean sexe,
							   boolean nouveau, int niveau){
		Joueur j = this.getJoueur(id);
		j.modifierJoueur(nom, prenom, date, sexe, nouveau, niveau);
	}

	public boolean paireValide(int nuterrain){
		return !this.terrains.get(nuterrain).getMatch().getPaire1().getJoueur1().equals(this.terrains.get(nuterrain).getMatch().getPaire1().getJoueur2())
				& !this.terrains.get(nuterrain).getMatch().getPaire1().getJoueur1().equals(this.terrains.get(nuterrain).getMatch().getPaire2().getJoueur1())
				& !this.terrains.get(nuterrain).getMatch().getPaire1().getJoueur1().equals(this.terrains.get(nuterrain).getMatch().getPaire2().getJoueur2())
				& !this.terrains.get(nuterrain).getMatch().getPaire1().getJoueur2().equals(this.terrains.get(nuterrain).getMatch().getPaire2().getJoueur1())
				& !this.terrains.get(nuterrain).getMatch().getPaire1().getJoueur2().equals(this.terrains.get(nuterrain).getMatch().getPaire2().getJoueur2())
				& !this.terrains.get(nuterrain).getMatch().getPaire2().getJoueur1().equals(this.terrains.get(nuterrain).getMatch().getPaire2().getJoueur2());
	}

	/**
	 * remet a zero toutes les données de jeux (matches paires scores ect)
	 */
	public void resetAll() {
		//clear historique des paires
		this.paires.clear();
		//clear scores
		for (Joueur j : anciensJoueurs)
			j.setScore(0);

		for (Joueur j : nouveauxJoueurs)
			j.setScore(0);
	}

	/**
	 * remet a faux le getdans paire des joueurs. Permet de liberer les joueurs pour l'algo des paires
	 */
	public void viderGetDansPaire(){
		for (Joueur joueur : getAllJoueurs()) {
			joueur.setDansPaire(false);
		}
	}

	public ArrayList<Paire> getPaires() {
		return this.paires;
	}

	/**
	 * Retourne la liste des joueurs qu'on peut trouver dans le CSV
	 * @param fileDirectory le chemin pour accèder au CSV
	 * @return une ArrayList avec tous les joueurs
	 */
	public void importJoueurs(String fileDirectory) throws java.io.IOException, ImportExportException {
		ArrayList<Joueur> listeJoueurs = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader(fileDirectory));

        reader.readNext(); // Pour ne pas avoir les en-têtes

        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {

            String joueurCourant[];
            boolean sexe, nouveau;
            LocalDate date;
            int niveau;

            String nom, prenom;
            // Ordre d'une ligne du fichier CSV
            // [0] : prenom / [1] : nom / [2] : sexe (0 : femme / 1 : homme)
            // [3] ancienneté (0 : Ancien/ 1 : Nouveau)
            // [4] date de naissance
            // [5] : niveau  (0 : vide /1 : "Débutant" / 2 : "Intermédiaire" / 3 : "Confirmé")
            joueurCourant = nextLine; // le "-1" sert à récupérer une chaine même si elle est vide

            prenom = joueurCourant[0];
            nom = joueurCourant[1];

            //Lecture du sexe
            if (!joueurCourant[2].equals("Femme") && !joueurCourant[2].equals("Homme")) //Si la troisième valeur n'est ni Homme ni Femme
                throw new ImportExportException("Problème avec un genre");
            sexe = (joueurCourant[2].equals("Homme")); // Si la troisième valeur est Homme, sexe = true, sinon sexe = false

            //Lecture de l'ancienneté
            if (!joueurCourant[3].equals("Ancien") && !joueurCourant[3].equals("Nouveau")) //Si la quatrième valeur n'est ni Ancien ni Nouveau
                throw new ImportExportException("Problème avec l'ancienneté");
            nouveau = (joueurCourant[3].equals("Nouveau")); // Si la quatrième valeur est Nouveau, nouveau = true, sinon nouveau = false

			date = null; //Utile si l'âge est indéfini

            //Lecture de l'âge
			//Si le joueur possède plus de 4 attributs (et donc 5 ou 6), il a un possiblement un age de défini
            if (joueurCourant.length > 4 && !joueurCourant[4].isEmpty()) {

				//parsage en date
				try {
					date = parseDate(joueurCourant[4]);
				} catch (DateParsingExeption dateParsingExeption) {
					dateParsingExeption.printStackTrace();
				}
			}

            niveau = 0; // Utile si le niveau est indéfini

            //Lecture du niveau
			//Si le joueur possède plus de 5 attributs (et donc les 6), il a un niveau de défini
			if (joueurCourant.length > 5 && !joueurCourant[5].isEmpty()) {
				//Si la sixième valeur n'est ni débutant ni confirmé ni intermédiaire on lance une erreur
				if (!joueurCourant[5].equals("Débutant") && !joueurCourant[5].equals("Confirmé") && !joueurCourant[5].equals("Intermédiaire")) //Si la troisième valeur n'est ni Ancien ni Nouveau
                    throw new ImportExportException("Problème avec le niveau");
                if (joueurCourant[5].equals("Débutant"))
                    niveau = 1;
                else if (joueurCourant[5].equals("Intermédiaire"))
                    niveau = 2;
                else if (joueurCourant[5].equals("Confirmé"))
                    niveau = 3;
            }

			listeJoueurs.add(new Joueur(Joueur.nbJoueursCrees, nom, prenom, date, sexe, nouveau, niveau, true));
		}

        reader.close();

        for (Joueur j: listeJoueurs) {
            if (!this.getAnciensJoueurs().contains(j) && !this.getNouveauxJoueurs().contains(j)) {
                this.ajouterJoueur(j);
            }
        }


	}

	/**
	 * Découpe un joueur pour obtenir une chaine de caractère correspondant à une ligne CSV
	 * @param joueur le joueur à découper
	 * @return La chaine de caractère correspondant à une ligne CSV représentant le joueur découpé
	 */
	public static String decouperJoueur(Joueur joueur) {
		// Ordre d'une ligne du fichier CSV
		// Prénom,Nom,Sexe,Ancienneté,Âge,Niveau
		// [0] : prenom / [1] : nom / [2] : sexe (0 : "Femme" / 1 : "Homme")
		// [3] ancienneté (0 : "Ancien"/ 1 : "Nouveau")
		// [4] date
		// [5] : niveau  (0 : "" /1 : "Débutant" / 2 : "Intermédiaire" / 3 : "Confirmé")
		StringBuilder res = new StringBuilder();

		res.append(joueur.getPrenom() + "," + joueur.getNom() + ",");

		//Si joueur.getSexe() : Homme / sinon : femme
		res.append((joueur.getSexe()) ? "Homme," : "Femme,");

		//Si joueur.getNouveau() : Nouveau / sinon : Ancien
		res.append((joueur.getNouveau()) ? "Nouveau," : "Ancien,");

		if (joueur.getNaissance() != null)
			res.append(joueur.getNaissance());

		res.append(","); // S'il n'y a pas de date alors on ajoute seulement une virgule

		int niveau = joueur.getNiveau();

		if (niveau == 0)
			res.append("");
		else if (niveau == 1)
			res.append("Débutant");
		else if (niveau == 2)
			res.append("Intermédiaire");
		else
			res.append("Confirmé");

		return res.toString();
	}

	/**
	 * Exporte les classements dans un fichier CSV
	 * @param filepath le chemin du fichier où exporter le classement
	 */
	public void exportClassement(String filepath) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(filepath), ',');
		ArrayList<Joueur> all = getAllJoueurs();
		Collections.sort(all,new  ComparateurJoueurScore());
		String[] entries;
		entries = ("Nom,Prenom,Score,Ancienneté").split(",");
		writer.writeNext(entries);
		for (Joueur j : all){
			entries = (j.getNom()+","+j.getPrenom()+","+j.getScore()+","+j.getAnciennte()).split(",");
			writer.writeNext(entries);
		}
		writer.close();



	}

	/**
	 * Exporte les joueurs dans un fichier CSV
	 * @param filepath le chemin du fichier où enregistrer les joueurs
     */
	public void exportJoueurs(String filepath) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(filepath), ',');
		ArrayList<Joueur> all = getAllJoueurs();
		String[] entries = ("Prénom,Nom,Sexe,Ancienneté,Âge,Niveau").split(",");
		writer.writeNext(entries);
		for (Joueur j : all){
            String[] joueurDecoupe = decouperJoueur(j).split(",", -1);
			entries = (joueurDecoupe[0]+","+joueurDecoupe[1]+","+joueurDecoupe[2]+
                    ","+joueurDecoupe[3]+","+joueurDecoupe[4]+","+joueurDecoupe[5]).split(",");
			writer.writeNext(entries);
		}
		writer.close();
	}

	/**
	 * Parse un String représentant une date du format "dd/MM/yyyy"
	 * pour retourner la date correspondante de type LocalDate
	 * @param datestr
	 * @return la date
	 * @throws DateParsingExeption si l'âge n'est pas au bon format
     */
	public LocalDate parseDate(String datestr) throws DateParsingExeption {
		LocalDate date;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
		formatter = formatter.withLocale(Locale.FRANCE);
		try{
			date = LocalDate.parse(datestr, formatter);
		}catch(DateTimeParseException ex){
			throw new DateParsingExeption("Problème avec l'âge");

		}
		return date;
	}
}

