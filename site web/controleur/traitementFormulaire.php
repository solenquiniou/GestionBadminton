<?php

require_once __DIR__."/../modele/Modele.php";

  	if(!empty($_POST))
  	{
    	if(empty($_POST['prenom']) || empty($_POST['nom']) || empty($_POST['sexe']) || empty($_POST['anciennete']) ||
            empty($_POST['date']) || empty($_POST['niveau'])) {
            header('Location : ../index.html');
    	} else {
            try {
                $modele = new Modele();

                $prenom = $_POST['prenom'];
                if(strlen($prenom)>255) {
                    header('Location : ../index.html');
                }

                $nom = $_POST['nom'];
                if(strlen($nom)>255) {
                    header('Location : ../index.html');
                }
                $sexe = ($_POST['sexe'] == "Homme" ? "homme" : "femme");

                $anciennete = ($_POST['anciennete'] == "ancAdherent" ? "ancien" : "nouveau");

                $dateNonFormate = $_POST['date'];
                $dateNonFormateSplit = explode("/", $dateNonFormate);
                $date = $dateNonFormateSplit[2] . "-" . $dateNonFormateSplit[1] . "-" . $dateNonFormateSplit[0];

                $niveau;
                if (in_array($_POST['niveau'], ["indefini", "debutant", "intermediaire", "confirme"])) {
                    $niveau = $_POST['niveau'];
                } else {
                    header('Location : ../index.html');
                }

                $modele->addJoueur($prenom, $nom, $sexe, $anciennete, $date, $niveau);
                header('Location : ../index.html');


            } catch (Exception $e) {
                echo("problème");
                exit;
            }
        }
	} else {
        header('Location: index.html');
    }
?>