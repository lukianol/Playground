package graphs;

import java.util.List;

import core.StringTuple;
import graphs.TopologicalSort;

public class ProjectBuildOrder {	
	
	public static List<String> find(String[] projectNames, StringTuple[] dependencies, TopologicalSort<String, String> sort) throws CycleDetectedException, DuplicatedNodeException {
		Graph<String, String> graph = buildGraph(projectNames, dependencies, sort);
		return sort.sort(graph);
	}

	private static Graph<String, String> buildGraph(String[] projectNames, StringTuple[] dependencies, TopologicalSort<String, String> sort) throws DuplicatedNodeException {
		Graph<String, String> graph = new Graph<String, String>();
		for(String projectName : projectNames) {
			graph.createNode(projectName);
		}
		
		Class<? extends TopologicalSort> type = sort.getClass();
		
		if (type == DfsTopologicalSort.class) {
			ProjectBuildOrder.buildDependecniesForDfs(dependencies, graph);
		} else if (type == KahnTopologicalSort.class){
			ProjectBuildOrder.buildDependecniesForKahn(dependencies, graph);
		} else {
			throw new IllegalArgumentException("sort");
		}
		return graph;
	}

	private static void buildDependecniesForDfs(StringTuple[] dependencies, Graph<String, String> graph) {
		for(StringTuple dependency : dependencies){
			graph.getNode(dependency.item2).addConnection(graph.getNode(dependency.item1));
		}		
	}
	
	private static void buildDependecniesForKahn(StringTuple[] dependencies, Graph<String, String> graph) {
		for(StringTuple dependency : dependencies){
			graph.getNode(dependency.item1).addConnection(graph.getNode(dependency.item2));
		}		
	}
}
