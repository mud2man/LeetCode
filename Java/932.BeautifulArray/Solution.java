/* Divide and conquer: Time:O(n * logn), Space:O(n)
 * 1. Split the array {a, a + b, a + 2b, ...} to left = {a, a + 2b, a + 4b, ...} and right = {a + b, a + 3b, a + 5b, ...}
 * 2. Call the helper to get beautiful array given left and right, and return the joint array of beautiful of left and beautiful of right
 * 3. We can said any tuple {left, left, right} or {left, right, right} is beautiful, since (left + right = 2a + (odd numbe) * b) != (2*left = 2a + (even numbe) * b) != (2*right = 2a + (even numbe) * b)
 */

import java.util.*; // Stack


public class Solution {
    private List<Integer> divideConquer(int a, int b, int n){
        List<Integer> beautiful = new ArrayList<>();
        if(n == 1){
            beautiful.add(a);
        }else{
            int letfCount = (n  + 1) / 2;
            int rightCount = n - letfCount;
            List<Integer> leftBeautiful = divideConquer(a, 2 * b, letfCount);
            List<Integer> rightBeautiful = divideConquer(a + b, 2 * b, rightCount);
            beautiful.addAll(leftBeautiful);
            beautiful.addAll(rightBeautiful);
        }
        return beautiful;
    }
    
    public int[] beautifulArray(int n) {
        List<Integer> beautiful = divideConquer(1, 1, n);
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            ans[i] = beautiful.get(i);
        }
        return ans;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 4;
        System.out.println("n:" + n);
        System.out.println("beautiful array" + Arrays.toString(sol.beautifulArray(n)));
    }
}
