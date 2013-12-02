package gae;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@PersistenceCapable
public class Persister {
    @PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String name;

    @Persistent
    private User author;
    
    @Persistent
    private String description;

    @Persistent
    private String latitude;
    
    @Persistent
    private String longitude;
    
    @Persistent
    private String xCord;
    
    @Persistent
    private String yCord;

    public Persister(String name, User author, String description, String latitude, String longitude, String xCord, String yCord) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.xCord = xCord;
        this.yCord = yCord;
    }

	public Key getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public User getAuthor() {
		return author;
	}

	public String getDescription() {
		return description;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getxCord() {
		return xCord;
	}

	public String getyCord() {
		return yCord;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setxCord(String xCord) {
		this.xCord = xCord;
	}

	public void setyCord(String yCord) {
		this.yCord = yCord;
	}

   
}