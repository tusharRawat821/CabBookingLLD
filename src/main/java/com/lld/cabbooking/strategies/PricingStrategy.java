package com.lld.cabbooking.strategies;

import com.lld.cabbooking.model.Location;

public interface PricingStrategy {
	public Double calculateFare(Location origin, Location dest);
}
