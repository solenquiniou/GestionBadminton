package main.tournoi;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Joueur est la classe représentant un joueur du tournoi
 * @author OUAKRIM Yanis, RICHARD Nicolas, ORHON Paul, RIALET Yohann, NIVELAIS Quentin
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 * @version 1.1
 */
public class Joueur {

	/**
	 * l'id unique du joueur
     */
	private int id;
	private String nom;
	private String prenom;

	/**
	 * la catégorie d'âge du joueur, elle peut avoir pour valeur :
	 * 0 : indéfini
	 * 1 : -18 jeune
	 * 2 : 18-35 senior
	 * 3 : 35+ veteran
	 */
	private int age;

	/**
	 * la date de naissance du joueur
     */
	private LocalDate dateN;

	/**
	 * le sexe du joueur représenté par un boolean
	 * true si c'est un homme et false si c'est une femme
     */
	private boolean sexe;

	/**
	 * indique si le joueur est nouveau
	 * true s'il est nouveau, false s'il est ancien
     */
	private boolean nouveau;
	private int score;

	/**
	 * indique si le joueur est en train de jouer à un moment m
     */
	private boolean joue;

	/**
	 * indique si le joueur est dans une paire à un moment m
     */
	private boolean dansPaire;

	/**
	 * représente l'indice de performance du joueur, il est calculé en fonction de l'age, du sexe et du niveau
     */
	private int perf;

	/**
	 * la catégorie de niveau du joueur, il peut avoir pour valeur :
	 * 0 : le niveau est indéfini
	 * 1 : Débutant
	 * 2 : Intermédiaire
	 * 3 : Confirmé
     */
	private int niveau;

	/**
	 * La liste des joueurs avec lesquels le joueur à joué
     */
	private ArrayList<Joueur> anciensPart;

	/**
	 * indique si le joueur est prioritaire
	 * true si le joueur est prioritaire, false s'il ne l'est pas
     */
	private boolean prio;
	private int nbMatchJoues;

	/**
	 * Indique si le joueur peut Jouer
	 * true si le joueur peut jouer, false sinon
	 */
	private boolean peutJouer;

	/**
	 * Savoir combien on a créé de joueurs, afin de donner un id unique
     */

	public static int nbJoueursCrees = 0;

	/**
	 * Constructeur de la classe Joueur
	 * @param id l'id du joueur
	 * @param nom le nom du joueur
	 * @param prenom le prénom du joueur
	 * @param date date de naissance
	 * @param sexe le sexe du joueur (false : femme / true : homme)
	 * @param nouveau (false : joueur ancien / true : joueur nouveau)
	 * @param niveau le niveau du joueur (0 : débutant / 1 : intermédiaire / 2 : confirmé)
	 * @param peutJouer si le joueur peut jouer (true : peux jouer / false : ne peut pas jouer)
	 */
	public Joueur(int id, String nom, String prenom, LocalDate date, boolean sexe,
			boolean nouveau, int niveau, boolean peutJouer){
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateN = date;
		if (this.dateN == null) {
			this.age = 0;
		}else
			this.age = calculAge();

		this.sexe = sexe;
		this.nouveau = nouveau;
		this.score = 0;
		this.joue = false;
		this.setDansPaire(false);
		this.niveau = niveau;

		//on calcule la performance en fonction de l'age, du sexe et du niveau
		this.perf = this.calculerPerf();

		this.prio = true;
		this.anciensPart = new ArrayList<>();
		this.nbMatchJoues = 0;
		this.peutJouer = peutJouer;

		nbJoueursCrees++;
	}

	/**
	 * Calcule la catégorie d' age a la quelle appartient le joueur a partir de sa date
	 * numero de la categorie
	 * 0 : indéfini
	 * 1 : -18 jeune
	 * 2 : 18-35 senior
	 * 3 : 35+ veteran
	 * @return la catégorie correspondant au joueur
     */
	public int calculAge(){
		LocalDate aujourdui = LocalDate.now();
		int age;

		//S'il n'y a pas de date de naissance, alors la catégorie est indéfinie
		if(this.dateN == null)
			age = 0;
		else {
			int ageEcart = Period.between(this.dateN, aujourdui).getYears();

			if (ageEcart <= 18)
				age = 1;
			else if (ageEcart <= 35)
				age = 2;
			else
				age = 3;
		}
		return age;
	}

	/**
	 * Pour calculer un indice de performance d'un joueur en fonction de son âge, son niveau et son sexe
	 * La performance est calculée en fonction du niveau, du sexe ainsi que de l'âge du joueur
	 * L'indice de performance minimal est 3, l'indice maximal est 7
	 * @return l'indice de performance du joueur
     */
	public int calculerPerf(){
		int perf = 0;

		if (niveau == 0) //Si le niveau n'est pas défini on considère que le joueur est de niveau intermédiaire
			perf += 2;
		else if (niveau == 1 || niveau == 2 || niveau == 3) // sinon on lui ajoute son niveau en indice
			perf += niveau;

		if (sexe) //Si le joueur est un homme on augmente son indice
			perf += 1;

		if (age == 0) //Si l'âge n'est pas défini on considère qu'il a entre 18 et 35 ans
						// (on trouve sur internet une moyenne d'environ 26 ans pour les joueurs de badminton)
			perf += 2;
		else if (age == 1) //Si le joueur a moins de 18 ans, on lui ajoute le moins de point à son indice
			perf += 1;
		else if (age == 2) //Si le joueur a entre 18 et 35 ans on lui ajoute le plus de points à son indice
			perf += 3;
		else if (age == 3) //Si le joueur a plus de 35 ans on lui ajoute un nombre moyen de point à son indice
			perf += 2;

		return perf;
	}

	/**
	 * Remet a zero le nombre de joueur
	 * A utiliser lorsque l'on cree un nouveau tournoi
	 */
	public static void resetNbJoueur() {
		Joueur.nbJoueursCrees = 0;
	}

	/**
	 * Incrémente de 1 le nombre de matchs joué par le joueur
	 */
	public void ajouterMatchJoue() {
		this.nbMatchJoues++;
	}

	/**
	 * Ajoute un joueur dans les anciens partenaires du joueur,
	 * c'est à dire les joueurs avec lesquels le joueur a déjà joué
	 * @param j le joueur à ajouter dans les partenaires
	 */
	public void ajouterAnciensPart(Joueur j){
		this.anciensPart.add(j);
	}

	/**
	 * Redéfinition de la méthode equals() pour savoir si deux joueurs sont les mêmes
	 * on considère que les joueurs sont les mêmes s'il ont le même id, le même nom et le même prénom
	 * @param o l'objet à comparer
	 * @return false s'ils ne sont pas égaux true s'ils sont égaux
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof Joueur){
			return (this.id==((Joueur)o).id
					&& this.nom.equals(((Joueur)o).nom)
					&& this.prenom.equals(((Joueur)o).prenom));
		}
		else
			return false;
	}

	/**
	 * Permet de modifier toutes les informations d'un joueur avec une seule méthode
	 * @param nom2 le nouveau nom du joueur
	 * @param prenom2 le nouveau prénom du joueur
	 * @param date2 la nouvelle date du joueur
	 * @param sexe2 le nouveau sexe du joueur
	 * @param nouveau2 la nouvelle ancienneté du joueur
     * @param niveau2 le nouveau niveau du joueur
     */
	public void modifierJoueur(String nom2, String prenom2, LocalDate date2, boolean sexe2, boolean nouveau2,
			int niveau2) {
		this.setNom(nom2);
		this.setPrenom(prenom2);
		this.setDateN(date2);
		this.setSexe(sexe2);
		this.setNouveau(nouveau2);
		this.setNiveau(niveau2);
	}

	/**
	 * Retourne si le joueur peut jouer ou non
	 * @return false si le joueur ne peut pas jouer, true si le joueur peut jouer
	 */
	public boolean peutJouer() {
		return peutJouer;
	}

	/**
	 * Retourne le statut du joueur, c'est à dire le joueur peut jouer ou non en String, utilisé pour l'affichage
	 * @return "Non" si le joueur ne peut pas jouer, "Oui" si le joueur peut jouer
	 */
	public String statut() {
		return (peutJouer) ? "Oui" : "Non";
	}

	/**
	 * Redéfinit l'attribut "nom"
	 * @param nom Le nouveau nom du joueur
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Redéfinit l'attribut "prenom"
	 * @param prenom Le nouveau prenom du joueur
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Redéfinit l'attribut "sexe"
	 * @param sexe Le nouveau sexe du joueur
	 */
	public void setSexe(boolean sexe) {
		this.sexe = sexe;
	}

	/**
	 * Redéfinit l'attribut "nouveau"
	 * @param nouveau false si le joueur est ancien, true si le joueur est nouveau
	 */
	public void setNouveau(boolean nouveau) {
		this.nouveau = nouveau;
	}

	/**
	 * Redéfinit l'attribut "niveau" qui représente la catégorie de niveau du joueur, et qui peut avoir pour valeur :
	 * 0 : le niveau est indéfini
	 * 1 : Débutant
	 * 2 : Intermédiaire
	 * 3 : Confirmé
	 * @param niveau le nouveau niveau du joueur
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	/**
	 * Redéfinit l'attribut "peutJouer"
	 * @param peutJouer false si le joueur ne doit plus jouer, true si le joueur doit jouer
	 */
	public void setPeutJouer(boolean peutJouer) {
		this.peutJouer = peutJouer;
	}

	/**
	 * Redéfinit l'attribut "joue"
	 * @param bool false si le joueur ne joue pas, true si le joueur joue
	 */
	public void setJoue(boolean bool){
		this.joue = bool;
		if(bool){
			this.nbMatchJoues++;
		}
	}

	/**
	 * Redéfinit l'attribut "score"
	 * @param score le score nouveau score du joueur
	 */
	public void setScore(int score){
		this.score = score;
	}

	/**
	 * Redéfinition de l'attribut "dansPaire"
	 * @param dansPaire le fait que le joueur soit dans une paire ou non
	 */
	public void setDansPaire(boolean dansPaire) {
		this.dansPaire = dansPaire;
	}

	/**
	 * redéfini la date de naissance et actualise l'age en conséquence
	 * Utile s'il y a eu une erreur de saisie par exemple
	 * @param dateN la nouvelle date de naissance du joueur
	 */
	public void setDateN(LocalDate dateN) {
		this.dateN = dateN;
		this.age = calculAge();
	}

	/**
	 * Change la priorité d'un joueur
	 * @param pr true pour le rendre prioritaire, false pour lui enlever la priorité
	 */
	public void setPrio(boolean pr){
		this.prio = pr;
	}

	/**
	 * Retourne l'id d'un joueur, cet identifiant est unique
	 * @return l'id du joueur
	 */
	public int getId(){
		return this.id;
	}

	/**
	 * Retourne si le joueur est dans une paire
	 * @return true si le joueur est dans paire, false si le joueur n'est pas dans une paire
	 */
	public boolean getDansPaire() {
		return this.dansPaire;
	}

	/**
	 * Retourne le nombre de match joués par le joueur
	 * @return le nombre de matchs que le joueur à joué
	 */
	public int getNbMatchJoues() {
		return nbMatchJoues;
	}

	/**
	 * Retourne le nom d'un joueur
	 * @return le nom du joueur
	 */
	public String getNom(){
		return this.nom;
	}

	/**
	 * Retourne le prénom d'un joueur
	 * @return le prénom du joueur
	 */
	public String getPrenom(){
		return this.prenom;
	}

	/**
	 * Retourne la date de naissance du joueur de type LocalDate, ou null si la date de naissance du joueur n'est pas définie
	 * @return la date de naissance du joueur ou null si la date de naissance du joueur n'est pas définie
	 */
	public LocalDate getDateN() {
		return this.dateN;
	}

	/**
	 * Retourne la date de naissance du joueur ou null si le joueur n'a pas de date de naissance de spécifiée
	 * @return une représentation en String de la date de naissance au format "dd/MM/yyyy"
	 */
	public String getNaissance(){

		String res = null;

		if (this.dateN != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			res =  this.dateN.format(formatter);
		}

		return res;
	}

	/**
	 * Retourne le sexe d'un joueur
	 * @return le sexe du joueur
	 */
	public boolean getSexe(){
		return this.sexe;
	}

	/**
	 * Retourne le score d'un joueur
	 * @return le score du joueur
	 */
	public int getScore(){
		return this.score;
	}

	/**
	 * Retourne l'ancienneté d'un joueur
	 * @return false si le joueur est ancien, true si le joueur est nouveau
	 */
	public boolean getNouveau(){
		return this.nouveau;
	}

	/**
	 * Retourne l'ancienneté d'un joueur en string lisible pour l'affichage
	 * @return false si le joueur est ancien, true si le joueur est nouveau
	 */
	public String getAnciennte(){
		return (this.nouveau) ? "Nouveau" : "Ancien";
	}

	/**
	 * Retourne si le joueur joue ou s'il ne joue pas
	 * @return false si le joueur ne joue pas, true si le joueur joue
	 */
	public boolean getJoue(){
		return this.joue;
	}

	/**
	 * Retourne la priorité du joueur
	 * @return true si le joueur est prioritaire, false s'il ne l'est pas
	 */
	public boolean getPrio(){
		return this.prio;
	}

	/**
	 * Retourne le niveau d'un joueur
	 * @return niveau le niveau du joueur
	 */
	public int getNiveau(){
		return this.niveau;
	}

	/**
	 * Retourne l'indice de performance du joueur, compris entre 3 et 7
	 * @return l'indice de performance du joueur
	 */
	public int getPerf(){
		return this.perf;
	}

	/**
	 * Redéfinition de la méthode toString()
	 * On obtient le nom et le prénom du joueur dans ce format : "<prenom>  <nom>"
	 * @return l'affichage d'un joueur
	 */
	@Override
	public String toString(){
		return this.prenom + "  " + this.nom;
	}

	/**
	 * Retourne tous les anciens partenaires d'un joueur, c'est
	 * à dire tous les joueurs avec lesquels le joueur a déjà joué
	 * @return la liste de tous les anciens partenaires d'un joueur
	 */
	public ArrayList<Joueur> getAnciensPart(){
		return this.anciensPart;
	}

	/**
	 * Retourne si le joueur a déjà joué avec un autre (en paramètre)
	 * @param j1 le joueur à tester
	 * @return false si les joueurs n'ont jamais joué ensemble, true s'ils ont déjà joué ensemble
	 */
	public boolean aJoueAvec(Joueur j1){
		return this.getAnciensPart().contains(j1);
	}

	/**
	 * Retourne si le joueur est compatible avec un autre (en paramètre),
	 * c'est-à-dire si le joueur n'a jamais joué avec le joueur passé en paramètre
	 * @param joueur le joueur à tester
	 * @return false si les joueurs ne sont pas compatibles, true si les joueurs sont compatibles
	 */
	public boolean estCompatibleAvec(Joueur joueur){
		//On vérifie si les joueurs ont déjà joué ensemble
		return !this.aJoueAvec(joueur);
	}

}
