/* Binary search: Time:O(logn), Space:O(1)
 * 1. If citations[mid] == (size - mid), set lb = mid, hb = lb - 1, and break
 * 2. If citations[mid] < (size - mid), set lb = (mid + 1)
 * 3. Otherwise, set hb = mid - 1;
 */

import java.util.*; // Stack

public class Solution{
    public int hIndex(int[] citations) {
        int size = citations.length;
        int lb = 0;
        int hb = size - 1;
        
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            if(citations[mid] < (size - mid)){
                lb = mid + 1;
            }
            else {
                hb = mid - 1;
            }
        }
        return size - lb;
    }

    public static void main(String[] args){
        
        Solution sol;
        int hIndex;
        int[] citations = new int[] {0, 1, 3, 6, 5};
        
        sol = new Solution();
        hIndex = sol.hIndex(citations);
        
        System.out.println("hIndex: " +  hIndex);
    }
}
