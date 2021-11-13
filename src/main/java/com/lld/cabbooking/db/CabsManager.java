package com.lld.cabbooking.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lld.cabbooking.expections.CabAlreadyExistsException;
import com.lld.cabbooking.expections.CabNotFoundException;
import com.lld.cabbooking.model.Cab;
import com.lld.cabbooking.model.Location;

import lombok.NonNull;

public class CabsManager {
	
	/**
	 * In-memory DB 
	 */
	private Map<String, Cab> cabs = new HashMap<>();
	
	public void createCab(@NonNull final Cab newCab) {
		if(cabs.containsKey(newCab.getId())) {
			throw new CabAlreadyExistsException(newCab.getId() + " already exists in the system.");
		}
		
		cabs.put(newCab.getId(), newCab);
	}
	
	public Cab getCab(final String cabId) {
		if(!cabs.containsKey(cabId)) {
			throw new CabNotFoundException("Cab(id=)" + cabId + " not found!");
		}
		
		return cabs.get(cabId);
	}
	
	public void updateCabLocation(final String cabId, final Location newLocation) {
		if(!cabs.containsKey(cabId)) {
			throw new CabNotFoundException("Cab(id=)" + cabId + " not found!");
		}
		cabs.get(cabId).setCurrentLocation(newLocation);
	}
	
	/**
	 * if newAvailability == false, means cab is out of service 
	 * else cab is in service. But this doesn't determines whether the Cab is on-trip or not.
	 * @param cabId
	 * @param newAvailability
	 */
	public void updateCabAvailability(final String cabId, final boolean newAvailability) {
		if(!cabs.containsKey(cabId)) {
			throw new CabNotFoundException("Cab(id=)" + cabId + " not found!");
		}
		cabs.get(cabId).setAvailable(newAvailability);
	}
	
	
	/**
	 * helper method to get all available cabs in the certain radius.
	 */
	public List<Cab> getAvailableCabs(final Location fromPoint, final Double distance) {
		List<Cab> availableCabs = new ArrayList<>();
		
		for(Cab cab: cabs.values()) {
			if(cab.isAvailable() && cab.getCurrentLocation().distance(fromPoint) <= distance) {
				availableCabs.add(cab);
			}
		}
		
		return availableCabs;
	}
	
}
