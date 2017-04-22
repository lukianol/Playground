public class KadaneAlgorithm {
	public static int[] findSubArrayWithMaxSum(int[] array) {
		if (array == null) {
			return null;
		}
		
		if (array.length == 0) {
			return array.clone();
		}
		
		int start = 0;
		int end = 0;
		int newStart = 0;
		int currentMax = array[0];
		int maxSoFar = array[0];
		for(int i = 0; i < array.length; i++) {
			int value = array[i];
			int newMax = value + currentMax;
			if (value > newMax) {
				currentMax = value;
				newStart = i;
			} else {
				currentMax = newMax;
			}
			if (maxSoFar < currentMax) {
				start = newStart;
				end = i;
				maxSoFar = currentMax;
			}
		}
		
	    int[] newArray = new int[end - start + 1];
		for(int i = 0; i < newArray.length; i++){
			newArray[i] = array[start + i];
		}
		return newArray;		
	}
}
