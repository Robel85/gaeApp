
function init_maptilesAnimate() {
	$("#map").mousemove(
			function(e) {
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
				
				/* Show info */
				$("#mousepos").html(
						'GPS-Position linke obere Ecke: Breite '
								+ number_format(tile2lat(base_tile_y, zoom), 7,
										'.', '')
								+ ', Länge '
								+ number_format(tile2long(base_tile_x, zoom),
										7, '.', '')
								+ '<br />'
								+ 'Offset in Pixel: '
								+ cc_x
								+ ', '
								+ cc_y
								+ '<br />'
								+ 'Mausposition absolut: '
								+ e.pageX
								+ ', '
								+ e.pageY
								+ '<br /> Mauspostion relativ: '
								+ rel_x
								+ ', '
								+ rel_y
								+ '<br /> GPS-Position: Breite '
								+ number_format(tile2lat(pixel2tileslat(
										base_tile_y, rel_y), zoom), 7, '.', '')
								+ ', Länge '
								+ number_format(tile2long(pixel2tileslong(
										base_tile_x, rel_x), zoom), 7, '.', '')

				);
			});
}
