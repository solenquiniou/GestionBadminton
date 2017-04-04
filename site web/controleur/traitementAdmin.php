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
    function start(){
    
      if(isset($_POST["delRow"])){
            $this->modele->modele($nom,$prenom);
            $this->vueAdmin->afficher($this->modele->getJoueurs());
      }else{
        if(isset($_POST["modifierJoueur"])){
          //todo repasser les info a modifier
              $this->modele->modifier($nom,$prenom);
              $this->vueAdmin->afficher($this->modele->getJoueurs());
        }else{
          $this->vueAdmin->afficher($this->modele->getJoueurs());
        }
        //TODO les autres isset
      }
    }


}
?>
