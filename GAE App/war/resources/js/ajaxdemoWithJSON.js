/*
 * Source:
 * book by Stefan Koch: Java Script
 * ISBN 978-3-89864.731-1
 *
 * Adapted to Django and JSON by FABR
 */

function getXMLHttpRequest() {
	var httpReq = null;
	if (window.XMLHttpRequest) {
		httpReq = new XMLHttpRequest();
	} else if (typeof ActiveXObject != "undefined") {
		httpReq = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return httpReq;
}

function sendRequest(url, handler, param) {
	req = getXMLHttpRequest();
	if (req) {
		req.onreadystatechange = handler;
		req.open("get", url + "?value=" + param, true);
		req.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
		req.send(null);
	}
}

function info(num) {
        /* force an error
        if (num == 2) {
            //num = "kkkkkk";
            num = "4711";
        }
        */ 
	sendRequest("staedteViaJSON", anzeigen, num);
}

function anzeigen() {
	if (req.readyState == 4) {
		var parsedResponse = JSON.parse(req.responseText);
                var stadt = parsedResponse[0];
		var ausg = document.getElementById("ausgabe");

                if (stadt.fehler) {
		    ausg.innerHTML =
			"Fehler: " + stadt.fehler;
                } else {
		    ausg.innerHTML =
			stadt.name + ", " + stadt.einwohner + " Tsd. Einwohner";
                }
	}
}

