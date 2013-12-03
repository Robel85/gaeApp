package gae.map.client;

/*StoreServlet beinhaltet die Methode zur Persistierung "makePersistent" 
 * doPost fängt die gesendeten Daten aus dem HTML-Form ab und speichert dann die
 * Persister Entität in der Datenbank*/

import java.io.IOException;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class StoreServlet extends HttpServlet {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(StoreServlet.class
			.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String latitude = req.getParameter("latitude");
		String longitude = req.getParameter("longitude");
		String xCord = req.getParameter("xCord");
		String yCord = req.getParameter("yCord");

		Persister persisting = new Persister(name, description, latitude,
				longitude, xCord, yCord);

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			if (persisting != null)
				pm.makePersistent(persisting);
		} finally {
			pm.close();
		}

		resp.sendRedirect("/maps.jsp");
	}

}
