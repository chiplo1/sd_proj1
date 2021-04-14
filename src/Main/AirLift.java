package Main;

import ActiveEntity.*;

import DepartureAirport.SRDepartureAirport;
import DestinationAirport.SRDestinationAirport;
import Plane.SRPlane;

import genclass.*;

public class AirLift {

	private static void startSimulation() {
		 
		int MAX_PASSENGER = 21;
		
		GenericIO.writeString("Simulation started\n");
		 
		AEPilot aePilot = new AEPilot();
		AEHostess aeHostess = new AEHostess();
		AEPassenger[] aePassenger = new AEPassenger[MAX_PASSENGER];
		 
		// start active entities: Threads
		aePilot.start();
		aeHostess.start();
		 		 
		for (Integer i = 0; i < MAX_PASSENGER;i++) {
			aePassenger[i] = new AEPassenger();
			aePassenger[i].start();
		}
		
		SRDestinationAirport destinationAirport = new SRDestinationAirport(aePilot,aeHostess,aePassenger);
		SRDepartureAirport departureAirport = new SRDepartureAirport(aePilot,aeHostess,aePassenger);
		SRPlane plane = new SRPlane(aePilot,aeHostess,aePassenger);
		
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
		
		// 1 piloto, 1 hospedeira e 21 passageiros -- diferentes threads
		// 3 classes: departure airport, destination airport, plane
		// 1 ficheiro: general repository of information (historico das alterações nas classes partilhadas) - mais 1 class
		// criar 1 e 1 só instância de cada classe
		// uma thread tem como argumento as classes que necessita

		startSimulation();
	}

}