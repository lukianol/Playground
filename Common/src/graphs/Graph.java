package graphs;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import core.Tuple;

public class Graph<Tkey, Tvalue> {	

	private HashMap<Tkey, Node<Tkey, Tvalue>> nodes;
	
	public Graph(){
		this.nodes = new HashMap<Tkey, Node<Tkey, Tvalue>>();
	}

	public Collection<Node<Tkey, Tvalue>> getNodes() {
		return nodes.values();
	}

	public Node<Tkey, Tvalue> createNode(Tkey key) throws DuplicatedNodeException {
		
		if (this.nodes.containsKey(key)) {
			throw new DuplicatedNodeException();
		}
		
		Node<Tkey, Tvalue> node = new Node<Tkey, Tvalue>(key);		
		this.nodes.put(key, node);
		return node;
	}

	public Node<Tkey, Tvalue> getNode(Tkey key) {
		return this.nodes.get(key);
	}

	/**
	 * space complexity: O(V + E), time complexity: O(V + E)
	 * @param graph
	 */
	public static <Tkey, Tvalue> void reverse(Graph<Tkey, Tvalue> graph) {
		HashSet<Tuple<Tkey, Tkey>> revertedConnections = new HashSet<Tuple<Tkey, Tkey>>();
		HashSet<Tkey> visitedNodes = new HashSet<Tkey>();
		for(Node<Tkey, Tvalue> node : graph.getNodes()){
			Graph.reverse(node, revertedConnections, visitedNodes);
		}
		
	}
	
	private static <Tkey, Tvalue> void reverse(Node<Tkey, Tvalue> node, HashSet<Tuple<Tkey, Tkey>> revertedConnections, HashSet<Tkey> visitedNodes) {
		if (visitedNodes.contains(node)){
			return;
		}
		
		Tkey nodeKey = node.getKey();
		visitedNodes.add(nodeKey);
		
		Iterator<Node<Tkey, Tvalue>> iterator = node.getConnections().iterator();
		
		while(iterator.hasNext()) {
			Node<Tkey, Tvalue> connection = iterator.next();
			Tkey connectionKey = connection.getKey();
			Tuple<Tkey, Tkey> nodeToConnectionKey = new Tuple<Tkey, Tkey>(nodeKey, connectionKey);
			
			if (revertedConnections.contains(nodeToConnectionKey)) {
				continue;
			}
			
			if (!connection.hasConnection(node)) {			
				connection.addConnection(node);
				iterator.remove();
			} else {
				revertedConnections.add(nodeToConnectionKey);
			}
			revertedConnections.add(new Tuple<Tkey, Tkey>(connectionKey, nodeKey));
			Graph.reverse(connection, revertedConnections, visitedNodes);
		}
	}
}
