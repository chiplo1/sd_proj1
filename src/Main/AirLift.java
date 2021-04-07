package Main;

import ActiveEntity.*;

public class AirLift {

	private void startSimulation() {
		
		 GenericIO.writeString("Simulation started");
		 
		 // start active entities: Threads
		 aePilot.start();
		 aeHostess.start();
		 for (Integer i = 0; i < MAX_PASSENGER;i++)
			 aePassenger[i].start();
		 
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

		/*
		Plane plane = new Plane();
		DepartureAirport dep_airport = new DepartureAirport();
		DestinationAirport dest_airport = new DestinationAirport();

		int n = 21; // Number of passengers
        for (int i = 0; i < n; i++) {
            Passenger passenger = new Passenger();
            passenger.start();
        }
        Pilot pilot = new Pilot();
        pilot.start();
        Hostess hostess = new Hostess();
        hostess.start();
        */
	}

}

/* Pilot
AT_TRANSFER_GATE – transition state (initial / final state)
READY_FOR_BOARDING – transition state
WAIT_FOR_BOARDING – blocking state
The pilot is waken up by the operation informPlaneReadyToTakeOff of the hostess when
boarding is complete.
FLYING_FORWARD – independent state with blocking
The pilot should be made to sleep for a random time interval in the simulation.
DEBOARDING – blocking state
The pilot is waken up by the operation leaveThePlane of the last passenger to leave the plane.
FLYING_BACK – independent state with blocking
The pilot should be made to sleep for a random time interval in the simulation.
 */
class Pilot extends Thread {
    public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println(
                "Thread " + Thread.currentThread().getId()
                + " is running, pilot.");
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}

/* Hostess
WAIT_FOR_NEXT_FLIGHT – blocking state (initial / final state)
The hostess is waken up by the operation informPlaneReadyForBoarding of the pilot after
parking the plane at the departure gate.
WAIT_FOR_PASSENGER – blocking state
The hostess is waken up by the operation waitInQueue of the passenger while he / she waits
to have his / her documents checked.
CHECK_PASSENGER – blocking state
The hostess is waken up by the operation showDocuments of the passenger when he / she
hands his / her plane ticket to be checked.
READY_TO_FLY – transition state
*/
class Hostess extends Thread {
    public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println(
                "Thread " + Thread.currentThread().getId()
                + " is running, hostess.");
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}

/* Passenger
GOING_TO_AIRPORT – independent state with blocking (initial state)
The passenger should be made to sleep for a random time interval in the simulation.
IN_QUEUE – double blocking state
The passenger is waken up first by the operation checkDocuments of the hostess requesting him/ her
to present the plane ticket and is waken up next by the operation waitForNextPassenger of the
hostess after the checking is being made.
IN_FLIGHT – blocking state
The passenger is waken up by the operation announceArrival of the pilot after parking the plane at
the arrival gate.
AT_DESTINATION – transition state (final state)
*/
class Passenger extends Thread {
    public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println(
                "Thread " + Thread.currentThread().getId()
                + " is running, passenger.");
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}