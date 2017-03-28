<?php
require_once __DIR__."/../modele/Modele.php";
require_once __DIR__."/../vue/formulaire.php";

class traitementFormulaire {

    private $vueFormulaire;
	private $modele;

    function __construct() {
        $this->vueFormulaire = new VueFormulaire();

        try {
            $this->modele = new Modele();
        } catch (ConnexionException $e) {
            $this->vue->erreur("Échec de la connexion : " . $e->getMessage());
            exit;
        }
    }

    public function verifierForm($prenom,$nom,$sexe,$anciennete,$date,$niveau) {
        try {

            $sexe = ($sexe == "Homme" ? "homme" : "femme");
            $anciennete = ($anciennete == "ancAdherent" ? "ancien" : "nouveau");

            $modele = new Modele();
            if(strlen($prenom)>255) {
                $this->vueFormulaire->afficher(true);
                return;
            }
            
            if(strlen($nom)>255) {
                $this->vueFormulaire->afficher(true);
                return;
            }
            
            $dateNonFormate = $_POST['date'];
            if(!preg_match(
                '~^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$~',
                $dateNonFormate)) {
                $this->vueFormulaire->afficher(true);
            return;
            }
            
            $dateNonFormateSplit = explode("/", $dateNonFormate);
            $date = $dateNonFormateSplit[2] . "-" . $dateNonFormateSplit[1] . "-" . $dateNonFormateSplit[0];

            if (!in_array( $niveau , ["indefini", "debutant", "intermediaire", "confirme"])) {
                $this->vueFormulaire->afficher(true);
                return;
            }
            
            $modele->addJoueur($prenom, $nom, $sexe, $anciennete, $date, $niveau);
            $this->vueFormulaire->afficher(false);
            return;

        } catch (Exception $e) {
                $err = new VueErr();
                $err->afficher("accesBase");
        }
    }

    public function accueil() {
        $this->vueFormulaire->afficher(false);
    }
}

?>