package com.lld.cabbooking.strategies;

import com.lld.cabbooking.model.Location;

public class DefaultPricingStrategy implements PricingStrategy {
	
	public static final Double PER_KM_RATE = 10.0;
	
	/**
	 * find any cab having no currentTrip.
	 * this is an extensible design as if in future, 
	 * we want to show different pricing for premium users
	 * we can just design a different strategy based on 
	 * type of user (premium/general) and plug it in TripsManager.
	 */
	@Override
	public Double calculateFare(Location origin, Location dest) {
		return origin.distance(dest) * PER_KM_RATE;
	}
	
}
