$().ready(function(){
  $(".plusb").click(function(){
    var lign_num=this.id.slice(-1);
    if ($("#detail"+lign_num).is(":hidden")) {
        $("#detail"+lign_num).show();
        $("#detailCol"+lign_num).show();
        $("#button"+lign_num).attr('value', '-');
        $("#button"+lign_num).css('background', '#003A23');
    }else{
        $("#detail"+lign_num).hide();
        $("#detailCol"+lign_num).hide();
        $("#button"+lign_num).attr('value', '+');
        $("#button"+lign_num).css('background', '#1F1959');
    }

  });

  $(".del").click(function(){
    var lign_num=this.id.slice(-1);
    var nomJ = $("#ligneNomPrenom"+lign_num).closest(".nom");
    var prenomJ = $("#ligneNomPrenom"+lign_num).closest(".prenom");
    $.ajax({
        url: 'controleur/traitementAdmin.php',
        type: 'POST',
        data: {delRow:"true",nom:nomJ,nom:prenomJ},
        success: function(data) {
            console.log(data); // Inspect this in your console
        }
    });

  });

  $("#ajouter").click(function(){
    //// TODO: formulaire d'ajout un peu comme le premier
  });

  $("#logout").click(function(){
    $.ajax({
        type: 'POST',
        url: 'controleur/routeur.php',
        data: "logout=true",
        success: function(response) { alert(response); }
    });


  });

  $("#editer").click(function(){
    ////TODO cf click mais en passant plus de paramètre

  });



});
