/* Greedy: Time:O(n), Space:O(n)
 * 1. Havea a set "positions" to store the visited position without meeting the value which has the same integer
 * 2. Have a set "values" to store the visited value without meeting the position which has the same integer
 * 3. Accumulate count only if "positions" and "values" are empty
 *
 * ex: arr = {1, 0, 2, 3, 4}
 * position = 0: positions = {0}, values = {1}, count = 0
 * position = 1: positions = {}, values = {}, count = 1
 * position = 2: positions = {}, values = {}, count = 2
 * position = 3: positions = {}, values = {}, count = 3
 * position = 4: positions = {}, values = {}, count = 4
 */

import java.util.*;

public class Solution{
    public int maxChunksToSorted(int[] arr) {
        HashSet<Integer> positions = new HashSet<Integer>();
        HashSet<Integer> values = new HashSet<Integer>();
        int count = 0;
        
        for(int position = 0; position < arr.length; ++position){
            int value = arr[position];
            
            if(value == position){
                count = (positions.isEmpty() && values.isEmpty())? count + 1: count;
                continue;
            }
            
            if(values.contains(position)){
                values.remove(position);
            }
            else{
                positions.add(position);
            }
            
            if(positions.contains(value)){
                positions.remove(value);
            }
            else{
                values.add(value);
            }
            
            count = (positions.isEmpty() && values.isEmpty())? count + 1: count;
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] arr = {1, 0, 2, 3, 4};
        
        sol = new Solution();
        System.out.println("arr[]: " + Arrays.toString(arr));
        System.out.println("maximum chunks number: " + sol.maxChunksToSorted(arr));
    }
}
