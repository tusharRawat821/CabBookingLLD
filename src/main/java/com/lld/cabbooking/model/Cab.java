package com.lld.cabbooking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
public class Cab {
	
	private String id;
	private String driverName;
	
	@Setter private Trip currentTrip;  	// if cab is on an active trip.
	@Setter private Location currentLocation;
	@Setter private boolean available;
	
	public Cab(final String id, final String driverName) {
		this.id = id;
		this.driverName = driverName;
		this.available = true;		// by default cab is available as soon as they gets created/registered in the system
	}
	
	@Override
	public String toString() {
		return "Cab{" +
				"id= " + id + 
				", driverName= " + driverName +
				", currentLocation= " + currentLocation +
				", isAvailable= " + available + 
				"}";
	}
}
