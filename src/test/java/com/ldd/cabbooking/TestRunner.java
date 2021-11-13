package com.ldd.cabbooking;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lld.cabbooking.controller.CabsController;
import com.lld.cabbooking.controller.RidersController;
import com.lld.cabbooking.db.CabsManager;
import com.lld.cabbooking.db.RidersManager;
import com.lld.cabbooking.db.TripsManager;
import com.lld.cabbooking.expections.CabAlreadyExistsException;
import com.lld.cabbooking.expections.CabNotFoundException;
import com.lld.cabbooking.expections.NoCabsAvailableException;
import com.lld.cabbooking.expections.RiderAlreadyExistsException;
import com.lld.cabbooking.expections.RiderNotFoundException;
import com.lld.cabbooking.strategies.CabMatchingStrategy;
import com.lld.cabbooking.strategies.DefaultCabMatchingStrategy;
import com.lld.cabbooking.strategies.DefaultPricingStrategy;
import com.lld.cabbooking.strategies.PricingStrategy;


class TestRunner {
	
	CabsController cabsController;
	RidersController ridersController;
	
	@BeforeEach
	void setUp() {
		CabsManager cabsManager = new CabsManager();
		RidersManager ridersManager = new RidersManager();
		CabMatchingStrategy cabMatchingStrategy = new DefaultCabMatchingStrategy();
		PricingStrategy pricingStrategy = new DefaultPricingStrategy();
		
		
		TripsManager tripsManager = new TripsManager(cabsManager, pricingStrategy, cabMatchingStrategy);
		
		cabsController = new CabsController(cabsManager, tripsManager);
		ridersController = new RidersController(ridersManager, tripsManager);
	}
	
	@Test
	void test() {
		// registering riders
		String r1 = "r1";
	    ridersController.registerRider(r1, "ud");
	    String r2 = "r2";
	    ridersController.registerRider(r2, "du");
	    String r3 = "r3";
	    ridersController.registerRider(r3, "rider3");
	    String r4 = "r4";
	    ridersController.registerRider(r4, "rider4");

	    // registering cabs
	    String c1 = "c1";
	    cabsController.registerCab(c1, "driver1");
	    String c2 = "c2";
	    cabsController.registerCab(c2, "driver2");
	    String c3 = "c3";
	    cabsController.registerCab(c3, "driver3");
	    String c4 = "c4";
	    cabsController.registerCab(c4, "driver4");
	    String c5 = "c5";
	    cabsController.registerCab(c5, "driver5");
	    
	    // set cabs location
	    cabsController.updateCabLocation(c1, 1.0, 1.0);
	    cabsController.updateCabLocation(c2, 2.0, 2.0); //na
	    cabsController.updateCabLocation(c3, 100.0, 100.0);
	    cabsController.updateCabLocation(c4, 110.0, 110.0); //na
	    cabsController.updateCabLocation(c5, 4.0, 4.0);

	    // which cabs are available
	    cabsController.updateCabAvailability(c2, false);
	    cabsController.updateCabAvailability(c4, false);

	    // Requesting trips
	    ridersController.book(r1, 0.0, 0.0, 500.0, 500.0);
	    ridersController.book(r2, 0.0, 0.0, 500.0, 500.0);

	    // trips status
	    System.out.println("\n### Printing current trips for r1 and r2");
	    System.out.println(ridersController.fetchHistory(r1));
	    System.out.println(ridersController.fetchHistory(r2));

	    cabsController.updateCabLocation(c5, 50.0, 50.0);

	    System.out.println("\n### Printing current trips for r1 and r2");
	    System.out.println(ridersController.fetchHistory(r1));
	    System.out.println(ridersController.fetchHistory(r2));
	    
	    System.out.println("\n---- Trip status ----");
	    
	    cabsController.endTrip(c5);
	    
	    System.out.println("---- Trip status ----");
	    
	    
	    System.out.println("\n### Printing current trips for r1 and r2");
	    System.out.println(ridersController.fetchHistory(r1));
	    System.out.println(ridersController.fetchHistory(r2));
	    
	    
	    assertThrows(NoCabsAvailableException.class, () -> {
	      ridersController.book(r3, 0.0, 0.0, 500.0, 500.0);
	    });
	    

	    ridersController.book(r4, 48.0, 48.0, 500.0, 500.0);
	    System.out.println("\n### Printing current trips for r1, r2 and r4");
	    System.out.println(ridersController.fetchHistory(r1));
	    System.out.println(ridersController.fetchHistory(r2));
	    System.out.println(ridersController.fetchHistory(r4));

	    assertThrows(RiderNotFoundException.class, () -> {
	      ridersController.book("abcd", 0.0, 0.0, 500.0, 500.0);
	    });

	    assertThrows(RiderAlreadyExistsException.class, () -> {
	      ridersController.registerRider("r1", "shjgf");
	    });

	    assertThrows(CabAlreadyExistsException.class, () -> {
	      cabsController.registerCab("c1", "skjhsfkj");
	    });

	    assertThrows(CabNotFoundException.class, () -> {
	      cabsController.updateCabLocation("shss", 110.0, 110.0);
	    });

	    assertThrows(CabNotFoundException.class, () -> {
	      cabsController.updateCabAvailability("shss", false);
	    });
	}

}
