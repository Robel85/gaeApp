<!doctype html>

<%@page import="gae.map.models.TemplatesHtml"%>
<%@page import="gae.map.models.MapSources" %>
<%@page import="gae.map.models.PathSource" %>
<%@page import="gae.map.client.PathController" %>
<%@page import="javax.jdo.PersistenceManager" %>
<%@page import="gae.map.client.Persister" %>
<%@page import="gae.map.client.PMF" %>
<%@page import="gae.map.client.DataRecover" %>


<html>
<head>

    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>

<link type="text/css" rel="stylesheet" href="/static/css/project.css">
<link type="text/css" rel="stylesheet" href="/static/css/bootstrap.min.css">    
<link type="text/css" rel="stylesheet" href="/static/css/bootstrap-responsive.min.css">

<script type="text/javascript" src="/static/js/maptilesCoordinates.js"></script>
<script type="text/javascript" src="/static/js/maptilesAnimate.js"></script>
<script type="text/javascript" src="/static/js/maptilesGetPoiInfo.js"></script>
<script type="text/javascript" src="/static/js/maptilesStorePoiInfo.js"></script>
<script type="text/javascript" src="/static/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.form.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/javamap/javamap.nocache.js"></script>
 </head>
 <body>
<div class="container">
	<%= new TemplatesHtml().buildNavbar() %> <%-- aufbauen der navigationsleiste--%>
	<%= new TemplatesHtml().buildHead() %> <%-- aufbauen der Überschriften--%>
	<%  
		/*cont enthält alle pfaddaten so dass diese nur an einer stelle aktualisiert und ausgedruckt werden müssen*/
		PathController cont = new PathController(request); 
		cont.checkAndInitialize();
		cont.parsePaths();	
	%>

	
<p>	<div id="center_container">
		<div id="map" onMouseOver="load()" >
			<div id="mapsource" class="mapconfig"><%=cont.targetMap %></div>
			<div id="zoom" class="mapconfig">6</div>
			<div id="xtile" class="mapconfig">33</div>
			<div id="ytile" class="mapconfig">19</div>
			<div id="format" class="mapconfig">png</div>
			
			<div id="nw">
				<img src="<%= cont.getMaps().getWestPathList().get(0).getPath() %>"
					alt="Tile NW" style="border: 0px" width="256" height="256" />
			</div>
			<div id="ne">
				<img src="<%= cont.getMaps().getEastPathList().get(0).getPath() %>"
					alt="Tile NE" style="border: 0px" width="256" height="256" />
			</div>
			<div id="mw">
				<img src="<%= cont.getMaps().getWestPathList().get(1).getPath() %>"
					alt="Tile MW" style="border: 0px" width="256" height="256" />
			</div>
			<div id="me">
				<img src="<%= cont.getMaps().getEastPathList().get(1).getPath() %>"
					alt="Tile ME" style="border: 0px" width="256" height="256" />
			</div>
			<div id="sw">
				<img src="<%= cont.getMaps().getWestPathList().get(2).getPath() %>"
					alt="Tile SW" style="border: 0px" width="256" height="256" />
			</div>
			<div id="se">
				<img src="<%= cont.getMaps().getEastPathList().get(2).getPath() %>"
					alt="Tile SE" style="border: 0px" width="256" height="256" />
			</div>
			
		</div>
				
	<!-- Show points of interest as image links; use a loop over a template variable -->
			<div id="all_pois" class="poi" onmouseover="init_maptilesStorePoiInfo()">
		<% 	DataRecover dataRecover = new DataRecover(cont);
		
			dataRecover.checkXY(); %>
			<! schleife die pois von datenbank holt und ausdruckt>
		<%= dataRecover.printPoints(cont.xDirection,cont.yDirection)%> 
		
			
			</div>
		
		<div id="mousepos"> </div>		
		
		<div id="form_section">
			<form id="poi_form" action="/sign" method="POST">
			 	
	 		<div id="table2">
		 		<table>
			 		<tr>
			 			<th><label>Name:	 		</label></th>
			 			<td><input type="text" name="name" id="id_name" ></td>
		 			</tr>
		 			<tr>
			 			<th><label>Latitude:	 		</label></th>
			 			<td><input type="text" name="latitude" id="id_latitude" ></td>
		 			</tr>
		 			<tr>
			 			<th><label>Longitude:	 		</label></th>
			 			<td><input type="text" name="longitude" id="id_longitude" ></td>
		 			</tr>
		 			<tr>
			 			<th><label>Beschreibung:	 		</label></th>
			 			<td><input type="text" name="description" id="id_description" ></td>
			 			<td><input type="hidden" name="xCord" id="xCord">
			 			<td><input type="hidden" name="yCord" id="yCord">
		 			</tr>
		 			<tr>
		 				<th><input type="submit" value="Speichern"><th><a href="maps.jsp?id=GoogleMaps&del=delete" value="Clear All Points"></th></th>	
		 			</tr>
		 		</table>
		 	</div>
		 	</form>	

 		
			<div id="chooseMaps" style="font-size: x-large; width:635px;">
			<table>
			
			<tr>
				<ul>			<!-- Maps können über diese Links gewechselt werden -->
					<li><a href="maps.jsp?id=GoogleMaps">GoogleMaps</a></li>
					<li><a href="maps.jsp?id=Cloudmade">Cloudmade</a>	</li>
					<li><a href="maps.jsp?id=Mapnik">Mapnik</a></li>
					<li><a href="maps.jsp?id=Relief">Relief</a></li>
										<br><br><br>
				</ul>
			</tr>
			
				<tr>		<!-- Buttons für die leichtere Navigation -->
					<a href="maps.jsp?id=<%=cont.targetMap %>&xCord=0&yCord=-1">
					<img alt="up" src="/static/img/arrow_up_4.png" width="120px"></a>
	
					<a href="maps.jsp?id=<%=cont.targetMap %>&xCord=-1&yCord=0">
					<img alt="left" src="/static/img/arrow_right_4.png" width="120px"></a>
					<a href="maps.jsp?id=<%=cont.targetMap %>&xCord=0&yCord=0">
					<img alt="left" src="/static/img/view_restore.png" width="120px"></a>
					<a href="maps.jsp?id=<%=cont.targetMap %>&xCord=1&yCord=0">
					<img alt="left" src="/static/img/arrow_left_4.png" width="120px"></a>
	
	
					<a href="maps.jsp?id=<%=cont.targetMap %>&xCord=0&yCord=1">
					<img alt="up" src="/static/img/arrow_down_4.png" width="120px"></a>
				</tr>
			</table>
			</div>
 		</div>
		 	

		<div id="below">
			Kartenquelle:
			von <%= cont.getParsedPath(cont.getMaps().getWestPathList().get(0))%> bis
			<%= cont.getParsedPath(cont.getMaps().getEastPathList().get(2)) %><br />
			Kacheln im Format "<%=cont.getFileFormat(cont.getMaps().getWestPathList().get(0))%>"
		</div>
	
	</div></p>

</div>

<script>
	function load() {
		init_maptilesAnimate();
		init_maptilesStorePoiInfo();
		init_maptilesGetPoiInfo();
	}
</script>


 </body>

</html>
