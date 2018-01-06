/* Math: Time:O(n + k), Space:O(n) where k is the number of update
 * 1. Caculate "diff" from update, diff[i] means the change of value happend on index i
 * 2. Caculate array[i] based on diff, where i starts from 0 
 *
 * ex: updates = {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}}
 * diff = {-2, +2, +3, +2, -2, -3}
 * array ={-2, -2 + 2, 0 + 3. 3 + 2, 5 - 2}
 */

import java.util.*;

public class Solution{
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] diff = new int[length + 1];
        int[] array = new int[length];
        
        for(int[] update: updates){
            int startIndex = update[0];
            int endIndex = update[1];
            int inc = update[2];
            diff[startIndex] += inc;
            diff[endIndex + 1] -= inc;
        }
        
        int previousValue = 0;
        for(int i = 0; i < length; ++i){
            array[i] = previousValue + diff[i];
            previousValue = array[i];
        }
        
        return array;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] updates = {{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
        int length = 5;

        System.out.println("length: " + length);
        System.out.println("updates: ");
        for(int[] update: updates){
            System.out.println(Arrays.toString(update));
        }
        System.out.println("array: " + Arrays.toString(sol.getModifiedArray(length, updates)));
    }
}
