/* Math: Time:O(n), Space:O(1).
 * 1. We number of combination after i-th char is pow(2, n - i - 1). We can build base = pow(2, n - 1) in the beginning, and divide by 2 in each loop
 * 2. In each loop, we can decide logicalIdx by (k - 1) / base, since we need to exclude pre char
 * 3. Recaculate the realIdx, and append letters[realIdx]
 */          

import java.util.*; 
public class Solution {
    public String getHappyString(int n, int k) {
        int base = (int)Math.pow(2, n - 1);
        if(k > 3 * base){
            return "";
        }
        char[] letters = {'a', 'b', 'c'};
        StringBuilder happy = new StringBuilder("");
        char prev = '#';
        for(int i = 0; i < n; ++i){
            int logicalIdx = (k - 1) / base;
            k -= logicalIdx * base;
            base /= 2;
            int realIdx = 0;
            while(logicalIdx > 0){
                logicalIdx -= (letters[realIdx] != prev)? 1: 0;
                realIdx++;
            }
            realIdx += (letters[realIdx] == prev)? 1: 0;
            happy.append(letters[realIdx]);
            prev = letters[realIdx];
        }
        return happy.toString();
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 3;
        int k = 9;
        System.out.println("n:" + n + ", k:" + k);
        System.out.println("happy string:" + sol.getHappyString(n, k));
    }
}
