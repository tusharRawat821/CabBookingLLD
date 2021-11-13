package com.lld.cabbooking.controller;

import com.lld.cabbooking.db.CabsManager;
import com.lld.cabbooking.db.TripsManager;
import com.lld.cabbooking.model.Cab;
import com.lld.cabbooking.model.Location;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CabsController {
	
	private CabsManager cabsManager;
	private TripsManager tripsManager;
	
	/**
	 * APIs exposed to the Driver/Cab in the system.
	 */
	
	public void registerCab(final String cabId, final String driverName) {
		cabsManager.createCab(new Cab(cabId, driverName));
	}
	
	public void updateCabLocation(final String cabId, final Double newX, final Double newY) {
		cabsManager.updateCabLocation(cabId, new Location(newX, newY));
	}
	
	public void updateCabAvailability(final String cabId, boolean newAvailabliltiy) {
		cabsManager.updateCabAvailability(cabId, newAvailabliltiy);
	}
	
	public void endTrip(final String cabId) {
		tripsManager.endTrip(cabsManager.getCab(cabId));
	}
	
}
