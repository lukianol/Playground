import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ShortestPathService {
	public List<String> getShortestPath(String start, String end, List<FlightRoute> routes) throws Exception {
		HashMap<String, Airport> airports = this.buildAirports(routes);
		Airport startAirport = airports.get(start);
		
		if (startAirport == null) {
			throw new Exception("no flights from here");
		}
		
		HashMap<String, Integer> distances = new HashMap<String, Integer>();
		HashSet<String> unvisited = new HashSet<String>();
		HashMap<String, String> shortest = new HashMap<String, String>();
		
		for(Airport airport : airports.values()) {
			distances.put(airport.getName(), Integer.MAX_VALUE);
			unvisited.add(airport.getName());
		}
		
		distances.put(startAirport.getName(), 0);
		do {
			String smallestAirport = getSmallestDistanceAirportName(distances, unvisited);
			if (distances.get(smallestAirport)== Integer.MAX_VALUE ){
				break;
			}
			calculateDistances(airports.get(smallestAirport), unvisited, distances, shortest);
			if (smallestAirport == end) {
				break;
			}
		} while(true);
		
		LinkedList<String> route = new LinkedList<String>();
		String previos = end;
		while(previos != null) {
			route.add(previos);
			previos = shortest.get(previos);
		}		
		Collections.reverse(route);
		return route;
	}
	
	private String getSmallestDistanceAirportName(HashMap<String, Integer> distances, HashSet<String> unvisited){
		int min = Integer.MAX_VALUE;
		String airport = null;
		for(String key : unvisited) {
			int distance = distances.get(key);
			if (min >= distance) {
				airport = key;
				min = distance;
			}
		}
		return airport;
	}
	
	private void calculateDistances(Airport airport, HashSet<String> unvisited, HashMap<String, Integer> distances, HashMap<String, String> shortest){
		for(FlightRoute tos: airport.routes.values()) {
			String toAirport = tos.to;
			if (!unvisited.contains(toAirport)){
				continue;
			}
			
			int tentativeDistance = distances.get(airport.getName()) + tos.time;
			if (distances.get(toAirport) > tentativeDistance) {
				distances.put(toAirport, tentativeDistance);
				shortest.put(toAirport, airport.getName());
			}			
		}
		unvisited.remove(airport.getName());
	}
	
	private HashMap<String, Airport> buildAirports(List<FlightRoute> routes){
		HashMap<String, Airport> airports = new HashMap<String, Airport>();
		
		for(FlightRoute route: routes) {
			Airport airport;
			if (!airports.containsKey(route.from)) {
				airport = new Airport(route.from);
				airports.put(route.from, airport);
			} else {
				airport = airports.get(route.from);
			}	
			airport.routes.put(route.to, route);
			if (!airports.containsKey(route.to)) {
				airport = new Airport(route.to);
				airports.put(route.to, airport);
			}
			
		}
		
		return airports; 
	}
}
