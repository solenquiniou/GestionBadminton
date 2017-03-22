<?php


class VueAdmin{

//@param joueur = résultat de la requete getJoueur,
function afficher($joueur){
?>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Administration</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
    <link rel="stylesheet" type="text/css" href="adminPage.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  </head>

  <body>
    <div id="loginWrapper">
        <input type="button" value="logout">
    </div>

    <div id="tabContainer">
      <div id="preTableau">
          <input type="button" value="ajouter">
      </div>
      <table>
        <?php
        $nbJoueur = count($joueur);
        for ($i = 0; $i < $nbJoueur; $i++) {
            $joueurCour = $joueur[$i];
        ?>
        <tr class="ligneNomPrenom">
          <td class ="nom"> <?php  echo $joueurCour['nom'] ?> </td>
          <td class ="prenom"> <?php  echo $joueurCour['prenom'] ?> </td>
          <td class ="plus"> + </td>
        </tr>
        <tr class="detail">
          <td class ="date de naissance">  <?php  echo $joueurCour['ddn'] ?> </td>
          <td class ="genre"> <?php  echo $joueurCour['sexe'] ?> </td>
          <td class ="nouveau"> <?php  echo $joueurCour['anciennete'] ?> </td>
          <td class ="niveau"> <?php  echo $joueurCour['niveau'] ?> </td>
        </tr>
        <?php } ?>
      </table>

    </div>
    <script src="script.js"></script>
  </body>
</html>
<?php
}
}
