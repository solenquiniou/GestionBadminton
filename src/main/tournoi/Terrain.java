package main.tournoi;

/**
 * Terrain est la classe représentant un terrain du main.tournoi.
 * @author OUAKRIM Yanis, RICHARD Nicolas, ORHON Paul, RIALET Yohann, NIVELAIS Quentin
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 * @version 1.1
 */

public class Terrain{

  private int numero;
  private Match match;

  /**
   * Constructeur de la classe Terrain
   * @param numero le numero du terrain
   * @param match le match associé au terrain
   */
  public Terrain(int numero, Match match){
    this.numero = numero;
    this.match = match;
  }

  /**
   * Constructeur de la classe Terrain sans spécifier de Match : le match est déclaré null
   * @param numero le numero d'un terrain
   */
  public Terrain(int numero){
    this.numero = numero;
    this.match = null;
  }

  /**
   * Redéfinit le match d'un terrain
   * @param match le nouveau match d'un terrain
   */
  public void setMatch(Match match){
    this.match=match;
  }


  /**
   * Retourne le match d'un terrain
   * @return match le match d'un terrain
   */
  public Match getMatch(){
    return this.match;
  }

  /**
   * Redéfinition de la méthode toString() pour afficher le numéro du terrain, et "LIBRE" si aucun match n'a lieu
   * @return l'affichage du terrain
   */
  @Override
  public String toString(){
	  String s = this.numero + " ";
	  if(this.match==null){
		  s+="LIBRE";
	  }
	  return s;
  }

  /**
   * Retourne le premier joueur de la première paire
   * @return le premier joueur de la première paire
   */
  public Joueur j1(){
    return this.match.getPaire1().getJoueur1();
  }

  /**
   * Retourne le deuxième joueur de la première paire
   * @return le deuxième joueur de la première paire
   */
  public Joueur j2(){
    return this.match.getPaire1().getJoueur2();
  }

  /**
   * Retourne le premier joueur de la deuxième paire
   * @return le premier joueur de la deuxième paire
   */
  public Joueur j3(){
    return this.match.getPaire2().getJoueur1();
  }

  /**
   * Retourne le deuxième joueur de la deuxième paire
   * @return le deuxième joueur de la deuxième paire
   */
  public Joueur j4(){
    return this.match.getPaire2().getJoueur2();
  }
}
