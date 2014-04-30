package cto.lead.betterq.server.handlers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cto.lead.betterq.server.common.Consts.MessageTypesEnum;
import cto.lead.betterq.server.common.QMessage;

/**
 * A handler thread class.  Handlers are spawned from the listening
 * loop and are responsible for a dealing with a single client
 * and broadcasting its messages.
 */
public class SingeUserHandler implements Runnable {
	private Socket clientSocket;
	
	public SingeUserHandler(Socket client) {
		this.clientSocket = client;
    }
	
    public void registerOnServer(QMessage message, ObjectOutputStream clientOutputStream){
    	// TODO implement
    }
    public void loginToServer(QMessage message, ObjectOutputStream clientOutputStream) {
    	// TODO implement
    }
    public void getStats(QMessage message, ObjectOutputStream clientOutputStream){
    	// TODO implement
    }
    public void getPlaces(QMessage message, ObjectOutputStream clientOutputStream){
    	// TODO implement
    }
    public void getFavorites(QMessage message, ObjectOutputStream clientOutputStream){
    	// TODO implement
    }
    
	@Override
	public void run() {
		try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            
			QMessage message = (QMessage)ois.readObject();
			MessageTypesEnum messageType = message.getType();
			switch(messageType){
				case Register: registerOnServer(message, oos); break;
				case Login: loginToServer(message, oos); break;
				case GetStats: getStats(message, oos); break;
				case GetPlaces: getPlaces(message, oos); break;
				case GetFavorites: getFavorites(message, oos); break;
				default: break; // TODO add more functionality in future
			}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
