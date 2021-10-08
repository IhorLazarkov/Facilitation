"use strict";

function FindByName(src, dest){

    const self = this;
    this.URL = "http://localhost:8081/autocomplete",
    this.value = src.val();

    return {
        lookUp : function(){
            $.ajax({
                method: "GET",
                url: self.URL,
                cache: true,
                data: { name: self.value },

                success: function(data){
                    var v = "";
                    for(const item in data){
                        v = v.concat("<option value=\"" + data[item] + "\"/>");
                    }
                    console.log(v);
                    dest.empty();
                    dest.append(v);
                },

                error: function(err){
                    console.log(err);
                }
            });
        }
    }
}