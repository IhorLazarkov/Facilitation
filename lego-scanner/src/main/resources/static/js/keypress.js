"use strict";

$("#findByName").keyup(function(event){

    const el = $("#findByName"),
        autocomplete = $("#autocomplete"),
        lookup = el.val();

    $.ajax({
        method: "GET",
        url: "http://localhost:8081/autocomplete",
        cache: true,
        data: { name: lookup }
    }).done(function(data){
        var v = "";
        if(lookup === ""){
            autocomplete.hide();
        } else {
            autocomplete.show();
        }
        for(const item in data){
            v = v.concat(item + ": " + data[item] + "\n");
            console.log(v);
            autocomplete.text(v);
        }
    });
});
