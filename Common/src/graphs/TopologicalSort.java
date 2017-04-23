package graphs;

import java.util.List;

public interface TopologicalSort<Tkey, TValue> {
	List<Tkey> sort(Graph<Tkey, TValue> graph) throws CycleDetectedException;
}