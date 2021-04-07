package ActiveEntity;
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
public class AEPassenger extends Thread {
	public void run() {
        System.out.println("Hello from Passenger!");
    }
}

