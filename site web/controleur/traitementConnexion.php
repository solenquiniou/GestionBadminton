<?php
require_once __DIR__."/../modele/Modele.php";

  	if(!empty($_POST))
  	{
    	if(empty($_POST['pseudo']) 
            || empty($_POST['pass'])){
            header('Location: ../login.html');
    	} else {
            try {
                $modele = new Modele();

                $pseudo = $_POST['pseudo'];
                if(strlen($pseudo)>255) {
                    header('Location: ../login.html');
                }

                $pass = $_POST['pass'];
                if(strlen($pass)>255) {
                    header('Location: ../login.html');
                }

                $hashedPass = $modele->getPass($pseudo);

                if (crypt($pass, $hashedPass) == $hashedPass) {
                    $_SESSION["utilisateur"] = $pseudo;
                    header('Location: ../gestionJoueurs.html', true);
                }
                
            } catch (Exception $e) {
                echo("problème");
                exit;
            }
        }
	} else {
        header('Location: login.html');
    }
?>