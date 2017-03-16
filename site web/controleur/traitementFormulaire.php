<?php
require_once __DIR__."/../modele/Modele.php";

  	if(!empty($_POST))
  	{
    	if(empty($_POST['prenom']) 
            || empty($_POST['nom']) 
            || empty($_POST['sexe']) 
            || empty($_POST['anciennete']) 
            || empty($_POST['date'])
            || empty($_POST['niveau'])){

            header('Location: ../index.html');
    	} else {
            try {
                $modele = new Modele();

                $prenom = $_POST['prenom'];
                if(strlen($prenom)>255) {
                    header('Location: ../index.html');
                }

                $nom = $_POST['nom'];
                if(strlen($nom)>255) {
                    header('Location: ../index.html');
                }
                $sexe = ($_POST['sexe'] == "Homme" ? "homme" : "femme");

                $anciennete = ($_POST['anciennete'] == "ancAdherent" ? "ancien" : "nouveau");

                $dateNonFormate = $_POST['date'];

                if(!preg_match(
                    '~^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$~',
                    $dateNonFormate)) {

                    header('Location: ../index.html');
                }
                
                $dateNonFormateSplit = explode("/", $dateNonFormate);
                $date = $dateNonFormateSplit[2] . "-" . $dateNonFormateSplit[1] . "-" . $dateNonFormateSplit[0];

                if (in_array($_POST['niveau'], ["indefini", "debutant", "intermediaire", "confirme"])) {
                    $niveau = $_POST['niveau'];
                } else {
                    header('Location: ../index.html');
                }

                $modele->addJoueur($prenom, $nom, $sexe, $anciennete, $date, $niveau);
                header('Location: ../index.html', true);
                

            } catch (Exception $e) {
                echo("problème");
                exit;
            }
        }
	} else {
        header('Location: index.html');
    }
?>