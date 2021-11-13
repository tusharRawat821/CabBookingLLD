package com.lld.cabbooking.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lld.cabbooking.expections.NoCabsAvailableException;
import com.lld.cabbooking.expections.TripNotFoundException;
import com.lld.cabbooking.model.Cab;
import com.lld.cabbooking.model.Location;
import com.lld.cabbooking.model.Rider;
import com.lld.cabbooking.model.Trip;
import com.lld.cabbooking.strategies.CabMatchingStrategy;
import com.lld.cabbooking.strategies.PricingStrategy;


public class TripsManager {
	
	private final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;
	
	// {riderId: List<Trip> trips}
	private Map<String, List<Trip>> trips = new HashMap<>();
	
	private CabsManager cabsManager;
	private PricingStrategy pricingStrategy;
	private CabMatchingStrategy cabMatchingStrategy;
	
	public TripsManager(CabsManager cabsManager, PricingStrategy pricingStrategy,
			CabMatchingStrategy cabMatchingStrategy) {
		super();
		this.cabsManager = cabsManager;
		this.pricingStrategy = pricingStrategy;
		this.cabMatchingStrategy = cabMatchingStrategy;
	}
	
	public void createTrip(final Rider rider, Location origin, Location dest) {
		
		List<Cab> nearByCabs = cabsManager.getAvailableCabs(origin, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
		List<Cab> candidateCabs = nearByCabs.stream().filter(cab -> cab.getCurrentTrip() == null).collect(Collectors.toList());
		
		// now decide which cab is best for the user based on some strategy
		final Cab selectedCab = 
				cabMatchingStrategy.matchCabToRider(rider, candidateCabs, origin, dest);
		if(selectedCab == null) {
			throw new NoCabsAvailableException();
		}
		
		final Double fare = pricingStrategy.calculateFare(origin, dest);
		final Trip newTrip = new Trip(rider, selectedCab, origin, dest, fare);
		
		if(!trips.containsKey(rider.getId()))
			trips.put(rider.getId(), new ArrayList<>());
		trips.get(rider.getId()).add(newTrip); // so that we can return history directly
		selectedCab.setCurrentTrip(newTrip);
	}
	
	// create Map<riderId, List<Trip>>> to make this operation simple and pre-calculated.
	public List<Trip> tripHistory(Rider rider) {
		return trips.get(rider.getId());
	}
	
	public void endTrip(final Cab cab) {
		if(cab.getCurrentTrip() == null)
			throw new TripNotFoundException();
		
		cab.getCurrentTrip().endTrip();
		cab.setCurrentTrip(null);
	}
}
