package gae;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@ManagedBean
@SessionScoped
public class Bean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String pfad;
	private String test;

	public String getPfad() {
		return pfad;
	}

	public void setPfad(String pfad) {
		this.pfad = pfad;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
	public void printer(){
		if(test.equals("Java")){
			pfad = "Valid user";
		} else{
			pfad = "Invalid user";
		}
	}
	
	@SuppressWarnings("deprecation")
	public String printPoints() {
		String s = "";
		s = "<form id='points' action='/recover'  method='post'>";
		
		Key key = KeyFactory.createKey("Persister", "id");
		Entity persister = new Entity("Persister", key);
		
		persister.setProperty("name", "id");
		persister.setProperty("vari", "var");
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		datastore.put(persister);

		Query q = new Query("Persister");
		q.addFilter("name", Query.FilterOperator.NOT_EQUAL, " ");
		
		PreparedQuery pq = datastore.prepare(q);
        
		for (Entity result : pq.asIterable()) {
					s = s
						+ "<a id='test' href='' onclick='parentNode.submit()'><img border='0' style='position: absolute;"
						+ " top:"
						+ result.getProperty("yCord")
						+ "px; "
						+ "left:" 
						+ result.getProperty("xCord")
						+ "px;'"
						+ " src='static/img/kreis.png' alt='kreis' width='11' height='11' "
						+ "name="+result.getProperty("name")+"></a><br/>";
			
		}

		s = s
				+ "<a id='test'><img border='0' style='position: absolute;"
				+ " src='static/img/kreis.png' alt='kreis' width='11' height='11' id='point'></a><br/>";

		s = s + "</form>";

		return s;
	}

}
