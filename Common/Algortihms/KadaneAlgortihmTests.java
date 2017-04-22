import static org.junit.Assert.*;

import org.junit.Test;

public class KadaneAlgortihmTests {

	@Test
	public void findSubArrayWithMaxSum_AllNegatives_ReturnsTheGreatest() {
		int [] allNegatives = {-2, -1, -3, -4, -1};
		int[] subArray = KadaneAlgorithm.findSubArrayWithMaxSum(allNegatives);
		assertArrayEquals(new int[]{-1}, subArray);		
	}

}
