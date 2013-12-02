package gae.map.models;

import java.util.LinkedList;

public class MapSources {

	private String mapName = "";
	private String xCoor = "";

	private LinkedList<PathSource> eastPathList = new LinkedList<PathSource>();
	private LinkedList<PathSource> westPathList = new LinkedList<PathSource>();
	
	public MapSources(){

	}

	public String getMapName() {
		return mapName;
	}

	public String getxCoor() {
		return xCoor;
	}


	public LinkedList<PathSource> getEastPathList() {
		return eastPathList;
	}

	public LinkedList<PathSource> getWestPathList() {
		return westPathList;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public void setxCoor(String xCoor) {
		this.xCoor = xCoor;
	}

	public void setEastPathList(LinkedList<PathSource> eastPathList) {
		this.eastPathList = eastPathList;
	}

	public void setWestPathList(LinkedList<PathSource> westPathList) {
		this.westPathList = westPathList;
	}
	
	
	
}
