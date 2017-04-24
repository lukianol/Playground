package graphs;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTests {

	@Test
	public void reverse_succeeds() throws DuplicatedNodeException {
		
		Graph<Integer, Integer> graph = new Graph<Integer, Integer>();
				
		Node<Integer, Integer> _1 = graph.createNode(1);
		Node<Integer, Integer> _2 = graph.createNode(2);
		Node<Integer, Integer> _3 = graph.createNode(3);
		Node<Integer, Integer> _4 = graph.createNode(4);
		Node<Integer, Integer> _5 = graph.createNode(5);
		Node<Integer, Integer> _6 = graph.createNode(6);
		Node<Integer, Integer> _7 = graph.createNode(7);
		Node<Integer, Integer> _8 = graph.createNode(8);
		
		_1.addConnection(_8);
		_1.addConnection(_2);
		_2.addConnection(_3);
		_3.addConnection(_4);
		_3.addConnection(_6);
		_4.addConnection(_5);
		_5.addConnection(_6);
		_6.addConnection(_8);
		_8.addConnection(_1);
		_8.addConnection(_4);
		
		Graph.reverse(graph);
		
		assertTrue(_1.hasConnection(_8));
		assertTrue(_2.hasConnection(_1));
		assertTrue(_3.hasConnection(_2));
		assertTrue(_4.hasConnection(_3));
		assertTrue(_4.hasConnection(_8));
		assertTrue(_5.hasConnection(_4));
		assertTrue(_6.hasConnection(_5));
		assertTrue(_6.hasConnection(_3));
		assertTrue(_8.hasConnection(_1));	
		assertTrue(_8.hasConnection(_6));
		assertTrue(_7.getConnections().size() == 0);
	}

}
