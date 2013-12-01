package gae;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class Bean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String test;
	 String pfad="static/img/GoogleMaps/6/33/19.png.tile";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	

}
