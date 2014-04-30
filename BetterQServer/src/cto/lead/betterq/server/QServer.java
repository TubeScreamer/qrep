/*
 * This is a simple server application
 * This server receive a string message from the Android mobile phone
 * and show it on the console.
 */
package cto.lead.betterq.server;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
 
public class QServer 
{ 
    private String message;
 
    public static void main(String[] args) 
    {
    	QServer server = new QServer();
    	
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        
        try {
            serverSocket = //new ServerSocket(4444);  //Server socket
//            new ServerSocket(9090, 0, InetAddress.getByName("10.0.2.2"));
            		new ServerSocket(9090);
//            InetAddress adr = serverSocket.getInetAddress();
//            SocketAddress sa = serverSocket.getLocalSocketAddress();

	        System.out.println("Server started. Listening to the port " + serverSocket.getInetAddress() + ":" + serverSocket.getLocalPort());
	 
	        while (true) {
	            try {
	 
	                clientSocket = serverSocket.accept();   //accept the client connection
	                inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
	                bufferedReader = new BufferedReader(inputStreamReader); //get the client message
	                server.setMessage(bufferedReader.readLine());
	 
	                System.out.println(server.getMessage());
	                inputStreamReader.close();
	                clientSocket.close();
	 
	            } catch (IOException ex) {
	                System.out.println("Problem in message reading");
	            }
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}