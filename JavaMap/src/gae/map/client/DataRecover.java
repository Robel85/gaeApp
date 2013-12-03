package gae.map.client;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/* Klasse zum Abrufen der Pois aus der Datenbank und Rückgabe an maps.jsp*/

public class DataRecover {

	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	PathController cont;

	public DataRecover(PathController conter) {
		cont = conter;
	}

	public void checkXY() {
		// prüfen ob die Sicht verschoben wurde und poi-positionen anpassen
		if (cont.countX == 0 && cont.countY == -1) {
			cont.xDirection = 0;
			cont.yDirection = 256;
		} else if (cont.countX == 0 && cont.countY == 1) {
			cont.xDirection = 0;
			cont.yDirection = -256;
		} else if (cont.countX == -1 && cont.countY == 0) {
			cont.xDirection = 256;
			cont.yDirection = 0;
		} else if (cont.countX == 1 && cont.countY == 0) {
			cont.xDirection = -256;
			cont.yDirection = 0;
		} else if (cont.delete) {
			deleteAllPoi();
		} else {
			cont.xDirection = 0;
			cont.yDirection = 0;
		}
	}

	// Methode zum löschen aller Pois
	@SuppressWarnings("deprecation")
	public void deleteAllPoi() {
		try {
			Query q = new Query("Persister");
			q.addFilter("name", Query.FilterOperator.NOT_EQUAL, " ");
			PreparedQuery pq = datastore.prepare(q);

			for (Entity result : pq.asIterable()) {
				if (result != null) {
					datastore.delete(result.getKey());
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	// gibt alle gespeicherten pois als String zurück
	public String printPoints(Integer counterX, Integer counterY) {
		String s = "";

		try {
			Query q = new Query("Persister");
			q.addFilter("name", Query.FilterOperator.NOT_EQUAL, " ");
			PreparedQuery pq = datastore.prepare(q);

			for (Entity result : pq.asIterable()) {
				if (result != null) {
					Integer y = (Integer.parseInt((String) result
							.getProperty("yCord"))) + counterY;
					Integer x = (Integer.parseInt((String) result
							.getProperty("xCord"))) + counterX;
					if (x > 0 && x < 512) {
						if (y > 0 && y < 768)
							s = s
									+ "<a id='test' href='recover?"
									+ result.getProperty("name")
									+ "'><img border='0' style='position: absolute;"
									+ " top:"
									+ y.toString()
									+ "px; "
									+ "left:"
									+ x.toString()
									+ "px;'"
									+ " src='/static/img/kreis.png' href='recover?"
									+ result.getProperty("name")
									+ "' alt='kreis' width='11' height='11' "
									+ "name=" + result.getProperty("name")
									+ "></a><br/>";
					}
				}
			}

			s = s
					+ "<a id='test'><img border='0' style='position: absolute;"
					+ " src='static/img/kreis.png' alt='kreis' width='11' height='11' id='point'></a><br/>";

			s = s + "</form>";
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return s;
	}
}
