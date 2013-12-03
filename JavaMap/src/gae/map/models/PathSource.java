package gae.map.models;

/*Eine Klasse zur Speicherung des Pfades für die parse-funktionalität */
public class PathSource {

	String path = "";

	public PathSource(String string) {
		path = string;
	}

	public String getPath() {
		return path;
	}

}
