import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class KadaneAlgortihmTests {

	@Test
	public void findSubArrayWithMaxSum_AllNegatives_Greatest() throws Exception {
		int [] allNegatives = {-2, -1, -3, -4, -1};
		int[] subArray = KadaneAlgorithm.findSubArrayWithMaxSum(allNegatives);
		assertArrayEquals(new int[]{-1}, subArray);		
	}
	
	@Test
	public void findSubArrayWithMaxSum_AllPositive_WholeArray() throws Exception {
		int [] allPositives = {2, 1, 3, 4, 1};
		int[] subArray = KadaneAlgorithm.findSubArrayWithMaxSum(allPositives);
		assertArrayEquals(allPositives, subArray);		
	}
	
	@Test
	public void findSubArrayWithMaxSum_Any_CopyOfArray() throws Exception {
		int [] allPositives = {2, 1, 3, 4, 1};
		int[] subArray = KadaneAlgorithm.findSubArrayWithMaxSum(allPositives);
		assertNotSame(allPositives, subArray);		
	}
	
	@Test
	public void findSubArrayWithMaxSum_Mix_OnlyPositiveSubarray() throws Exception {
		int [] mix = {-5,4,10,-2,-20};
		int[] subArray = KadaneAlgorithm.findSubArrayWithMaxSum(mix);
		assertArrayEquals(new int[] {4, 10}, subArray);		
	}
	
	@Test
	public void findSubArrayWithMaxSumAndAtLeastTwoElements_Misc_Misc() throws Exception {
		
		HashMap<int[], int[]> map = new HashMap<int[], int[]>();
		map.put(new int[]{-2, -1, -3, -4, -1}, new int[]{-2, -1});
		map.put(new int[]{-2, -1, -3, 4, -1}, new int[]{4, -1});
		map.put(new int[]{-2, -1, -3, 4, 1}, new int[]{4, 1});
		map.put(new int[]{-5,4,-2,10}, new int[]{4, -2, 10});
		
		for(HashMap.Entry<int[], int[]> entry : map.entrySet()) {
			int[] subArray = KadaneAlgorithm.findSubArrayWithMaxSum(entry.getKey(), 2);
			assertArrayEquals(entry.getValue(), subArray);
		}	
	}
	
	@Test
	public void findSubArrayWithMaxSumAndAtLeastThreeElements_Misc_Misc() throws Exception {
		
		HashMap<int[], int[]> map = new HashMap<int[], int[]>();
		map.put(new int[]{-2, -1, -3, -4, -1}, new int[]{-2, -1, -3});
		map.put(new int[]{-2, -1, -3, 4, -1}, new int[]{-1, -3, 4});
		map.put(new int[]{-2, -1, -3, 4, 1}, new int[]{-3, 4, 1});
		map.put(new int[]{-5,  4, -2, 10}, new int[]{4, -2, 10});
		
		for(HashMap.Entry<int[], int[]> entry : map.entrySet()) {
			int[] subArray = KadaneAlgorithm.findSubArrayWithMaxSum(entry.getKey(), 3);
			assertArrayEquals(entry.getValue(), subArray);
		}	
	}
}
