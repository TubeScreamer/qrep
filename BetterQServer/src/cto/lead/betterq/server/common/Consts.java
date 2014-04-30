package cto.lead.betterq.server.common;

public class Consts 
{
	public static final String SERVER_IP = "10.0.0.2";
	public static final int SERVER_PORT = 9090;
	
	public static final int SCHEDULED_JOB_TIMESTAMP = 10; // 10 minutes 
	
	public enum MessageTypesEnum {
		Register, Login, GetStats, GetPlaces, GetFavorites // will be more types 
	}
	
	/**
	 * Server events to users
	 */
	public enum EventEnum {
		LoadChangeNotification, WorkingHoursChangeNotification, NoEvent
	}
}