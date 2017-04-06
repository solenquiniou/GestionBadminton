$().ready(function(){
    
    $(".plusb").click(function(){

        //regex capturant le dernier nombre de la ligne
        var regExp = /(\d)*$/;
        //on applique le regex sur l'id
        var matches = regExp.exec($(this).attr('id'));

        //on récupère le numéro de la ligne
        var lign_num = matches[0];

        if ($("#detail"+lign_num).is(":hidden")) {
            
            $("#detail"+lign_num).show();
            $("#detailCol"+lign_num).show();
            $("#button"+lign_num).attr('value', '-');
            $("#button"+lign_num).css('background', '#003A23');
        } else {

            $("#detail"+lign_num).hide();
            $("#detailCol"+lign_num).hide();
            $("#button"+lign_num).attr('value', '+');
            $("#button"+lign_num).css('background', '#1F1959');
        }

    });

    $(".delb").click(function(){

        //regex capturant le dernier nombre de la ligne
        var regExp = /(\d)*$/;
        //on applique le regex sur l'id
        var matches = regExp.exec($(this).attr('id'));

        //on récupère le numéro de la ligne
        var lign_num = matches[0];
        var nomJ = $("#ligneNomPrenom"+lign_num+ " .nom").text();
        var prenomJ = $("#ligneNomPrenom"+lign_num+ " .prenom").text();

        $.redirect('index.php', {
            'method': 'del',
            'nomJ': nomJ,
            'prenomJ': prenomJ
        });

    });

    $("#ajouter").click(function(){
        $("#modal").dialog({
            width: 500
        });
    });

    $("#buttonExportCsv").click(function(){
        $.redirect('index.php', {
            'method': 'csv'
        });
    });

    $("#logout").click(function(){

        window.location.replace("../controleur/logout.php");

    });

    $(".editer").click(function(){
        
        //regex capturant le dernier nombre de la ligne
        var regExp = /(\d)*$/;
        //on applique le regex sur l'id
        var matches = regExp.exec($(this).attr('id'));

        //on récupère le numéro de la ligne
        var lign_num = matches[0];

        //Récupération des informations à modifier
        var prenom = $.trim($("#ligneNomPrenom"+lign_num + " .prenom").text());
        var nom = $.trim($("#ligneNomPrenom"+lign_num + " .nom").text());
        var ddn = $("#champsddn"+lign_num).val();
        var sexe = $("#champssexe"+lign_num).val();
        var anciennete = $("#champsanciennete"+lign_num).val();
        var niveau = $("#champsniveau"+lign_num).val();

        $.redirect('index.php', {
            'method': 'modif',
            'prenom': prenom,
            'nom': nom,
            'ddn': ddn,
            'sexe': sexe,
            'anciennete': anciennete,
            'niveau': niveau
        });
        
    });

});
