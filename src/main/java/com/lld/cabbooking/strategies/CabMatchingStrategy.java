package com.lld.cabbooking.strategies;

import java.util.List;

import com.lld.cabbooking.model.Cab;
import com.lld.cabbooking.model.Location;
import com.lld.cabbooking.model.Rider;

public interface CabMatchingStrategy {
	public Cab matchCabToRider(Rider rider, List<Cab> closeByCabs, Location origin, Location dest);
}
