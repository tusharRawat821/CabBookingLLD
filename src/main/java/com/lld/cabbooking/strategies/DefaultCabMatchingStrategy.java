package com.lld.cabbooking.strategies;

import java.util.List;

import com.lld.cabbooking.model.Cab;
import com.lld.cabbooking.model.Location;
import com.lld.cabbooking.model.Rider;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy {
	
	/**
	 * find any cab having no currentTrip.
	 * this is an extensible design as if in future, 
	 * we want to show on-trip cabs also OR
	 * we want to select cab having best rating
	 * we can just design a different strategy based on 
	 * capacity of the cab and plug it in TripsManager.
	 */
	@Override
	public Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location origin, Location dest) {
		if(candidateCabs.size() == 0) 
			return null;
		return candidateCabs.get(0);
	}
	
}
