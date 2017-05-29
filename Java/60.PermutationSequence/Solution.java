/* Math: O(n)
 * 1. Build the list of non-used number
 * 2. Select the corresponding number from the un-used number list 
 * 
 * ex: n = 4, k = 9
 * time[0]: residure = 9, unusedNums = {1, 2, 3, 4}, permutation = ""
 * time[1]: pos = residure/3! = 9/6 = 1, residure = 9%3! = 3, unusedNums = {1, 3, 4}, permutation = "2"
 * time[2]: pos = residure/2! = 3/2 = 1, residure = 3%2! = 1, unusedNums = {1, 4}, permutation = "23"
 * time[3]: pos = residure/1! = 1/1 = 1 => pos - 1 = 0, residure = 1%1! = 0 => residure = 1! = 1, 
 *          unusedNums = {4}, permutation = "231" (because residure = 0, we need correction)
 * time[4]: pos = residure/0! = 1/1 = 1 => pos - 1 = 0, residure = 1%0! = 0 => residure = 0! = 1, 
 *          unusedNums = {}, permutation = "2314" (because residure = 0, we need correction)
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
        String permution = "";
        LinkedList<String> unusedNums;
        int base, pos, residure;
        
        //build the list of non-used number
        unusedNums = new LinkedList<String>();
        for(int i = 1; i <= n; i++){
            unusedNums.add(Integer.toString(i));
        }
        
        //select the corresponding number from the un-used number list 
        residure = k;
        base = factorial(unusedNums.size() - 1);
        while(!unusedNums.isEmpty()){
            pos = residure / base;
            residure = residure % base;
            
            if(residure == 0){
                pos--;
                residure = base;
            }
            
            permution = permution + unusedNums.get(pos);
            unusedNums.remove(pos);
            base = (unusedNums.size() > 0)? (base / unusedNums.size()): base;
        }
        
        return permution;
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
