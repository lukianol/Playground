public class KadaneAlgorithm {
		
	public static int[] findSubArrayWithMaxSum(int[] array) throws Exception {
		return findSubArrayWithMaxSum(array, 0);
	}
	
	public static int[] findSubArrayWithMaxSum(int[] array, int subArrayMinSize) throws Exception {
		if (array == null) {
			return null;
		}
		
		if (subArrayMinSize < 0) {
			throw new IllegalArgumentException("subArrayMinSize cannot be less than zero.");
		}
		
		if (array.length < subArrayMinSize) {
			throw new Exception("Cannot return subArray of specified min size.");
		}
		
		if (subArrayMinSize == array.length) {
			return array.clone();
		}
		
		if (subArrayMinSize == 0) {
			subArrayMinSize = 1;
		}
		
		int start = 0;
		int end = subArrayMinSize - 1;
		int newStart = 0;
		int currentMax = 0;
		for(int i = 0; i < subArrayMinSize; i++) {
			currentMax += array[i];
		}
		int maxSoFar = currentMax;
		int lastValues = currentMax - array[0];
		
		for(int i = subArrayMinSize; i < array.length; i++) {
			int value = array[i];
			int newMax = value + currentMax;
			int subArrayStart = i - (subArrayMinSize - 1);
			if (value + lastValues > newMax) {
				currentMax = value + lastValues;
				newStart = subArrayStart;
			} else {
				currentMax = newMax;
			}
			if (maxSoFar < currentMax) {
				start = newStart;
				end = i;
				maxSoFar = currentMax;
			}
			lastValues += value;
			lastValues -= array[subArrayStart];
		}
		
	    int[] newArray = new int[end - start + 1];
		for(int i = 0; i < newArray.length; i++){
			newArray[i] = array[start + i];
		}
		return newArray;		
	}
}
