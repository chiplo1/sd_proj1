package Main;

import ActiveEntity.*;

import DepartureAirport.SRDepartureAirport;
import DestinationAirport.SRDestinationAirport;
import Plane.SRPlane;

import genclass.*;

public class AirLift {

	private static void startSimulation(int maxPassengers) {
		 
		final int MAX_PASSENGER = maxPassengers;
		
		GenericIO.writeString("Simulation started\n");
		
		GeneralRepositoryInformation airport = new GeneralRepositoryInformation();
		
		SRDepartureAirport departureAirport = new SRDepartureAirport(airport);
		SRDestinationAirport destinationAirport = new SRDestinationAirport(airport);
		SRPlane plane = new SRPlane(airport,MAX_PASSENGER);
		 
		AEPilot aePilot = new AEPilot(0,departureAirport,destinationAirport,plane);
		AEHostess aeHostess = new AEHostess(0,departureAirport,destinationAirport,plane);
		AEPassenger[] aePassenger = new AEPassenger[MAX_PASSENGER];
		 
		// start active entities: Threads
		aePilot.start();
		aeHostess.start();
		 		 
		for (Integer i = 0; i < MAX_PASSENGER;i++) {
			aePassenger[i] = new AEPassenger(i,departureAirport,destinationAirport,plane);
			aePassenger[i].start();
		}
		
		
		
		// wait active entities to die
		try {
			aePilot.join();
			aeHostess.join();
			for (Integer i = 0; i < MAX_PASSENGER;i++)
				aePassenger[i].join();
		 } catch ( Exception ex ) {
			 // code
		 }
		 
		 GenericIO.writeString("End of Simulation");
		 
	}
	public static void main(String[] args) {

		int numPassengers;
		
		if(args.length == 1){
	           try{
	               numPassengers= Integer.parseInt(args[0]);
	           }catch(Exception e){   
	        	   numPassengers = 21;
	            }
	       }else{
	    	   numPassengers = 21;
	       }

		startSimulation(numPassengers);
	}

}