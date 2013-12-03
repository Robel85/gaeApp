package gae.map.client;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/*PMF wird vom PersistenceManager verwendet um Entitäten zu persistieren*/
public final class PMF {

	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	private PMF() {
	}

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}

}
