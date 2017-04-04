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
    //TODO
  
  });

  $("#ajouter").click(function(){
    //// TODO:
  });

  $("#logout").click(function(){
    //// TODO:
  });



});
