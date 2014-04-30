package cto.lead.betterq.server.db.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Place implements Serializable {
	private int id;
	private String name;
	private String address;
	private int liveLoad;
	
	public Place(){
		
	}
	
	public Place(int id, String name, String address, int liveLoad) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.liveLoad = liveLoad;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getLiveLoad() {
		return liveLoad;
	}
	public void setLiveLoad(int liveLoad) {
		this.liveLoad = liveLoad;
	}
	
	@Override
	public String toString() {
		return "id: " + this.id + " | name: " + this.name + " | address: " + this.address + " | liveLoad " + this.liveLoad;
	}
}
