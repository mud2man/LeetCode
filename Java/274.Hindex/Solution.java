/* Sorting first and Check: O(nlgn)
 * 1. Sort and check from the largest 
 */

import java.util.*; // Stack

public class Solution{

    Solution(){
	}

	public int hIndex(int[] citations){
		int idx;
		int arraySize;
		int hValue;
		int maxhValue;
		int hCount;
		
		maxhValue = 0;

		Arrays.sort(citations);
		arraySize = citations.length;

		for(idx = arraySize - 1; idx >= 0; idx--){
			
			hValue = arraySize - idx;

			if(hValue <= citations[idx]){
				if(idx > 0){
					if(citations[idx - 1] <= hValue){
						maxhValue =  hValue;
					}
				}
				else{
					maxhValue =  hValue;
				}
			}
		}

		return maxhValue;
	}

	public static void main(String[] args){
		
		Solution sol;
		int hIndex;
		int[] citations = new int[] {1, 1};
		
		sol = new Solution();
		hIndex = sol.hIndex(citations);
		
		System.out.println("hIndex: " +  hIndex);
	}
}
