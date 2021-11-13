package com.lld.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Location {
	
	private Double x;
	private Double y;
	
	public Double distance(Location location2) {
		return Math.sqrt(Math.pow(this.x - location2.getX(), 2) + Math.pow(this.y - location2.getY(), 2));
	}
	
}
