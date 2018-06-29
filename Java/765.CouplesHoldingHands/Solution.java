/* Graph: Time:O(n), Space:O(n). Leetcode has a easier-understanding solution
 * 1. The problem has a maximum swap count (n - 1). And making a swap not benifited couple is redundant
 * 2. There are 3 cases of swap
 * 3. Case1: Swapping from a paired couple. It a best case
 * 4. Case2: Swapping from a non-paired couple, but can make another paired couple. It's can be proved that swapping husband 
 *    or wife can caused another couple
 * 5. Case3: Swapping from a non-paired couple, and cannot make another paired couple. Then the relationship of rest n - 2 keeps the same
 */         

import java.util.*;

public class Solution {
    private void swap (int[] row, int x, int y){
        int tmp = row[x];
        row[x] = row[y];
        row[y] = tmp;
    }
    
    public int minSwapsCouples(int[] row) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
        for(int i = 0; i < row.length; ++i){
            if(row[i] % 2 == 0){
                row[i] = -(row[i] + 1);
            }
            map.put(row[i], i);
        }
        
        int swapCount = 0;
        for(int i = 0; i < row.length; i+=2){
            if((row[i] + row[i + 1]) == 0){
                continue;
            }
            else{
                int partner = -row[i];
                int partnerIdx = map.get(partner);
                map.put(row[i + 1], partnerIdx);
                map.put(partner, i + 1);
                swap(row, i + 1, partnerIdx);
                swapCount++;
            }
        }
        
        return swapCount;
    }
 
    public static void main(String[] args){
        Solution sol= new Solution();
        int[] row = {0, 2, 1, 3}; 
        System.out.println("row: " + Arrays.toString(row));
        System.out.println("minimum swap #: " + sol.minSwapsCouples(row));
    }
}
