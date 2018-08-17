/* Math: Time:O(1), Space:O(1).
 * 1. For i-th bulb, the status is dependent of the number of factors. ex: 6 has 1,2,3,6
 * 2. All numbers except square has even number of factors, because factor p and n/p partner
 * 3. So the porblem is to find how many squares in n
 */

import java.util.*;

public class Solution{
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 3;
        System.out.println("n: " + n);
        System.out.println("lighted bulb: " + sol.bulbSwitch(n));
    }
}
