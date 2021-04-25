package Main;


import ActiveEntity.*;

import DepartureAirport.SRDepartureAirport;
import DestinationAirport.SRDestinationAirport;
import Plane.SRPlane;

import genclass.*;
import utils.BlockingQueue;

public class AirLift {

	private static void startSimulation(int totalPassengers) {
		 
		final int TOTAL_PASSENGERS = totalPassengers;
		final int MAX_PASSENGERS = 10;
		final int MIN_PASSENGERS = 5;
				
		GenericIO.writeString("Simulation started\n");
		
		GeneralRepositoryInformation airport = new GeneralRepositoryInformation();
		
		BlockingQueue<Integer> plane = new BlockingQueue<>(TOTAL_PASSENGERS);
		
		SRDepartureAirport departureAirportSR = new SRDepartureAirport(airport, plane, TOTAL_PASSENGERS, MIN_PASSENGERS, MAX_PASSENGERS);
		SRDestinationAirport destinationAirportSR = new SRDestinationAirport(airport, plane, TOTAL_PASSENGERS);
		SRPlane planeSR = new SRPlane(airport, plane);
		 
		AEPilot aePilot = new AEPilot(0,departureAirportSR,destinationAirportSR,planeSR);
		AEHostess aeHostess = new AEHostess(0,departureAirportSR,destinationAirportSR,planeSR);
		AEPassenger[] aePassenger = new AEPassenger[TOTAL_PASSENGERS];
		 
		// start active entities: Threads
		aePilot.start();
		aeHostess.start();
		 		 
		for (Integer i = 0; i < TOTAL_PASSENGERS;i++) {
			aePassenger[i] = new AEPassenger(i,departureAirportSR,destinationAirportSR,planeSR);
			aePassenger[i].start();
		}
		
		
		
		// wait active entities to die
		try {
			aePilot.join();
			aeHostess.join();
			for (Integer i = 0; i < TOTAL_PASSENGERS;i++)
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