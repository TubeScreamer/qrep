package cto.lead.betterq.uientities;

public class PlaceUI {

		 String id = null;
		 String name = null;
		 boolean selected = false;

		 public PlaceUI(String code, String name, boolean selected) {
		  super();
		  this.id = code;
		  this.name = name;
		  this.selected = selected;
		 }

		 public String getCode() {
		  return id;
		 }

		 public void setCode(String code) {
		  this.id = code;
		 }

		 public String getName() {
		  return name;
		 }

		 public void setName(String name) {
		  this.name = name;
		 }

		 public boolean isSelected() {
		  return selected;
		 }

		 public void setSelected(boolean selected) {
		  this.selected = selected;
		 }

}

	


