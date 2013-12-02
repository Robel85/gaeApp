package gae.map.models;

public class TemplatesHtml {
	StringBuilder builder;
	
	public TemplatesHtml(){
		
	}

	public String buildNavbar(){
		
		builder = new StringBuilder();
		
		builder.append("<div class='navbar navbar-inverse navbar-fixed-top'>");
		builder.append("<div class='navbar-inner'>");
		builder.append("<div class='container'>");
		builder.append("<a class='btn btn-navbar' data-toggle='collapse' data-target='.nav-collapse'>");
		builder.append("<span class='icon-bar'></span><span class='icon-bar'></span><span class='icon-bar'></span></a>");
		builder.append("<a class='brand' href='maps.jsp'>Map Application</a>");
		builder.append("<div class='nav-collapse collapse'>");
		builder.append("<ul class='nav'>");
		builder.append("<li class='active'><a href='maps.jsp'>Home</a></li>");
		builder.append("<li><a href='#about'>About</a></li>");
		builder.append("<li><a href='#contact'>Contact</a></li></ul>");
		builder.append("</div></div></div></div>");

		return builder.toString();
	}

	public String buildHead(){
		
		builder = new StringBuilder();
		
		builder.append("<h1>Web Application Map Project</h1>");
		
		return builder.toString();
	}

}
