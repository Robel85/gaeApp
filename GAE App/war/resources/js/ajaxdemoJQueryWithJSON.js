/*
 * Source:
 * book by Stefan Koch: Java Script
 * ISBN 978-3-89864.731-1
 *
 * Adapted to Django, JSON and JQuery by FABR
 */

$(document).ready(function() {
  $("map area").mouseover(function() {
    // Access id area element : $(this).attr("id")
    // alert($(this).attr("id"));

    // Do a JSON query
    // Pass key not as GET-parameter but as part of the URL (Django style)
    $.getJSON('staedteViaJQueryAndJSON/' + $(this).attr("id"), anzeigen);
    // Force error
    // $.getJSON('staedteViaJQueryAndJSON/' + 'bla', anzeigen);
  });
});

function anzeigen(data) {
        var stadt = data[0];
        if(stadt.fehler){
                $("#ausgabe").html("Fehler: " + stadt.fehler);
        } else {
                $("#ausgabe").html(stadt.name + ", " + stadt.einwohner + " Tsd. Einwohner"); 
        }
}
