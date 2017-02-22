package main.tournoi;

/**
 * Paire est la classe représentant une paire de joueurs.
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 * @version 1.1
 */
public class Paire {

	private Joueur joueur1;
	private Joueur joueur2;
	private int score;
	private boolean dansMatch;

	/**
	 * Constructeur de la classe Paire
	 * @param j1 le premier joueur de la paire
	 * @param j2 le deuxième joueur de la paire
	 */
	public Paire(Joueur j1, Joueur j2){
		this.joueur1 = j1;
		this.joueur2 = j2;

		//On déclare que les deux joueurs ne jouent pas
		this.joueursJouent(false);
		this.dansMatch = false;
	}

	/**
	 * Retourne le premier joueur de la paire
	 * @return joueur1 le premier joueur de la paire
	 */
	public Joueur getJoueur1(){
		return this.joueur1;
	}

	/**
	 * Retourne le deuxième joueur de la paire
	 * @return joueur2 le deuxième joueur de la paire
	 */
	public Joueur getJoueur2(){
		return this.joueur2;
	}

	/**
	 * Redéfinition de l'attribut "joueur1"
	 * @param j1 le premier joueur de la paire
	 */
	public void setJoueur1(Joueur j1){
		this.joueur1 = j1;
	}

	/**
	 * Redéfinition de l'attribut "joueur2"
	 * @param j2 le deuxième joueur de la paire
	 */
	public void setJoueur2(Joueur j2){
		this.joueur2 = j2;
	}

	/**
	 * Redéfinition de la méthode toString() pour afficher :
	 * "<Joueur1> et <Joueur2>"
	 * @return l'affichage de la paire
	 */
	@Override
	public String toString(){
		return this.joueur1.toString() + " et " + this.joueur2.toString();
	}

	/**
	 * Retourne si la paire est dans un match
	 * @return true si la paire est dans un match, false sinon
     */
	public boolean isDansMatch() {
		return dansMatch;
	}

	/**
	 * Redéfini si la paire est dans un match
	 * @param dansMatch qui vaut true si la paire est dans un match, false si elle ne l'est pas
     */
	public void setDansMatch(boolean dansMatch) {
		this.dansMatch = dansMatch;
	}

	/**
	 * modifie si les joueurs de la paire jouent
	 * @param j vrai s'ils jouent faux sinon
     */
	public void joueursJouent(boolean j){
		this.joueur1.setJoue(j);
		this.joueur2.setJoue(j);

	}

	/**
	 * Incrémente le nombre de match joué des deux joueurs de la paires
     */
	public void ajouterMatchJoue(){
		this.joueur1.ajouterMatchJoue();
		this.joueur2.ajouterMatchJoue();
	}

	/**
	 * Retourne le score de la paire
	 * @return score le score de la paire
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Redéfinition de l'attribut "score"
	 * et mise a jour des scores des jouerus de la paire
	 * @param score le score de la paire
	 */
	public void setScore(int score) {
		this.score = score;
		//on incrémente le score a partir du score que le joueur avais deja a la base
		this.joueur1.setScore(score+this.joueur1.getScore());
		this.joueur2.setScore(score+this.joueur2.getScore());
	}


	/**
	 * <p>
	 * Calcule la priorité de la paire en fonction de la priorité
	 * des deux joueurs (+1 par joueur prioritaire)
	 * </p>
	 * Cette priorité est donc comprise entre 0 et 2
	 * @return un int qui estime la prioritée de la paire
     */
	public int prio(){
		int prio = 0;
		if ( this.joueur1.getPrio()) {prio++;}
		if ( this.joueur2.getPrio()) {prio++;}


		return prio;
	}
	
	/**
	 * Redéfinition de la méthode equals() pour savoir si deux paires sont égales.
	 * Deux paires sont considérées comme égales si les deux joueurs des deux paires sont égaux
	 * @param o l'objet à comparer
	 * @return true si les deux paires sont égales false sinon
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof Paire){
			return (this.joueur1.equals(((Paire)o).joueur1)
					&& this.joueur2.equals(((Paire)o).joueur2));
		}
		else {
			return false;
		}
	}

	/**
	 * Retourne si la paire est compatible avec un autre (en paramètre)
	 * On considère que deux paires sont compatibles si un des joueurs n'a pas
	 * joué avec aucun des deux autres joueurs de la paire antagoniste
	 * @param paire2 la paire à tester
	 * @return booléen false si les paires ne sont pas compatibles, true si elles le sont
	 */
	public boolean estCompatible(Paire paire2){
		//On vérifie si les joueurs ont déjà joué ensemble
		return !this.joueur1.aJoueAvec(paire2.joueur1)&&
				!this.joueur1.aJoueAvec(paire2.joueur2)&&
				!this.joueur2.aJoueAvec(paire2.joueur1)&&
				!this.joueur2.aJoueAvec(paire2.joueur2);
	}
}
