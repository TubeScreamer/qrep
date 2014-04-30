package cto.lead.betterq.server.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import cto.lead.betterq.server.common.Consts;
import cto.lead.betterq.server.db.entities.Place;
import cto.lead.betterq.server.db.entities.User;

public class DBUtils 
{
	private ArrayList<User> registeredUsers;
	private ArrayList<Place> allPlaces;

	private static volatile DBUtils instance = null;
	
	private DBUtils(){	
		loadDataFromDB();
	}
	
	public static DBUtils getInstance() {
	    DBUtils r = DBUtils.instance;
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
	public synchronized void loadDataFromDB(){
		deserializeUsers();
		deserializePlaces();
	}
	
	private void deserializeUsers(){
		try{
	         FileInputStream fileIn = new FileInputStream(Consts.USERS_FILE_PATH);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         registeredUsers = (ArrayList<User>) in.readObject();
	         in.close();
	         fileIn.close();
	    }
		catch(IOException i){
	         i.printStackTrace();
	    }
		catch(ClassNotFoundException c){
	         c.printStackTrace();
	    }
	}
	
	private void deserializePlaces(){
		try{
	         FileInputStream fileIn = new FileInputStream(Consts.PLACES_FILE_PATH);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         allPlaces = (ArrayList<Place>) in.readObject();
	         in.close();
	         fileIn.close();
	    }
		catch(IOException i){
	         i.printStackTrace();
	    }
		catch(ClassNotFoundException c){
	         c.printStackTrace();
	    }
	}
	/**
	 * serialization to DB
	 */
	public synchronized void saveToDB(){
		serializeObject(Consts.USERS_FILE_PATH, registeredUsers);
		serializeObject(Consts.PLACES_FILE_PATH, allPlaces);
	}
	
	private void serializeObject(String path, Object items){
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(items);
			out.close();
			fileOut.close();
	    }
		catch(IOException i) {
			i.printStackTrace();
	    }
	}
	
	public synchronized void addUser(User u){
		registeredUsers.add(u);
	}
	
	public synchronized void addPlace(Place p){
		allPlaces.add(p);
	}
	
	public synchronized void addFavorivePlace(User u, Place p){
		u.addFavoritePlace(p);
	}
	
	public synchronized void printLists(){
		System.out.println("Users:");
		for(User u : registeredUsers){
			System.out.println(u);
		}
		
		System.out.println("Places:");
		for(Place p : allPlaces){
			System.out.println(p);
		}
	}
}

