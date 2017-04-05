<?php
require_once __DIR__."/traitementAdmin.php";
require_once __DIR__."/traitementConnexion.php";
require_once __DIR__."/traitementFormulaire.php";
require_once __DIR__."/../modele/Modele.php";

class Routeur {

	private $db;
	private $traitementConnexion;
	private $traitementFormulaire;
	private $traitementAdmin;

	public function __construct() {
		$this->db = new Modele();
		$this->traitementConnexion = new traitementConnexion();
		$this->traitementFormulaire = new traitementFormulaire();
		$this->traitementAdmin = new TraitementAdmin();
	}

	// Traite une requête entrante
	public function start() {

		if (isset($_POST['soumettreFormulaire'])) {
			if(isset($_POST['prenom'])
				 && isset($_POST['nom'])
				 && isset($_POST['sexe'])
				 && isset($_POST['anciennete'])
				 && isset($_POST['date'])
				 && isset($_POST['niveau'])) {

				$fromAdmin = isset($_POST['admin']);
				$this->traitementFormulaire->verifierForm($fromAdmin,
															$_POST['prenom'],
															$_POST['nom'],
															$_POST['sexe'],
															$_POST['anciennete'],
															$_POST['date'],
															$_POST['niveau']);
			} else {
				$this->traitementFormulaire->accueil();
			}
		}

		else if(isset($_POST['soumettreLogin'])) {
			if(isset($_POST['pseudo']) && isset($_POST['pass'])){
				$this->traitementConnexion->checkPass($_POST['pseudo'], $_POST['pass']);
			} else {
				$this->traitementConnexion->accueil();
			}
		}

		else if (isset($_POST['method'])) {
			$method = $_POST['method'];
			if ($method == "del") {
				if (isset($_POST['prenomJ']) && isset($_POST['nomJ'])) {
					$this->traitementAdmin->supprimerJoueur($_POST['prenomJ'], $_POST['nomJ']);
				} else {
					$this->traitementAdmin->afficher();
				}
			} else if ($method == "csv") {
				$this->traitementAdmin->telechargerCsv();
			}
		}
		else {
			$this->traitementFormulaire->accueil();
		}
	}
}

?>
