<?php

class VueLogin{

//@param joueur = Si le formulaire n'est pas bien renseigné
function afficher($erreur){
?>

<html>
  <head>
    <meta charset="UTF-8">
    <title>Formulaire d'inscription</title>
    <link rel="stylesheet" type="text/css" href="vue/css/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  </head>

  <body>
    <div id="retourWrapper">
        <form method="post">
            <input type="submit" value="retour">
        </form>
    </div>
    <div id="loginFieldContainer">
        <form method="post">
            <ul class="form-style-1">
                <li>
                    <label>Login</label>
                    <input type="text" name="pseudo" class="field-long"/>
                </li>
                <li>
                    <label>Mot de passe</label>
                    <input type="password" name="pass" class="field-long"/>
                </li>
                <li>
                    <input type="submit" name="soumettreLogin" value="Connexion"/>
                </li>
            </ul>
        </form>
    </div>
    <script src="vue/script/script.js"></script>
  </body>
</html>
<?php
 }
}

?>