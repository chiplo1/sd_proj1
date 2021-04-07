package ActiveEntity;
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
public class AEHostess extends Thread {
	public void run() {
        System.out.println("Hello from Hostess!");
    }
}
