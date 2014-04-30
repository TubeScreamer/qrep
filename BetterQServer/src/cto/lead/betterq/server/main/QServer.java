/*
 * This is a simple server application
 * This server receive a string message from the Android mobile phone
 * and show it on the console.
 */
package cto.lead.betterq.server.main;
 
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cto.lead.betterq.server.common.Consts;
import cto.lead.betterq.server.handlers.QSheduledJob;
import cto.lead.betterq.server.handlers.SingeUserHandler;
 
public class QServer 
{ 
	public QServer() {
    }
    	
    public  void startScheduledJob(QSheduledJob job){
    	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    	scheduler.scheduleAtFixedRate(job, 0, Consts.SCHEDULED_JOB_TIMESTAMP, TimeUnit.MINUTES);
    }
    
 
    public void startListenToClientConnections() {
    	ServerSocket serverSocket = null;
    	try {
            serverSocket = new ServerSocket(Consts.SERVER_PORT);  //Server socket
	        System.out.println("Server started. Listening to the port " + 
	        		serverSocket.getInetAddress() + ":" + serverSocket.getLocalPort());
	 
	        while (true) {
	        	new SingeUserHandler(serverSocket.accept()).run();
	        }
        } catch (IOException e) {
            System.out.println("Could not listen on port: 9090");
        }
        finally{
        	try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }


	/**
     * The appplication main method, which just listens on a port and spawns handler threads.
     */
    public static void main(String[] args){
        QServer server = new QServer();
        
        // 1. start the scheduled job
        server.startScheduledJob(new QSheduledJob()); // asynchronous
        
        // 2. start receiving client requests
        server.startListenToClientConnections(); // synchronous task
    }
}