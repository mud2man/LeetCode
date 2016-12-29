/* Reverse twice: O(n),
 * 1. Traverse backward the current list and add pow(2, i)
 * ex: 0, 1 += 11, 10
 *     00, 01, 10, 11 += 111, 110, 101, 100
 *     000, 001, 010, 011, 111, 110, 101, 100 += 100, 1101, 1110, 1111, 1011, 1010, 1001, 1000
 */

import java.util.*;

public class Solution{
    public List<Integer> grayCode(int n) {
        List<Integer> grayCodes;
        int i;
        int j;
        int k;
        
        grayCodes = new ArrayList<Integer>();
        grayCodes.add(0);
        
        for(i = 0; i < n; i++){
            j = (int)Math.pow(2, i);
            for(k = j - 1; k >= 0; --k){
                grayCodes.add(grayCodes.get(k) + j); 
            }
        }
        return grayCodes;
    }
 
    public static void main(String[] args){
		List<Integer> grayCodes;
		Solution sol;
        int n;

       	n = 3;
		sol = new Solution();
		grayCodes = sol.grayCode(n);

        System.out.println("grayCodes(" + n + "): " + grayCodes);
	}
}
