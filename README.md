# Low Level Design of a Cab Booking Application - like OLA/UBER/LYFT etc.


# Cab Booking Application

### Problem Statement:
We want to build a cab booking platform to allow a rider to book a cab.

### Details:
* The location is represented as a (x, y) coordinate.
* Distance between two points (x1, y1) and(x2, y2) is sqrt((x1-x2)^2 + (y1-y2)^2)
* Platform has decided upon maximum distance a driver has to travel to pickup a rider.
* A cab has only 1 driver.
* Sharing of cab is not allowed between riders
* There is a single type of cab

Please build an application that exposes following features to riders and drivers.
* Register a rider.
* Register a driver/cab
* Update a cab's location
* A driver can switch on/off his availability
* A rider can book a cab
* Fetch history of all rides taken by a rider.
* End the Trip


### Expectation from this round
* Demonstrable code is first expectation. To do this, you can choose any interface you
are comfortable with - CLI, using unit tests or even simply run the code via a main method.

* Code should be extensible.
* Clean professional level code.
* Functional Completeness including good modelling.
* User Identification but not authentication.
* Backend Database is optional. However modelling should be complete.




#### Approach 

* Write clean, modular, extensible and maintainable code.
* Follow OOPs.
* Write code keeping SOLID principles in mind.
* Try to see if you can use any Design Pattern specific to the problem statement.


Credits :

<a href = "https://github.com/anomaly2104/lld-cab-booking-ola-uber-grab-lyft">Udit Agarwal [YT]</a>