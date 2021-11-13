package com.lld.cabbooking.model;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Trip {
	
	private Rider rider;
	private Cab cab;
	private Location origin;
	private Location dest;
	private Double fare;
	private LocalDate date;
	
	private TripStatus status;
	
	public Trip(final Rider rider, final Cab cab, final Location origin, final Location dest, final Double fare) {
		this.rider = rider;
		this.cab = cab;
		this.origin = origin;
		this.dest = dest;
		this.fare = fare;
		
		this.date = LocalDate.now();
		this.status = TripStatus.ACTIVE; 
	}
	
	public void endTrip() {
		this.status = TripStatus.COMPLETED;
		System.out.println(this + " ends.");
	}

}
