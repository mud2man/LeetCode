/* Dynamic Programming: Time:O(n), Space:O(1)
 * 1. "minDeleteEndA" is the minimum deletion with ending char = a, while "minDeleteEndB" is the minimum deletion with ending char = b
 * 2. Update "minDeleteEndA" and "minDeleteEndB" while traversing s from left to right
 */

import java.util.*; // Stack


public class Solution {
    public int minimumDeletions(String s) {
        int minDeleteEndA = 0;
        int minDeleteEndB = 0;
        for(char c: s.toCharArray()){
            if(c == 'a'){
                minDeleteEndB = Math.min(minDeleteEndA + 1, minDeleteEndB + 1);
            }else{
                minDeleteEndB = Math.min(minDeleteEndA, minDeleteEndB);
                minDeleteEndA++;
            }
        }
        return Math.min(minDeleteEndA, minDeleteEndB);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "aababbab"; 
        System.out.println("s:" + s);
        System.out.println("minimun deletions:" + sol.minimumDeletions(s));
    }
}
