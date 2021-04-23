package DepartureAirport;

public interface IDepartureAirport_Hostess {
	public void prepareForPassBoarding();
	public void checkDocuments();
	public boolean waitForNextPassenger();
	public void informPlaneReadyToTakeOff();
	public void waitForNextFlight();
}
