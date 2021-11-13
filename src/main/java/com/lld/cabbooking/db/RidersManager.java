package com.lld.cabbooking.db;

import java.util.HashMap;
import java.util.Map;

import com.lld.cabbooking.expections.RiderAlreadyExistsException;
import com.lld.cabbooking.expections.RiderNotFoundException;
import com.lld.cabbooking.model.Rider;

import lombok.NonNull;

public class RidersManager {
	private Map<String, Rider> riders = new HashMap<>();
	
	public void createRider(@NonNull final Rider rider) {
		if(riders.containsKey(rider.getId())) {
			throw new RiderAlreadyExistsException("Rider(id= " + rider.getId() + ") " + " already exists!");
		}
		riders.put(rider.getId(), rider);
	}
	
	public Rider getRider(@NonNull final String riderId) {
		if(!riders.containsKey(riderId)) {
			throw new RiderNotFoundException("Rider(id= " + riderId + ") " + " not found!");
		}
		return riders.get(riderId);
	}
	
}
