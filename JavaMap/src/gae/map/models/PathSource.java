package gae.map.models;

/*Eine Klasse zur Speicherung des Pfades f�r die parse-funktionalit�t */
public class PathSource {

	String path = "";

	public PathSource(String string) {
		path = string;
	}

	public String getPath() {
		return path;
	}

}
