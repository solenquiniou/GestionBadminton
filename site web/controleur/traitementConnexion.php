<?php
require_once __DIR__."/../modele/Modele.php";
require_once __DIR__."/../vue/login.php";
require_once __DIR__."/../vue/adminPage.php";
class TraitementConnexion {

    private $vueLogin;
    private $vueAdmin;
    private $modele;

    function __construct() {
        $this->vueLogin = new VueLogin();
        $this->vueAdmin = new vueAdmin();
        try {
            $this->modele = new Modele();

        } catch (ConnexionException $e) {
            $this->vue->erreur("Échec de la connexion : " . $e->getMessage());
            exit;
        }
    }

    function checkPass($pseudo, $pass){

        try {
            $pseudo = $_POST['pseudo'];
            if(strlen($pseudo)>255) {
                $this->vueLogin->afficher(true);
                return;
            }

            $pass = $_POST['pass'];
            if(strlen($pass)>255) {
                $this->vueLogin->afficher(true);
                return;
            }

            $hashedPass = $this->modele->getPass($pseudo);

            if (crypt($pass, $hashedPass) == $hashedPass) {
                $_SESSION["utilisateur"] = $pseudo;
                $this->vueAdmin->afficher($this->modele->getJoueurs());
                return;
            } else {
                $this->vueLogin->afficher(true);
                return;
            }

        } catch (Exception $e) {
            echo("problème");
            exit;
        }
    }

    function accueil(){
        $this->vueLogin->afficher(false);
    }
}
?>
