<?php
require_once __DIR__."/modele/Modele.php";
require_once __DIR__."/adminPage.php";
$model = new Modele();
$p = new VueAdmin();
$p->afficher($model->getJoueurs());
?>
