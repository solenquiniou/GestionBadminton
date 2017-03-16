<?php
require_once __DIR__.'/../config.php';

/**
* Classe qui gère les accès à la base de données
* Il faut renseigner dans le fichier config le nom de la base, le nom de compte et le mot de passe
*/
class Modele{
    private $connexion;

// Constructeur de la classe

    public function __construct(){
        try{
            $chaine="mysql:host=localhost;dbname=".Config::$DB_NAME;  //Va chercher les noms dans le fichier config.php
            $this->connexion = new PDO($chaine,Config::$DB_USER,Config::$DB_PASSWD);
            $this->connexion->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
        }
        catch(PDOException $e){
            throw new ConnexionException("problème de connection à la base de données, essayez de modifier le fichier config.php à la racine du dossier mastermind puis rafraichissez la page");
        }
    }

    // méthode qui permet de se deconnecter de la base
    public function deconnexion(){
        $this->connexion=null;
    }

    /** Retourne tous les joueurs présents dans la base de données
     * @return array Tous les joueurs
     * @throws TableAccesException si problème d'accès à la base
     */
    public function getJoueurs(){
        try{
            $statement=$this->connexion->query("SELECT * FROM joueurs");
            return($statement->fetchAll(PDO::FETCH_ASSOC));
        } catch(PDOException $e){
            $this->deconnexion();
            throw new TableAccesException("problème avec la table parties : Veuillez vérifier qu'elle existe bien");
        }
    }

    /** Ajoute un joueur à la base de données
     * @param $pseudo Le pseudo du joueur jouant
     * @param $partieGagnee 0 si la partie est perdu, 1 si elle est gagnée
     * @param $nombreCoups Le nombre de coup si la partie est gagnée, 10 si elle est perdue
     * @return bool Si l'ajout a bien été effectué
     * @throws TableAccesException si problèmme d'accès à la base
     */
    public function addJoueur($prenom, $nom, $sexe, $anciennete, $date, $niveau) {
        try{
            $statement = $this->connexion->prepare("INSERT INTO joueurs VALUES (NULL,?,?,?,?,?,?);");
            $statement->bindParam(1, $prenom);
            $statement->bindParam(2, $nom);
            $statement->bindParam(3, $sexe);
            $statement->bindParam(4, $anciennete);
            $statement->bindParam(5, $date);
            $statement->bindParam(6, $niveau);

            $result = $statement->execute();
            return $result;

        } catch(PDOException $e){
            $this->deconnexion();
            throw new TableAccesException("problème avec la table parties : Veuillez vérifier qu'elle existe bien");
        }

    }

    public function getPass($pseudo) {
        try{

            $statement = $this->connexion->prepare("SELECT password from " . Config::$DB_tableAdministrateurs . " where pseudo=?;");
            $statement->bindParam(1, $pseudo);
            $statement->execute();
            $result=$statement->fetch(PDO::FETCH_ASSOC);

            return $result["motDePasse"];
        }
        catch(PDOException $e){
            $this->deconnexion();
            throw new TableAccesException("problème avec la table " . Config::$DB_tableAdministrateurs);
        }
    }
}

?>
