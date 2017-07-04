/* Backtrack: O(n), where n is the number of gray code
 * 1. Traverse backward the current list and add pow(2, i)
 * ex: 0, 1 += 11, 10
 *     00, 01, 10, 11 += 111, 110, 101, 100
 *     000, 001, 010, 011, 111, 110, 101, 100 += 100, 1101, 1110, 1111, 1011, 1010, 1001, 1000
 */

import java.util.*;

public class Solution{
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<Integer>();
        int base = 1;
        
        ans.add(0);
        for(int i = 0; i < n; ++i){
            for(int j = ans.size() - 1; j >= 0; --j){
                ans.add(ans.get(j) + base);
            }
            base = base * 2;
        }
        return ans;
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
