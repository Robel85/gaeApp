package gae.map.client;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class DataRecover {

	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public DataRecover() {
//		// Get the Datastore Service
//
//		// The Query interface assembles a query
//		Query q = new Query("Persister");
//
//		q.addFilter("name", Query.FilterOperator.EQUAL, "as");
//
//		// PreparedQuery contains the methods for fetching query results
//		// from the datastore
//		PreparedQuery pq = datastore.prepare(q);
//
//		for (Entity result : pq.asIterable()) {
//			String desc = (String) result.getProperty("description");
//			String lati = (String) result.getProperty("latitude");
//			String longi = (String) result.getProperty("longitude");
//			System.out.println(desc + " " + lati + ", " + longi);
//		}

	}

	@SuppressWarnings("deprecation")
	public String printPoints(Integer counterX, Integer counterY)  {
		String s = "";

		
		try{
		Query q = new Query("Persister");
		q.addFilter("name", Query.FilterOperator.NOT_EQUAL, " ");
		PreparedQuery pq = datastore.prepare(q);
        

		for (Entity result : pq.asIterable()) {
			if (result != null) {
				Integer y = (Integer.parseInt((String) result.getProperty("yCord"))) + counterY;
				Integer x = (Integer.parseInt((String) result.getProperty("xCord"))) + counterX;
				if(x>0&&x<512){
					if(y>0&&y<768)
				s = s
						+ "<a id='test' href='recover?"+result.getProperty("name")+"'><img border='0' style='position: absolute;"
						+ " top:"
						+ y.toString()
						+ "px; "
						+ "left:" 
						+ x.toString()
						+ "px;'"
						+ " src='/static/img/kreis.png' href='recover?"+result.getProperty("name")+"' alt='kreis' width='11' height='11' "
						+ "name="+result.getProperty("name")+"></a><br/>";
				}
			}
		}

		s = s
				+ "<a id='test'><img border='0' style='position: absolute;"
				+ " src='static/img/kreis.png' alt='kreis' width='11' height='11' id='point'></a><br/>";

		s = s + "</form>";
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		return s;
	}

	public String getName(String lati, String longi) {

		String name = "";

		if (lati.equals("") || longi.equals("")) {
			return "";
		}

		else
			return name;
	}

	public String getDescription(String name) {

		String description = "";

		if (name.equals("")) {
			return "";
		}

		else
			return description;
	}

}
