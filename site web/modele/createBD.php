<?php
/**
Ce fichier sert à générer la base de données lors de l'installation, il suffit de changer les identifiants :
host, username et password. Il suffit ensuite de lancer le script sur le serveur.
*/
	$host="localhost"; 

	$username="root"; 
	$password=""; 

    $dbname="adherents_badminton";

	$tb="CREATE TABLE joueurs(
    prenom VARCHAR(255),
    nom VARCHAR(255),
    sexe ENUM('homme', 'femme'),
    anciennete ENUM('nouveau', 'ancien'),
    ddn DATE,
    niveau ENUM('indefini', 'debutant', 'intermediaire', 'confirme')
    )";

    try {
        $dbh = new PDO("mysql:host=$host", $username, $password);

        //S'il y a un pb lors de la création de la base de données
        if(!$dbh->exec("CREATE DATABASE IF NOT EXISTS `$dbname`;")) { 
            print_r($dbh->errorInfo(), true);
        } else {

            // On crée la connection
            $conn = new mysqli($host, $username, $password, $dbname);

            //S'il y a eu une erreur pendant la connection
            if ($conn->connect_error) {
                die("Connection failed: " . $conn->connect_error);
            } 

            if ($conn->query($tb)) {
                echo "Table MyGuests created successfully";
            } else {
                echo "Error creating table: " . $conn->error;
            }
        }

    } catch (PDOException $e) {
        die("DB ERROR: ". $e->getMessage());
    }

?>