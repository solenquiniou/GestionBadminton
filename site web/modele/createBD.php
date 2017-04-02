<?php
require_once __DIR__.'/../config.php';
/**
* Ce fichier sert à générer la base de données lors de l'installation, il suffit de changer les identifiants :
* host, username et password. Il suffit ensuite de lancer le script sur le serveur.
* Il faut renseigner dans le fichier config le nom de la base, le nom de compte et le mot de passe
* A supprimer après avoir crée la base
*/

	$host="localhost"; 

    /* Les 5 lignes suivantes iront chercher les identifiants de connexion à la base MySQL 
    dans le fichier config.php situé à la racine du site */
	$username=Config::$DB_USER; 
	$password=Config::$DB_PASSWD; 
    $dbname=Config::$DB_NAME;
    $tablename=Config::$DB_tableJoueurs;
    $tablename2=Config::$DB_tableAdministrateurs;

    //Choix du pseudo et mot de passe de l'administrateur
    $loginAdministrateur = "squiniou";
    $passwordAdministrateur = "projetIUT2017";


    /* Ne pas modifier la suite */

    $salt = "$6\$round=5000$".md5($passwordAdministrateur)."$";
    $hashPasswordAdministrateur = crypt($passwordAdministrateur, $salt);

    //ligne de commande pour créer la base contenant les joueurs
	$tb="CREATE TABLE IF NOT EXISTS $tablename(
        id          INT             NOT NULL PRIMARY KEY AUTO_INCREMENT,
        prenom      VARCHAR(255),
        nom         VARCHAR(255),
        sexe        ENUM('homme', 'femme'),
        anciennete  ENUM('nouveau', 'ancien'),
        ddn         DATE,
        niveau      ENUM('indefini', 'debutant', 'intermediaire', 'confirme')
    )";
    
    //ligne de commande pour créer la base contenant les administrateurs
    $tb2="CREATE TABLE IF NOT EXISTS $tablename2(
        id          INT             NOT NULL PRIMARY KEY AUTO_INCREMENT,
        login       VARCHAR(255)    NOT NULL,
        password    VARCHAR(255)    NOT NULL
    )";

    //Ignore fait en sorte que l'administrateur ne soit pas ajouté plusieurs fois si le script php est lancé plusieurs fois
    $cmdAjoutAdmin="INSERT IGNORE INTO $tablename2 (login, password) VALUES('$loginAdministrateur','$hashPasswordAdministrateur')";
    
    //Connexion à MySQL, création de la base et des tables
    try {
        $dbh = new PDO("mysql:host=$host", $username, $password);

        //S'il y a un pb lors de la création de la base de données
        if(!$dbh->exec("CREATE DATABASE IF NOT EXISTS `$dbname`;")) { 
            print_r($dbh->errorInfo(), true);
        } else {

            $dbh=null;

            $chaine="mysql:host=$host;dbname=$dbname;charset=utf8";
            $conn = new PDO($chaine,$username,$password);

            //création de la table tb
            if ($conn->query($tb)) {
                echo "Table $tablename created successfully </br>";
            } else {
                echo "Il y a eu une erreur lors de la création de la table: ";
                print_r($conn->errorInfo());
            }

            //création de la table tb2
            if ($conn->query($tb2)) {
                echo "Table $tablename2 created successfully </br>";
            } else {
                echo "Il y a eu une erreur lors de la création de la table: ";
            }

            if ($conn->query($cmdAjoutAdmin)) {
                echo "l'administrateur a été ajouté </br>";
            } else {
                echo "Il y a eu une erreur lors de l'ajout de l'administrateur: ";
            }
        }

    } catch (PDOException $e) {
        die("DB ERROR: ". $e->getMessage());
    }

?>