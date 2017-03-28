<?php

class VueAdmin{

//@param joueur = résultat de la requete getJoueur,
function afficher($joueur){
?>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Administration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="vue/css/style.css"/>
    <link rel="stylesheet"  href="vue/css/adminPage.css"/>

  </head>

  <body>
    <div id="loginWrapper">
      <form method="post">
        <input id ="logout" type="submit" value="logout">
      </form>
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
        <tr class="ligneNomPrenom" id=<?php echo"ligneNomPrenom".$i ?> >
          <td class ="nom"> <?php  echo $joueurCour['nom'] ?> </td>
          <td class ="prenom"> <?php  echo $joueurCour['prenom'] ?> </td>
          <td class ="plus" >   <input class="plusb" id=<?php echo "button".$i ?> type="button" value="+"> </td>
          <td class ="del" >   <input class="delb" id=<?php echo "del".$i ?> type="button" value="X"> </td>
        </tr>
        <tr class="detail" id=<?php echo"detail".$i ?> >
          <td class ="date de naissance">  <?php  echo $joueurCour['ddn'] ?> </td>
          <td class ="genre"> <?php  echo $joueurCour['sexe'] ?> </td>
          <td class ="nouveau"> <?php  echo $joueurCour['anciennete'] ?> </td>
          <td class ="niveau"> niveau: <?php  echo $joueurCour['niveau'] ?> </td>
        </tr>
        <?php } ?>
      </table>

    </div>
    <script src="vue/script/script.js"></script>
    <script src="vue/script/detail.js"></script>
  </body>
</html>
<?php
}
}
