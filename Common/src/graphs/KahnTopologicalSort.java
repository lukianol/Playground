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
		HashMap<Tkey, Integer> dependencies = this.countDependencies(graph);
		this.addRoots(graph, dependencies, process);		
		
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
	
	private void addRoots(Graph<Tkey, Tvalue> graph, HashMap<Tkey, Integer> dependencies, LinkedList<Node<Tkey, Tvalue>> process) {
		for(Node<Tkey, Tvalue> node : graph.getNodes()){
			if (dependencies.get(node.getKey()) == 0) {
				process.add(node);
			}
		}		
	}

	private HashMap<Tkey, Integer> countDependencies(Graph<Tkey, Tvalue> graph) {
		HashMap<Tkey, Integer> dependencies = new HashMap<Tkey, Integer>();
		for(Node<Tkey, Tvalue> node : graph.getNodes()){
			Tkey key = node.getKey();
			if (!dependencies.containsKey(key)) {
				dependencies.put(key, 0);
			}
			for(Node<Tkey, Tvalue> connection : node.getConnections()) {
				key = connection.getKey();
				int counter = 0;
				if (dependencies.containsKey(key)) {
					counter = dependencies.get(key);
				}				 
				dependencies.put(key, counter + 1); 
			}
		}
		return dependencies;
	}
}
