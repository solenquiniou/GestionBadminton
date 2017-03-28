$().ready(function(){

//Partie index.html ------------------------------------------------------------------------------------------

  //Lorsque l'on change l'input prenom, si le champs est vide on demande de renseigner le prenom
  $("[name=prenom]").change(function(){
    if (this.value == "") {
      $("#texteNom").html("* Veuillez renseigner votre nom et votre prénom");
    } else if (this.value.length>255) {
      $("#texteNom").html("* Votre prenom est trop long");
    } else {
      $("#texteNom").html("*");
    }
  });

  //Lorsque l'on change l'input nom, si le champs est vide on demande de renseigner le nom
  $("[name=nom]").change(function(){
    if (this.value == "") {
      $("#texteNom").html("* Veuillez renseigner votre nom et votre prénom");
    } else if (this.value.length>255) {
      $("#texteNom").html("* Votre nom est trop long");
    } else {
      $("#texteNom").html("*");
    }
  });

  //Lorsque l'on change l'input date, si le champs est vide on demande de renseigner la date
  //Si la date n'est pas au bon format, on dit à l'utilisateur que le format est incorrect
  $("[name=date]").change(function(){
    if (this.value == "") {
      $("#texteDate").html("* Veuillez renseigner une date");
    } else if (!/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/.test(this.value)) {
      $("#texteDate").html("* Le format est incorrect !");
    } else {
      $("#texteDate").html("*");
    }
  });

  $('#formulaireJoueur').submit(function () {
    var prenom = $.trim($('[name=prenom]').val());
    var nom = $.trim($('[name=nom]').val());
    var date = $.trim($('[name=date]').val());

    if (prenom  === '') {
      $("#texteNom").html("* Veuillez renseigner votre prenom");
      return false;
    }

    if (prenom.length>255) {
      $("#texteNom").html("* Votre prenom est trop long");
      return false;
    }

    if (nom  === '') {
      $("#texteNom").html("* Veuillez renseigner votre nom");
      return false;
    }

    if (nom.length>255) {
      $("#texteNom").html("* Votre prenom est trop long");
      return false;
    }

    if (date  === '') {
      $("#texteDate").html("* Veuillez renseigner une date");
      return false;
    }

    if (!date.match(/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/)) {
      $("#texteDate").html("* Le format est incorrect !");
      return false;
    }
  });

//fin partie index.html ------------------------------------------------------------------------------------------
});