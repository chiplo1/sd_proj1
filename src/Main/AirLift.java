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
		
		BlockingQueue<Integer> queue = new BlockingQueue<>(totalPassengers);
		
		GenericIO.writeString("Simulation started\n");
		
		GeneralRepositoryInformation airport = new GeneralRepositoryInformation();
		
		SRDepartureAirport departureAirport = new SRDepartureAirport(airport, queue);
		SRDestinationAirport destinationAirport = new SRDestinationAirport(airport,TOTAL_PASSENGERS);
		SRPlane plane = new SRPlane(airport,MIN_PASSENGERS,MAX_PASSENGERS);
		 
		AEPilot aePilot = new AEPilot(0,departureAirport,destinationAirport,plane);
		AEHostess aeHostess = new AEHostess(0,departureAirport,destinationAirport,plane);
		AEPassenger[] aePassenger = new AEPassenger[TOTAL_PASSENGERS];
		 
		// start active entities: Threads
		aePilot.start();
		aeHostess.start();
		 		 
		for (Integer i = 0; i < TOTAL_PASSENGERS;i++) {
			aePassenger[i] = new AEPassenger(i,departureAirport,destinationAirport,plane);
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