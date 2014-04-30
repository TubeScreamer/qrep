package cto.lead.betterq.server.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cto.lead.betterq.server.common.Consts.MessageTypesEnum;

/**
 * Message class for client-server communication 
 * @author Nikita
 */

@SuppressWarnings("serial")
public class QMessage implements Serializable
{
	private MessageTypesEnum type;
	private ArrayList<Object> contents;
	private String clientId;
	
	public QMessage(){}
	
	public QMessage(MessageTypesEnum type, ArrayList<Object> contents, String clientId) {
		this.clientId = clientId;
		this.type = type;
		setContents(contents);
	}

	public MessageTypesEnum getType() {
		return type;
	}

	public void setType(MessageTypesEnum type) {
		this.type = type;
	}

	public List<Object> getContents() {
		return this.contents;
	}

	public void setContents(List<Object> contents) {
		this.contents = new ArrayList<Object>();
		for(Object o : contents){
			this.contents.add(o);
		}
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}	
}
