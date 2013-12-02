package gae.map.client;

import gae.map.models.MapSources;
import gae.map.models.PathSource;

public class PathController {
	private MapSources maps = new MapSources();
	Integer countY = 0;
	Integer countX = 0;

	public PathController(String s, Integer countX, Integer countY) {
		maps.setMapName(s);
		this.countX = countX;
		this.countY = countY;
	}

	public void parsePaths() {

		String end = ".png";
		if(maps.getMapName()!=null && maps.getMapName().equals("Relief")) end = ".jpg";
		Integer posY = 20 + countY;
		Integer posX = 33 + countX;

		for (int i = 0; i < 3; i++) {
			maps.getWestPathList().add(
					new PathSource("static/img/" + maps.getMapName() + "/6/"
							+ posX + "/" + posY + end + ".tile"));
			posY++;
		}
		posY = 20 + countY;
		posX++;
		for (int i = 0; i < 3; i++) {
			maps.getEastPathList().add(
					new PathSource("static/img/" + maps.getMapName() + "/6/"
							+ posX + "/" + posY + end + ".tile"));
			posY++;
		}

	}
	
	public String getParsedPath(PathSource path){
		
		String parsedPath ="";
		
		parsedPath = path.getPath().substring(11, path.getPath().indexOf('.'));
				
		
		
		return parsedPath;
	}
	
	public String getFileFormat(PathSource path){
		String fileFormat ="";
		
		fileFormat = path.getPath().substring(path.getPath().indexOf('.')+1,path.getPath().indexOf('.')+4 );
		
		return fileFormat;
	}

	public MapSources getMaps() {
		return maps;
	}

	public void setMaps(MapSources maps) {
		this.maps = maps;
	}
}
