package main.tournoi;

/**
 * Match est la classe représentant un match du tournoi
 * @author OUAKRIM Yanis, RICHARD Nicolas, ORHON Paul, RIALET Yohann, NIVELAIS Quentin
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 * @version 1.1
 */
public class Match {

	private Paire paire1;
	private Paire paire2;
	private Paire vainqueur;

	//les scores effectifs des joueurs
	private int score1;
	private int score2;

	private boolean enCours;

	/** Constructeur de la classe Match
	 * @param paire1 la première paire du match
	 * @param paire2 la deuxième paire du match
	 */
	public Match(Paire paire1, Paire paire2){
		this.paire1 = paire1;
		this.paire2 = paire2;
		this.vainqueur = null;
		this.enCours = true;
	}

	/**
	 * Retourne la première paire qui joue dans le match
	 * @return la première paire du match
	 */
	public Paire getPaire1(){
		return this.paire1;
	}

	/**
	 * Retourne la deuxième paire qui joue dans le match
	* @return la deuxième paire du match
	*/
	public Paire getPaire2(){
		return this.paire2;
	}

	/**
	 * Redéfinit l'attribut "paire1"
	 * @param p1 la première paire du match
	 */
	public void setPaire1(Paire p1){
		this.paire1 = p1;
	}

	/**
	 * Redéfinit l'attribut "paire2
	 * @param p2 la deuxième paire du match
	 */
	public void setPaire2(Paire p2){
		this.paire2 = p2;
	}

	/**
	 * Redéfinit l'attribut "vainqueur"
	 * @param leGagnant le gagnant du match
	 */
	public void setVainqueur(Paire leGagnant){
		this.vainqueur = leGagnant;
	}

	/**
	 * Retourne le vainqueur d'un match
	 * @return le vainqueur d'un match
	 */
	public Paire getVainqueur(){
		return this.vainqueur;
	}

	/**
	 * Retourne le score de la première paire
	 * @return le score de la première paire
	 */
	public int getScore1(){
		return this.score1;
	}

	/**
	 * Retourne le score de la deuxième paire
	 * @return le score de la deuxième paire
	 */
	public int getScore2(){
		return this.score2;
	}

	/**
	 * Redéfinit l'attribut "enCours", c'est à dire si le match est en cours ou non
	 * @param b true si le match est en cours, false sinon
	 */
	public void setEnCours(boolean b) {
		enCours = b;
	}

	/**
	 * Retourne si le match est en cours ou non
	 * @return true si le match est en cours, false sinon
	 */
	public boolean getEncours() { return enCours; }

	/**
	 * Détermine le vainqueur d'un match, et change l'attribut
	 * vainqueur du Match pour y mettre la Paire qui a gagné le match
	 * @param s1 le score de la paire 1
	 * @param s2 le score de la paire 2
	 */
	public void determinerVainqueur(int s1,int s2){
		this.enCours = false;
		this.score1 = s1;
		this.score2 = s2;
		if(s1>s2){
			this.vainqueur = paire1;
		}else if (s1<s2){
			this.vainqueur = paire2;
		}
		else{
			//égalité
			this.vainqueur = null;
		}
	}

	/**
	 * Modifie les scores de la paire gagnante et de la perdante
	 * le gagnant gagne la différence entre lui et le perdant
	 * @param s1 score de la paire 1
	 * @param s2 score de la paire 2
	 */
	public void modifierScores(int s1,int s2){
		this.determinerVainqueur(s1,s2);
		// On regarde le vainqueur et on modifie en conséquence
		if (this.vainqueur==null){ // s'il y a égalité
			paire1.setScore(0);
			paire2.setScore(0);

		}
		else{
			if (this.vainqueur.equals(this.paire1)){
				int diff = score1-score2;
				paire1.setScore(diff);
				paire2.setScore(0-diff);
			}
			else {
				int diff = score2-score1;
				paire1.setScore(0-diff);
				paire2.setScore(diff);
			}
		}
	}

	/**
	 * Redéfinition de la méthode toString() pour afficher les deux paires s'affrontant en affichant :
	 * "Joueur1 Joueur2 jouent contre Joueur3 Joueur4" où joueur1 et joueur2 sont dans la première paire,
	 * et joueur3 et joueur4 sont dans la deuxième paire
	 * @return l'affichage d'un match
	 */
	@Override
	public String toString(){
		return this.paire1.toString() + " jouent contre "+ this.paire2.toString();
	}

	/**
	 * Renvoie la valeur de priorité du match, qui est calculée en fonction
	 * de la valeur de priorité des deux paires qui jouent ce match
	 * @return la valeur de prioritée du match
     */
	public int prio(){
		return (paire1.prio() + paire2.prio());
	}

	public boolean isEnCours() {
		return enCours;
	}

}
