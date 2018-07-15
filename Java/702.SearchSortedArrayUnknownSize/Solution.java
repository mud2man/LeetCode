/* Binary search: Time:O(logn), Space:O(1)
 * 1. Binary search the length of array, and then binary search the target
 */

import java.util.*;

// This is the ArrayReader's control interface.
interface ArrayReader {
    public int get(int index);
}

public class Solution{
    public int search(ArrayReader reader, int target) {
        int lb = 0;
        int hb = 9999;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            if(reader.get(mid) == Integer.MAX_VALUE){
                hb = mid - 1;
            }
            else{
                lb = mid + 1;
            }
        }
        int length = lb;
        
        lb = 0;
        hb = length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            if(reader.get(mid) > target){
                hb = mid - 1;
            }
            else{
                lb = mid + 1;
            }
        }
        
        return (reader.get(hb) == target)? hb: -1;
    }
  
    public static void main(String[] args){
        System.out.println("No test cases");
    }
}
