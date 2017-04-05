<?php
require_once __DIR__."/../modele/Modele.php";
require_once __DIR__."/../vue/adminPage.php";

class TraitementAdmin {
    private $vueAdmin;
    private $modele;

    function __construct() {
        $this->vueAdmin = new vueAdmin();
        try {
            $this->modele = new Modele();

        } catch (ConnexionException $e) {
            $this->vue->erreur("Échec de la connexion : " . $e->getMessage());
            exit;
        }
    }

    function supprimerJoueur($nom, $prenom) {
        $this->modele->supprimerJoueur($nom,$prenom);
        $this->vueAdmin->afficher($this->modele->getJoueurs());
    }
    
    function modifierJoueur() {
        
    }

    function telechargerCsv() {
        $joueurs = $this->modele->getJoueurs();

        
        //Fichier temporaire dans lequel on va mettre tous les joueurs
        $f = fopen("php://output", "w");
        fputcsv($f, ["Prénom", "Nom", "Sexe", "Ancienneté", "Âge", "Niveau"]);
        foreach ($joueurs as $line) {
            $line = array_slice($line, 1);
            $line = array_values($line);
            //homme -> Homme et femme -> Femme
            $line[2] = ucfirst($line[2]);

            //nouveau -> Nouveau et ancien -> Ancien
            $line[3] = ucfirst($line[3]);

            $dateNonFormateSplit = explode("-", $line[4]);
            $line[4] = $dateNonFormateSplit[2] . "/" . $dateNonFormateSplit[1] . "/" . $dateNonFormateSplit[0];

            if ($line[5] == "indefini") {
                $line[5] = "";
            } else if ($line[5] == "debutant") {
                $line[5] = "Débutant";
            } else if ($line[5] == "intermediaire") {
                $line[5] = "Intermédiaire";
            } else if ($line[5] == "confirme") {
                $line[5] = "Confirmé";
            }

            //ajout de la ligne dans le CSV
            fputcsv($f, $line);
        }

        // tell the browser it's going to be a csv file
        header('Content-Type: application/csv');
        // tell the browser we want to save it instead of displaying it
        header('Content-Disposition: attachment; filename="joueurs.csv";');
        // make php send the generated csv lines to the browser
        fpassthru($f);
    }

    function afficher() {
        $this->vueAdmin->afficher($this->modele->getJoueurs());
    }
}
?>
