
/*
 * Author: FABR
 */

/* 
 * Compute tile coordinates from pixel coordinates
 */
function pixel2tileslong(xt0, xp) {
	return xt0 + xp / 256;
}

function pixel2tileslat(yt0, yp) {
	return yt0 + yp / 256;
}

/*
 * Code from http://wiki.openstreetmap.org/wiki/Slippy_map_tilenames
 */

function long2tile(lon, zoom) {
	return (Math.floor((lon + 180) / 360 * Math.pow(2, zoom)));
}

function lat2tile(lat, zoom) {
	return (Math.floor((1 - Math.log(Math.tan(lat * Math.PI / 180) + 1
			/ Math.cos(lat * Math.PI / 180))
			/ Math.PI)
			/ 2 * Math.pow(2, zoom)));
}

function tile2long(x, z) {
	return (x / Math.pow(2, z) * 360 - 180);
}

function tile2lat(y, z) {
	var n = Math.PI - 2 * Math.PI * y / Math.pow(2, z);
	return (180 / Math.PI * Math.atan(0.5 * (Math.exp(n) - Math.exp(-n))));
}

/*
 * Code from http://www.brain4.de/programmierecke/js/numberFormat.php
 */

function /* out: String */number_format( /* in: float */number,
/* in: integer */laenge,
/* in: String */sep,
/* in: String */th_sep) {

	number = Math.round(number * Math.pow(10, laenge)) / Math.pow(10, laenge);
	str_number = number + "";
	arr_int = str_number.split(".");
	if (!arr_int[0])
		arr_int[0] = "0";
	if (!arr_int[1])
		arr_int[1] = "";
	if (arr_int[1].length < laenge) {
		nachkomma = arr_int[1];
		for (i = arr_int[1].length + 1; i <= laenge; i++) {
			nachkomma += "0";
		}
		arr_int[1] = nachkomma;
	}
	if (th_sep != "" && arr_int[0].length > 3) {
		Begriff = arr_int[0];
		arr_int[0] = "";
		for (j = 3; j < Begriff.length; j += 3) {
			Extrakt = Begriff.slice(Begriff.length - j, Begriff.length - j + 3);
			arr_int[0] = th_sep + Extrakt + arr_int[0] + "";
		}
		str_first = Begriff.substr(0, (Begriff.length % 3 == 0) ? 3
				: (Begriff.length % 3));
		arr_int[0] = str_first + arr_int[0];
	}
	return arr_int[0] + sep + arr_int[1];
}
