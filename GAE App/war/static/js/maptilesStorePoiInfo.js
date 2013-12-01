
/*
 * Author: FABR
 */

function init_maptilesStorePoiInfo() {
	// Register the form
	registerForm();
    
    // Register the insertion of GPS data into form on click     
	$("#map").click(function(e) {
		e.preventDefault();
		/*
		 * Extract basic constants about the map tile from hidden elements in the DOM
		 */
		var zoom = parseInt($("#zoom").html());
		var base_tile_x = parseInt($("#xtile").html());
		var base_tile_y = parseInt($("#ytile").html());

		/* Get offset of upper left corner of our map container */
		var cc = $("#center_container");
		var cc_x = parseInt(cc.offset().left + 0.5);
		var cc_y = parseInt(cc.offset().top + 0.5);
		
		/* Compute relative coordinates of mouse pointer */
		var rel_x = e.pageX - cc_x;
		var rel_y = e.pageY - cc_y;
		
		/* Insert coordinates into form fields */
		$('#id_name').val("");
		$('#id_latitude').val(number_format(tile2lat(pixel2tileslat(
				base_tile_y, rel_y), zoom), 7, '.', ''));
		$('#id_longitude').val(number_format(tile2long(pixel2tileslong(
				base_tile_x, rel_x), zoom), 7, '.', ''));
		$('#id_description').val("");
		
		var xx = rel_x - 6;
		var yy = rel_y - 6;
		
		
		$('#xCord').val(xx);
		$('#yCord').val(yy);
		
//		$('#point').css('top', rel_y +'px');
//		$('#point').css('left', rel_x +'px');
		
		// Clean feedback field of form
		$("#form_feedback").html("");
		// remove old error messages from form
		$("#form_section .errorlist").remove();

	});
}

function registerForm(){
	// Register the form
	var options = { 
	        //target:        '#form_feedback',   // target element(s) to be updated with server response 
	        //beforeSubmit:  showRequest,  // pre-submit callback 
	        success:       storePoiInfoShowResponse  // post-submit callback 
	 
	        // other available options: 
	        //url:       url         // override for form's 'action' attribute 
	        //type:      type        // 'get' or 'post', override for form's 'method' attribute 
	        //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
	        //clearForm: true        // clear all form fields after successful submit 
	        //resetForm: true        // reset the form after successful submit 
	 
	        // $.ajax options can be used here too, for example: 
	        //timeout:   3000 
	    };
	
    // bind form using ajaxForm 
    $('#poi_form').ajaxForm(options);
}

function storePoiInfoShowResponse(responseText, statusText, xhr, $form)  { 
    // for normal html responses, the first argument to the success callback 
    // is the XMLHttpRequest object's responseText property 
 
    // if the ajaxForm method was passed an Options Object with the dataType 
    // property set to 'xml' then the first argument to the success callback 
    // is the XMLHttpRequest object's responseXML property 
 
    // if the ajaxForm method was passed an Options Object with the dataType 
    // property set to 'json' then the first argument to the success callback 
    // is the json data object returned by the server 
 
    //alert('status: ' + statusText + '\n\nresponseText: \n' + responseText);

	// Create a proper HTML text with a single root node!
	var responseHTML = '<div>' + responseText + '</div>';
	
	/* Now select parts of the response
	 * 
	 */
	 
	// Is there a form update?
	var newFormPart=jQuery(responseHTML).find("#form_section").html();
	if (newFormPart){
		$("#form_section").html(newFormPart);
		// Re-Register the form
		registerForm();
	}

	// Is there a new or updated POI?
	var newPOI=jQuery(responseHTML).find(".poi").html();
	if (newPOI){
		// Get the id of the created or updated POI
		//alert(jQuery(responseHTML).find(".poi a").attr("id"));
		// Use 'filter' instead of 'find' since a is the top element of newPOI
		var the_id = jQuery(newPOI).filter("a").attr("id");
		
		// Remove the element with this id first from the DOM if it exists
		$("#"+the_id).remove();

		// Add the new or updated element to the DOM
		$("#all_pois").append(newPOI);
		
		// Re-Register the click function on all POIS including the new POI
		registerGetPoiInfoClick();
	}
}
