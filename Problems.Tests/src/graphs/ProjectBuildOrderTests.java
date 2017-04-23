package graphs;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import core.StringTuple;

public class ProjectBuildOrderTests {
	
	@Test
	public void projectBuildOrder_Dfs_WorksAsExpected() throws CycleDetectedException {
		String[] projects = {"a", "b", "c", "d", "e", "f"};
		StringTuple[] dependencies = {
				new StringTuple("a", "d"),
				new StringTuple("f", "b"),
				new StringTuple("b", "d"),
				new StringTuple("f", "a"),
				new StringTuple("d", "c")
		};
		List<String> order = ProjectBuildOrder.find(
				projects, 
				dependencies, 
				new DfsTopologicalSort<String, String>());
		
		Assert.assertArrayEquals(new String[]{"f", "a", "b", "d", "c", "e"}, order.toArray());
	}
	
	@Test
	public void projectBuildOrder_Kahn_WorksAsExpected() throws CycleDetectedException {
		String[] projects = {"a", "b", "c", "d", "e", "f"};
		StringTuple[] dependencies = {
				new StringTuple("a", "d"),
				new StringTuple("f", "b"),
				new StringTuple("b", "d"),
				new StringTuple("f", "a"),
				new StringTuple("d", "c")
		};
		List<String> order = ProjectBuildOrder.find(
				projects, 
				dependencies, 
				new KahnTopologicalSort<String, String>());
		
		Assert.assertArrayEquals(new String[]{"e", "f", "a", "b", "d", "c" }, order.toArray());
	}
	
	@Test
	public void projectBuildOrder_GraphWithCycleDfsSort_ThrowsException() throws CycleDetectedException {
		String[] projects = {"a", "b"};
		StringTuple[] dependencies = {
				new StringTuple("a", "b"),
				new StringTuple("b", "a")
		};

		try {
			ProjectBuildOrder.find(
				projects, 
				dependencies, 
				new DfsTopologicalSort<String, String>());
			Assert.fail();
		} catch(Exception e) {
			if (e.getClass() != CycleDetectedException.class) {
				Assert.fail();
			}
		}
	}
	
	@Test
	public void projectBuildOrder_GraphWithCycleKahnSort_ThrowsException() throws CycleDetectedException {
		String[] projects = {"a", "b"};
		StringTuple[] dependencies = {
				new StringTuple("a", "b"),
				new StringTuple("b", "a")
		};

		try {
			ProjectBuildOrder.find(
				projects, 
				dependencies, 
				new KahnTopologicalSort<String, String>());
			Assert.fail();
		} catch(Exception e) {
			if (e.getClass() != CycleDetectedException.class) {
				Assert.fail();
			}
		}
	}
}
