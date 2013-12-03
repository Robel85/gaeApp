package gae.map.client;

import gae.map.models.MapSources;
import gae.map.models.PathSource;

import javax.servlet.http.HttpServletRequest;

public class PathController {
	private MapSources maps = new MapSources();
	public Integer countY = 0;
	public Integer countX = 0;
	public String targetMap;
	public Integer xDirection=0;
	public Integer yDirection=0;
	public HttpServletRequest request;
	public boolean delete = false;
	
	public PathController(HttpServletRequest requester){
		request = requester;
		
	}
	
	public void checkAndInitialize(){
		
		
		if(request.getParameter("id")!=null&&request.getParameter("xCord")!=null&&
				request.getParameter("yCord")!=null&&request.getParameter("yCord")!=null)
		{
			targetMap = (String) request.getParameter("id");
			countY = Integer.parseInt(request.getParameter("yCord"));
	 		countX = Integer.parseInt(request.getParameter("xCord"));
		} else if(request.getParameter("del")!=null&&(request.getParameter("id")!=null)){
			String deleted = (String) request.getParameter("del");
			targetMap = (String) request.getParameter("id");
			if(deleted.equals("1")){
				delete = true;
			}
		}else if(request.getParameter("id")!=null){
			targetMap = (String) request.getParameter("id");
			countY = 0;
			countX = 0;
		} 
		else{
			targetMap = "GoogleMaps";
			countY = 0;
			countX = 0;
		}
		maps.setMapName(targetMap);
		
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
