/* Math: O(n)
 * 1. Build the list of non-used number
 * 2. Select the corresponding number from the un-used number list 
 * 
 * ex: n = 4, k = 9
 * time[0]: k = 8, unusedNums = {1, 2, 3, 4}, permutation = ""
 * time[1]: pos = (9 - 1)/3! = 1, k = 9 - (1 * 3!) = 3, unusedNums = {1, 3, 4}, permutation = "2"
 * time[2]: pos = (3 - 1)/2! = 1, k = 3 - (1 * 2!) = 1, unusedNums = {1, 4}, permutation = "23"
 * time[3]: pos = (1 - 1)/1! = 0, k = 1 - (0 * 1!) = 1, unusedNums = {4}, permutation = "231"
 * time[4]: pos = (1 - 1)/0! = 0, k = 1 - (0 * 1!) = 1, unusedNums = {}, permutation = "2314"
 */

import java.util.*;

public class Solution{
    public int factorial(int n){
        if(n == 0){
            return 1;
        }
        return n * factorial(n - 1);
    }
    
    public String getPermutation(int n, int k) {
        StringBuilder permutation = new StringBuilder("");
        ArrayList<Integer> remain = new ArrayList<Integer>();
        int pos;
        for(int i = 1; i <= n; ++i){
           remain.add(i); 
        }
        
        while(!remain.isEmpty()){
            pos = (k - 1) / factorial(remain.size() - 1);
            permutation.append(remain.get(pos));
            remain.remove(pos);
            k = k - pos * factorial(remain.size());
        }
        return permutation.toString();
    }

	public static void main(String[] args){
        String permutation;
        Solution sol;
        int n, k;
        
        n = 4;
        k = 9;
        sol = new Solution();
	
        System.out.println("n:" + n + ", k:" + k);
        permutation = sol.getPermutation(n, k);
        System.out.println("permutation: " + permutation);
	}
}
