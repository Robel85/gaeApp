/*
 * Source:
 * book by Stefan Koch: Java Script
 * ISBN 978-3-89864.731-1
 *
 * Adapted to Django by FABR
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
	sendRequest("staedteViaXML", anzeigen, num);
}

function anzeigen() {
	if (req.readyState == 4) {

		var xml = req.responseText;
		var xmlDOM;

		if (typeof ActiveXObject != "undefined") {
			xmlDOM = new ActiveXObject("Microsoft.XmlDom");
			xmlDOM.loadXML(xml);
		} else {
			var parser = new DOMParser();
			xmlDOM = parser.parseFromString(xml,"text/xml");
		}

		var ausg = document.getElementById("ausgabe");
		

                var error_msg = xmlDOM.getElementsByTagName("fehler");
                if (error_msg[0].childNodes.length != 0) {
		    ausg.innerHTML =
			"Fehler: " + error_msg[0].childNodes[0].nodeValue;
                } else {
		    var n1 = xmlDOM.getElementsByTagName("name")[0];
		    var n2 = xmlDOM.getElementsByTagName("einwohner")[0];

		    var stadt = n1.childNodes[0].nodeValue;
		    var einwohner = n2.childNodes[0].nodeValue;

		    ausg.innerHTML =
			stadt + ", " + einwohner + " Tsd. Einwohner";
                }
	}
}

