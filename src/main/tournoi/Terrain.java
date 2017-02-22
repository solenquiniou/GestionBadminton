package main.tournoi;

/**
 * Terrain est la classe représentant un terrain du main.tournoi.
 * @author OUAKRIM Yanis, RICHARD Nicolas, ORHON Paul, RIALET Yohann, NIVELAIS Quentin
 * @author DERNONCOURT Cyril, DROUARD Antoine, LE BERT Lucas, MARTINEAU Lucas
 * @version 0.1
 */
public class Terrain{
  private int numero;
  private Match match;

  /**Constructeur de la classe Terrain
   *
   * @param numero le numero du terrain
   * @param match le match associé au terrain
   *
   */
  public Terrain(int numero, Match match){
    this.numero=numero;
    this.match=match;
  }

  /**Redéfinition du constructeur d'un terrain
   *
   * @param numero le numero d'un terrain
   *
   */
  public Terrain(int numero){
    this.numero=numero;
    this.match=null;
  }

  /**Redéfinit le numéro d'un terrain
   *
   * @param numero le nouveau numéro d'un terrain
   *
   */
  public void setNumero(int numero){
    this.numero=numero;
  }

  /**Redéfinit le match d'un terrain
   *
   * @param match le nouveau match d'un terrain
   *
   */
  public void setMatch(Match match){
    this.match=match;
  }


  /**Retourne le match d'un terrain
   *
   * @return match le match d'un terrain
   *
   */
  public Match getMatch(){
    return this.match;
  }
  
  @Override
  public String toString(){
	  String s = this.numero + " ";
	  if(this.match==null){
		  s+="LIBRE";
	  }
	  return s;
  }

  public Joueur j1(){
    return this.match.getPaire1().getJoueur1();
  }
  public Joueur j2(){
    return this.match.getPaire1().getJoueur2();
  }
  public Joueur j3(){
    return this.match.getPaire2().getJoueur1();
  }
  public Joueur j4(){
    return this.match.getPaire2().getJoueur2();
  }

}
