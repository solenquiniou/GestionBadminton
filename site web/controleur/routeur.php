<?php
require_once __DIR__."/traitementConnexion.php";
require_once __DIR__."/traitementFormulaire.php";
require_once __DIR__."/../model/Model.php";
require_once __DIR__."/../vue/erreur.php";
class Routeur {
	private $db;

public function __construct() {
	$this->db = new Modele(); 
}
  // Traite une requête entrante
  public function start() {
	if(isset($_POST["logout"])){
    	 	 $_SESSION['utilisateur']->reset();
     		 header('Location: ../vue/login.html');
  	}else{ 
		if(!empty($_POST['pseudo'])&& !empty($_POST['pass'])){
           		 try {
			        $modele = new Modele();
				$pseudo = $_POST['pseudo'];
				if(strlen($pseudo)>255) {
				    header('Location: ../vue/login.html');
				}
				$pass = $_POST['pass'];
				if(strlen($pass)>255) {
				    header('Location: ../vue/login.html');
				}
				$hashedPass = $modele->getPass($pseudo);

				if (crypt($pass, $hashedPass) == $hashedPass) {
				    $_SESSION["utilisateur"] = $pseudo;
				    header('Location: ../vue/gestionJoueurs.html', true);
				}
                
            		} catch (Exception $e) {
				$vueErr = new VueErr();
				$vueErr->afficher("accesBase");

            		}
        	}
		else{
			if(!empty($_POST['prenom']) && !empty($_POST['nom']) && !empty($_POST['sexe']) && !empty($_POST['anciennete']) 
            		&& !empty($_POST['date'])    && !empty($_POST['niveau'])){
				$verifForm = new testFormulaire();
 				$sexe = ($_POST['sexe'] == "Homme" ? "homme" : "femme");
				$anciennete = ($_POST['anciennete'] == "ancAdherent" ? "ancien" : "nouveau");

				$verifForm->verifierForm(($_POST['prenom']),($_POST['nom']),$sexe,$anciennete,($_POST['date']),($_POST['niveau']));
				
			}

		}
	}




     }





}

?>