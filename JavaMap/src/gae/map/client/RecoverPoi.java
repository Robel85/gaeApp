package gae.map.client;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@SuppressWarnings("serial")
public class RecoverPoi extends HttpServlet {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(StoreServlet.class.getName());
	
	private String poiName;
	private String poiLatitude;
	private String poiLongitude;
	private String poiDescription;
	
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		setStrings();
		
		String name = req.getParameter("name");
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query("Persister");

		q.addFilter("name", Query.FilterOperator.EQUAL, name);

		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(q);

		for (Entity result : pq.asIterable()) {
			poiName			= (String) result.getProperty("name");
			poiDescription 	= (String) result.getProperty("description");
			poiLatitude 	= (String) result.getProperty("latitude");
			poiLongitude 	= (String) result.getProperty("longitude");
		}
		
//		resp.sendRedirect("/maps.jsp?id=GoogleMaps&xCord=0&yCord=0&name="+poiName+"&description="+poiDescription+"&latitude="+poiLatitude+"&longitude="+poiLongitude);
	}

	private void setStrings() {
		poiName = new String();
		poiLatitude = new String();
		poiLongitude = new String();
		poiDescription = new String();
		
	}

	public String getPoiName() {
		return poiName;
	}

	public String getPoiLatitude() {
		return poiLatitude;
	}

	public String getPoiLongitude() {
		return poiLongitude;
	}

	public String getPoiDescription() {
		return poiDescription;
	}

	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}

	public void setPoiLatitude(String poiLatitude) {
		this.poiLatitude = poiLatitude;
	}

	public void setPoiLongitude(String poiLongitude) {
		this.poiLongitude = poiLongitude;
	}

	public void setPoiDescription(String poiDescription) {
		this.poiDescription = poiDescription;
	}

}
