import java.util.LinkedList;

import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		LinkedList<FlightRoute> routes = new LinkedList<FlightRoute>();
		routes.add(new FlightRoute("sea", "sfo", 2));
		routes.add(new FlightRoute("sea", "la", 3));
		routes.add(new FlightRoute("sea", "vancouver", 1));
		routes.add(new FlightRoute("sea", "chicago", 100));
		routes.add(new FlightRoute("sfo", "la", 1));
		routes.add(new FlightRoute("la", "phoenix", 2));
		routes.add(new FlightRoute("phoenix", "chicago", 3));
		routes.add(new FlightRoute("sfo", "chicago",5));
		routes.add(new FlightRoute("la", "chicago",1));
		List<String> route = new ShortestPathService().getShortestPath("sea", "chicago", routes);
		for(String s : route) {
			System.out.println(s);
		}
		
	}

}
