import java.util.HashMap;

public class Airport {
	private String _name;
	public Airport(String name) {
		this._name = name;
	}
	
	public String getName() {
		return _name;
	}
	public HashMap<String, FlightRoute> routes = new HashMap<String, FlightRoute>();
}
