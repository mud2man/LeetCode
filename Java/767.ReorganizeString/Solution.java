/* Heap: Time:O(n), Space:O(n). Similar with 169.MajorityElement
 * 1. Get the map to store map of character to frequency
 * 2. Poll from heap, and put the character in evene index first
 * 3. If the previous top not done, keep assign it to the odd index, and check if array[i] == array[i - 1]
 */

import java.util.*; // Stack

public class Solution {
    private class MaxHeapComparator implements Comparator<int[]>{
        public int compare(int[] x, int[] y){
            return y[1] - x[1];
        }    
    }
    
    public String reorganizeString(String S) {
        int[] map = new int[26];
        for(int i = 0; i < S.length(); ++i){
            map[S.charAt(i) - 'a']++;
        }
        
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>(new MaxHeapComparator());
        for(int i = 0; i < map.length; ++i){
            if(map[i] != 0){
                maxHeap.add(new int[]{i, map[i]});
            }   
        }

        char[] array = new char[S.length()];
        int i = 0;
        int[] top = {0, 0};
        while(i < array.length){
            top = maxHeap.poll();
            while(top[1] > 0 && i < array.length){
                array[i] = (char)(top[0] + 'a');
                top[1]--;
                i += 2;
            }
        }
        
        i = 1;
        while(i < array.length){
            top = (top[1] == 0)? maxHeap.poll(): top;
            for(int j = 0; j < top[1]; ++j){
                array[i] = (char)(top[0] + 'a');
                if(array[i] == array[i - 1]){
                    return "";
                }
                top[1]--;
                i += 2;
            }
        }
        
        return new String(array);
    }

    public static void main(String[] args){
        Solution sol;
        String S = "aab";

        sol = new Solution();
        System.out.println("S: " + S);
        System.out.println("reorginized string: " + sol.reorganizeString(S));
    }
}
