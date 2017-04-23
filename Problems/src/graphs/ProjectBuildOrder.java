package graphs;

import java.util.List;

import core.StringTuple;
import graphs.TopologicalSort;

public class ProjectBuildOrder {	
	
	public static List<String> find(String[] projectNames, StringTuple[] dependencies, TopologicalSort<String, String> sort) throws CycleDetectedException {
		Graph<String, String> graph = buildGraph(projectNames, dependencies);
		return sort.sort(graph);
	}

	private static Graph<String, String> buildGraph(String[] projectNames, StringTuple[] dependencies) {
		Graph<String, String> graph = new Graph<String, String>();
		for(String projectName : projectNames) {
			graph.createNode(projectName);
		}
		
		for(StringTuple dependency : dependencies){
			graph.getNode(dependency.item2).addConnection(graph.getNode(dependency.item1));
		}
		
		return graph;
	}
}
