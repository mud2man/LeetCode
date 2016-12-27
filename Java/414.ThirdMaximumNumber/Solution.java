/* Use priority queue and hash: O(n)
 */

import java.util.*; // Stack

public class Solution {
    
    public int thirdMax(int[] nums) {
        Set<Integer> hash;
        PriorityQueue<Integer> prq;
        int tmp;
        
        tmp = 0;
        hash = new HashSet<Integer>();
        prq = new PriorityQueue<Integer>();
        
        for(Integer i: nums){
            if(!hash.contains(i)){
                hash.add(i);
                prq.add(i);
                if(prq.size() > 3){
                    prq.poll();
                }
            }
        }
        
        if(prq.size() == 3){
            return prq.peek();
        }
        else{
            while(prq.size() != 0){
                tmp = prq.poll();
            }
            return tmp;
        }
    }
    
    public static void main(String[] args)
    {
		int[] nums = {2, 2, 3, 1};
		Solution sol;

		System.out.println("nums[]: ");
		for(Integer i: nums){
			System.out.print(i + ", ");
		}

		sol = new Solution();
        System.out.println("\nthe third max: " + sol.thirdMax(nums));
	}
}
