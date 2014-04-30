package cto.lead.betterq.server.db.entities;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class User implements Serializable {
	
	private String username;
	private String password;
	private int points = 0;
	private int level = 1;
	
	private ArrayList<Place> favorites;
	
	public User(){
		this.username = "";
		this.password = "";
		favorites = new ArrayList<Place>();
	}
	
	public User(String username, String password, int points, int level) {
		this.username = username;
		this.password = password;
		this.points = points;
		this.level = level;
		favorites = new ArrayList<Place>();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void addFavoritePlace(Place favorite){
		this.favorites.add(favorite);
	}
}
