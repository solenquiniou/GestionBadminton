<?php
require_once __DIR__."/controleur/routeur.php";
session_start();
$routeur = new Roueur();
$routeur->start();

?>
