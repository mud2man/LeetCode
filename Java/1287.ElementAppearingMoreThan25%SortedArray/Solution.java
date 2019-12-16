/* Binary search: Time:O(logn), Space:O(1)
 * 1. Since the majority is more than 25%, it must appear in either index arr.length / 4, arr.length / 2 or (arr.length / 4) * 3
 * 2. We can use biranry search "search" to get the index of first apperaing and the last appearing
 * 3. Return the candidate if end - start + 1 > arr.length / 4
 */

import java.util.*; // Stack

public class Solution {
    private int search(int[] arr, int target, boolean containsEqual){
        int lb = 0;
        int hb = arr.length - 1;
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            if(containsEqual){
                if(arr[mid] <= target){
                    lb = mid + 1;
                }else{
                    hb = mid - 1;
                }
            }else{
                if(arr[mid] < target){
                    lb = mid + 1;
                }else{
                    hb = mid - 1;
                }
            }
        }
        return containsEqual? hb: lb;
    }
    
    public int findSpecialInteger(int[] arr) {
        int[] candidateIdxs = {arr.length / 4, arr.length / 2, (arr.length / 4) * 3};
        for(int candidateIdx: candidateIdxs){
            int candidate = arr[candidateIdx];
            int start = search(arr, candidate, false);
            int end = search(arr, candidate, true);
            if(end - start + 1 > arr.length / 4){
                return candidate;
            }
        }
        return 0;
    }
  
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 6, 6, 6, 6, 7, 10};
        Solution sol = new Solution();
        System.out.println("arr:" + Arrays.toString(arr));
        System.out.println("majority:" + sol.findSpecialInteger(arr));
    }
}
