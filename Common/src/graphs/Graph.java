package graphs;

import java.util.Collection;
import java.util.HashMap;

public class Graph<Tkey, Tvalue> {	

	private HashMap<Tkey, Node<Tkey, Tvalue>> nodes;
	
	public Graph(){
		this.nodes = new HashMap<Tkey, Node<Tkey, Tvalue>>();
	}

	public Collection<Node<Tkey, Tvalue>> getNodes() {
		return nodes.values();
	}

	public void createNode(Tkey key) {
		this.nodes.put(key, new Node<Tkey, Tvalue>(key));
	}

	public Node<Tkey, Tvalue> getNode(Tkey key) {
		return this.nodes.get(key);
	}
}
