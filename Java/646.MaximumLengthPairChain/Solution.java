/* Greedy: Time:O(nlogn), Space:O(1)
 * 1. Sort by ending point, and update "endPoint" if pair[0] > endPoint
 * 2. We can prove pairs[0] must be in optimal solution, so we should get the next least-endPoint non-overlapping pair with pairs[0]
 */

import java.util.*; // Stack

public class Solution {
    private class EndPointComparator implements Comparator<int[]>{
        public int compare(int[] x, int[] y){
            return Integer.valueOf(x[1]).compareTo(Integer.valueOf(y[1]));
        }
    }
    
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new EndPointComparator());
        int len = 1;
        int endPoint = pairs[0][1]; 
        for(int i = 1; i < pairs.length; ++i){
            int[] pair = pairs[i];
            if(pair[0] > endPoint){
                ++len;
                endPoint = pair[1];
            }
        }
        return len;
    }
  
    public static void main(String[] args){
        int[][] pairs = {{1, 2}, {2, 3}, {3, 4}};
        Solution sol = new Solution();
        System.out.print("pairs: ");
        for(int[] pair: pairs){
            System.out.print(Arrays.toString(pair) + ", ");
        }
        System.out.println("");
        System.out.println("longest chain: " + sol.findLongestChain(pairs));
    }
}
