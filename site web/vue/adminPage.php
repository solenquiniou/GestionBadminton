<?php

class VueAdmin{

//@param joueur = résultat de la requete getJoueur,
function afficher($joueur){
?>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Administration</title>
    <link rel="stylesheet" type="text/css" href="vue/css/style.css"/>
    <link rel="stylesheet"  href="vue/css/adminPage.css"/>
    <link rel="stylesheet"  href="vue/css/jquery-ui.min.css"/>
    
    <!-- scripts Jquerys -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="vue/script/jquery.redirect.js"></script>
    <script src="vue/script/jquery-ui.min.js"></script>
    
    <!-- Nos scripts -->
    <script src="vue/script/script.js"></script>
    <script src="vue/script/detail.js"></script>

  </head>

  <body>
    <div id="loginWrapper">
      <form method="post">
        <input id ="logout" type="submit" value="logout">
      </form>
    </div>

    <div id="tabContainer">
      <div id="preTableau">
          <input id="ajouter" type="button" value="ajouter">
      </div>
      <table>
        <?php
        $nbJoueur = count($joueur);
        for ($i = 0; $i < $nbJoueur; $i++) {
            $joueurCour = $joueur[$i];
        ?>
        <tr class="ligneNomPrenom" id=<?php echo"ligneNomPrenom".$i ?> >
          <td class ="nom"> <?php echo $joueurCour['nom'] ?> </td>
          <td class ="prenom"> <?php echo $joueurCour['prenom'] ?> </td>
                <td>  </td>

          <td class ="plus" >   <input class="plusb" id=<?php echo "button".$i ?> type="button" value="+"> </td>
          <td>  </td>
          <td class ="del" >   <input class="delb" id=<?php echo "del".$i ?> type="button" value="X"> </td>
        </tr> 
        <tr class="detail noBorder" id=<?php echo"detailCol".$i ?> >
          <td> Date </td>
          <td> Genre </td>
          <td> Ancienneté </td>
          <td> Niveau </td>
        </tr>
        <tr class="detail" id=<?php echo"detail".$i ?> >

          <td class ="date de naissance">
            <input id="<?php echo 'champsddn'.$i ?>" type="text" value="<?php  echo $joueurCour['ddn'] ?>" />
          </td>
          <td class ="genre">
            <input id="<?php echo 'champssexe'.$i ?>" type="text" value="<?php  echo $joueurCour['sexe'] ?>" />
          </td>
          <td class ="nouveau">
            <input id="<?php echo 'champsanciennete'.$i ?>" type="text" value="<?php echo $joueurCour['anciennete'] ?>" />
          </td>
          <td class ="niveau">
            <input id="<?php echo 'champsniveau'.$i ?>" type="text" value="<?php  echo $joueurCour['niveau'] ?>" />
          </td>

          <td>
            <input class="editer" id=<?php echo "edit".$i ?> type="button" value="editer"> 
          </td>
       
        </tr>

        <?php } ?>
      </table>

    </div>
    
    <div id="wrapperButtonCsv">
      <input id="buttonExportCsv" type="button" value="Exporter au format CSV">
    </div>

    <!-- Fenêtre modale (apparait lors du clic sur "ajouter") -->
    <div id="modal" style="display: none">
      <form id="formulaireJoueur" method="post">
      <input type="hidden" value="true" name="admin" />
        <ul class="form-style-1">
            <li><label>Nom complet<span class="required" id="texteNom">*</span></label><input type="text" name="prenom" class="field-divided" placeholder="Prénom" />&nbsp;<input type="text" name="nom" class="field-divided" placeholder="Nom" /></li>
            <li>
                <label>Date de naissance<span class="required" id="texteDate">*</span></label>
                <input type="text" name="date" class="field-long" placeholder="Date de naissance (JJ/MM/AAAA)" />
            </li>
            <li>
                <label>Êtes vous un homme ou une femme? <span class="required">*</span></label>
                <label><input id="homme" type="radio" name="sexe" value="Homme" checked="checked">Homme</label>
                <label><input id="femme" type="radio" name="sexe" value="Femme">Femme</label>
            </li>
            <li>
                <label>Êtes-vous un nouvel adhérent? <span class="required">*</span></label>
                <label><input id="nouvAdherent" type="radio" name="anciennete" value="nouvAdherent" checked="checked">Nouvel adhérent</label>
                <label><input id="ancAdherent" type="radio" name="anciennete" value="ancAdherent">Ancien adhérent</label>
            </li>
            <li>
                <label>Quel niveau de jeu pensez-vous avoir?</label>
                <select name="niveau" class="field-select">
                <option value="indefini">Indéfini</option>
                <option value="debutant">Débutant</option>
                <option value="intermediaire">Intermédiaire</option>
                <option value="confirme">Confirmé</option>
                </select>
            </li>
            <li>
                <input type="submit" name="soumettreFormulaire" value="Envoyer" />
            </li>
        </ul>
      </form>
    </div>
  </body>
</html>
<?php
}
}
