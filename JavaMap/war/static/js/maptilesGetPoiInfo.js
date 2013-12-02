

/*
 * Author: FABR
 */

function init_maptilesGetPoiInfo() {
	registerGetPoiInfoClick();
}

function registerGetPoiInfoClick() {
	// Note the unbind before.
	// This is handy if we added some new POI
	// See function storePoiInfoShowResponse()
	$(".poi a").unbind("click").click(function(e) {
		e.preventDefault();

		var url = $(this).attr("href");
		// Do a JSON query
		// Pass key not as GET-parameter but as part of the URL
		// This is Django style!
		$.getJSON(url, showPoiInfo);
	});
}

function showPoiInfo(data) {
	var poi = data[0];
	// remove old error messages from form
	$("#form_section .errorlist").remove();

	if (poi.fehler) {
		$("#form_feedback").html("Fehler: " + poi.fehler);
	} else {
		$("#form_feedback").html("Abrufen von POI-info via AJAX getJSON() war erfolgreich");

		$('#id_name').val(poi.name);
		$('#id_latitude').val(poi.latitude);
		$('#id_longitude').val(poi.longitude);
		$('#id_description').val(poi.description);		
	}
}