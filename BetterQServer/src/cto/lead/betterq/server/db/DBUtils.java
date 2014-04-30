package cto.lead.betterq.server.db;

import java.util.ArrayList;

import cto.lead.betterq.server.db.entities.Place;
import cto.lead.betterq.server.db.entities.User;

public class DBUtils 
{
	private static ArrayList<User> registeredUsers;
	private static ArrayList<Place> allPlaces;

	private volatile DBUtils instance = null;
	
	private DBUtils(){	
		DBUtils.loadDataFromDB();
	}
	
	public DBUtils getInstance() {
	    DBUtils r = instance;
	    if(r == null) {
	        synchronized(DBUtils.class) {    // while we were waiting for the lock, another 
	            r = instance;       // thread may have instantiated the object
	            if(r == null) {  
	                r = new DBUtils();
	                instance = r;
	            }
	        }
	    }
	    return r;
	}
	
	/**
	 * unserializes the data into internal data structures
	 */
	public static void loadDataFromDB(){
		
	}
	
	/**
	 * serialization to DB
	 */
	public static void saveToDB(){
		
	}
	
	public static synchronized void addUser(User u){
		DBUtils.registeredUsers.add(u);
	}
	
	public static synchronized void addPlace(Place p){
		DBUtils.addPlace(p);
	}
	
	public static synchronized void addFavorivePlace(User u, Place p){
		u.addFavoritePlace(p);
	}
}

