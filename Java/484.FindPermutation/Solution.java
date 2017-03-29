/* Greedy: O(n)
 * 1. Keep a curr value
 * 2. If the current char is 'I', put curr into answer array and accumulate curr
 * 3. If the current char is 'D', count how many consecutive 'D' and store it into 'count'
 * 4. Put decending number ending as 'curr' into answer array with the size 'count', and curr = curr + count
 *
 * EX: s = 'IIDDDI'
 * time[0]: curr = 1, index = 0, min = [1, 0, 0, 0, 0, 0, 0] 
 * time[1]: curr = 2, index = 1, min = [1, 2, 0, 0, 0, 0, 0] 
 * time[2]: curr = 3, index = 2, min = [1, 2, 5, 4, 3, 0, 0] 
 * time[3]: curr = 6, index = 5, min = [1, 2, 5, 4, 3, 6, 0] 
 * time[4]: curr = 7, index = 6, min = [1, 2, 5, 4, 3, 6, 7] 
 */

import java.util.*; // Stack

public class Solution {
    public int[] findPermutation(String s) {
        int idx, jdx, curr, count;
        int[] min;
        
        min = new int[s.length() + 1];
        curr = 1;
        idx = 0;
        
        while(idx < s.length()){
            if(s.charAt(idx) == 'I'){
                min[idx++] = curr++;
            }
            else{
                /* count how many consecutive 'D' */
                for(count = 0; (count + idx) < s.length(); count++){
                    if(s.charAt(idx + count) == 'I'){
                        break;
                    }
                }
                curr = curr + count;
                for(jdx = 0; jdx <= count; jdx++){
                   min[idx + jdx] = curr - jdx; 
                }
                curr++;
                idx = idx + count + 1;
            }
        }
        
        if(idx == s.length()){
            min[idx] = curr;
        }
        return min;
    }
 
    public static void main(String[] args){
        Solution sol;
        String s = "IIDDDI"; 

        sol = new Solution();
        
        System.out.println("Secret signature: " + s);
        System.out.println("Smallest permutation: " + Arrays.toString(sol.findPermutation(s)));
    }
}
