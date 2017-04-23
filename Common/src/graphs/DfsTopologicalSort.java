package graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DfsTopologicalSort<Tkey, Tvalue> implements TopologicalSort<Tkey, Tvalue> {

	private enum VisitingState {
		Undefined,
		Visiting,
		Visited
	}

	@Override
	public List<Tkey> sort(Graph<Tkey, Tvalue> graph) throws CycleDetectedException {
		if (graph == null) {
			throw new IllegalArgumentException("grpah");
		}
		
		LinkedList<Tkey> sorted = new LinkedList<Tkey>();
		HashMap<Tkey, VisitingState> states = new HashMap<Tkey, VisitingState>();
		
		for(Node<Tkey, Tvalue> node : graph.getNodes()) {
			this.sort(node, sorted, states);
		}
		
		return sorted;
	}
	
	public void sort(Node<Tkey, Tvalue> node, LinkedList<Tkey> sorted, HashMap<Tkey, VisitingState> states) throws CycleDetectedException {
		
		Tkey nodeKey = node.getKey();
		VisitingState visitingState = states.get(nodeKey);
		
		if (visitingState == VisitingState.Visiting) {
			throw new CycleDetectedException();
		}
		
		if (visitingState == VisitingState.Visited) {
			return;
		}
		
		states.put(nodeKey, VisitingState.Visiting);
		
		for(Node<Tkey, Tvalue> connection : node.getConnections()) {
			sort(connection, sorted, states);
		}
		
		states.put(nodeKey, VisitingState.Visited);
		
		sorted.add(nodeKey);
	}

}
