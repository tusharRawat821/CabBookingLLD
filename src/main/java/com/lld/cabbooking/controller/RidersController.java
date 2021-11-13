package com.lld.cabbooking.controller;

import java.util.List;

import com.lld.cabbooking.db.RidersManager;
import com.lld.cabbooking.db.TripsManager;
import com.lld.cabbooking.model.Cab;
import com.lld.cabbooking.model.Location;
import com.lld.cabbooking.model.Rider;
import com.lld.cabbooking.model.Trip;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RidersController {
	
	private RidersManager ridersManager;
	private TripsManager tripsManager;
	
	/**
	 * APIs exposed to the Riders in the system.
	 */
	public void registerRider(final String riderId, final String riderName) {
		ridersManager.createRider(new Rider(riderId, riderName));
	}
	
	public void book(final String riderId, final Double originX, 
			final Double originY, final Double destX, final Double destY) {
		tripsManager.createTrip(ridersManager.getRider(riderId), new Location(originX, originY), new Location(destX, destY));
	}
	
	public List<Trip> fetchHistory(final String riderId) {
		return tripsManager.tripHistory(ridersManager.getRider(riderId));
	}
	
}
