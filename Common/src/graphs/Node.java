package graphs;

import java.util.Collection;
import java.util.HashMap;

public class Node<Tkey, Tvalue> {

	private HashMap<Tkey, Node<Tkey, Tvalue>> connections;
	private Tkey key;
	public Node(Tkey key) {
		this.key = key;
		this.connections = new HashMap<Tkey, Node<Tkey, Tvalue>>();
	}
	public Tkey getKey() {
		return key;		
	}
	public Collection<Node<Tkey, Tvalue>> getConnections() {
		return this.connections.values();
	}
	public void addConnection(Node<Tkey, Tvalue> node) {
		this.connections.put(node.getKey(), node);		
	}

}
