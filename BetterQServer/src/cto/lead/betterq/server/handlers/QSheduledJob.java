package cto.lead.betterq.server.handlers;

import java.util.Date;

/**
 * Scheduled job to automatic DB update
 * @author Nikita
 *
 */
public class QSheduledJob implements Runnable
{
	@Override
	public void run() {
		// TODO Update DB
		
		System.out.println("QScheduledJob:" + new Date());
	}
}
