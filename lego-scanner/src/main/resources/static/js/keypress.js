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
        for(const item in data){
            v = v.concat("<option value=\"" + data[item] + "\"/>");
        }
        console.log(v);
        autocomplete.empty();
        autocomplete.append(v);
    });
});
