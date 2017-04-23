package graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class KahnTopologicalSort<Tkey, Tvalue> implements TopologicalSort<Tkey, Tvalue> {

	@Override
	public List<Tkey> sort(Graph<Tkey, Tvalue> graph) throws CycleDetectedException {
		
		if (graph == null) {
			throw new IllegalArgumentException();
		}
		
		LinkedList<Tkey> sorted = new LinkedList<Tkey>();
		LinkedList<Node<Tkey, Tvalue>> process = new LinkedList<Node<Tkey, Tvalue>>();
		HashMap<Tkey, Integer> dependencies = this.countDependencies(graph, process);
		
		while(!process.isEmpty()) {
			Node<Tkey, Tvalue> nodeToProcess = process.remove();
			sorted.add(nodeToProcess.getKey());
			for(Node<Tkey, Tvalue> node : nodeToProcess.getConnections()){
				Tkey key = node.getKey();
				int counter = dependencies.get(key);
				dependencies.put(key, counter - 1);
				if (counter == 1) {
					process.add(node);
				}
			}
		}
		
		for(int counter : dependencies.values()) {
			if (counter > 0) {
				throw new CycleDetectedException();
			}
		}
		
		return sorted;
	}
	
	private HashMap<Tkey, Integer> countDependencies(Graph<Tkey, Tvalue> graph, LinkedList<Node<Tkey, Tvalue>> process) {
		HashMap<Tkey, Integer> dependencies = new HashMap<Tkey, Integer>();
		for(Node<Tkey, Tvalue> node : graph.getNodes()){
			int count = node.getConnections().size();
			dependencies.put(node.getKey(), count);
			if (count == 0) {
				process.add(node);
			}
		}
		return dependencies;
	}

}
