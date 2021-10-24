/* Greedy and Math: Time:O(n), Space:O(n)
 * 1. The order of swap doesn't matter
 * 2. The optimal solution can be retrieve from a previous direct swap
 * 3. So we can get the min by (xCount / 2 + yCount / 2) + (xCount % 2 + yCount % 2) if it equals to 2
 */

import java.util.*; // Stack


public class Solution {
    public int minimumSwap(String s1, String s2) {
        int xCount = 0;
        int yCount = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                xCount += (s1.charAt(i) == 'x')? 1: 0;
                yCount += (s1.charAt(i) == 'y')? 1: 0;
            }
        }
        
        int minSwapCount = 0;
        minSwapCount += xCount / 2;
        int xCountAfterSwapSameChar = xCount % 2;
        minSwapCount += yCount / 2;
        int yCountAfterSwapSameChar = yCount % 2;
        int countAfterSwapSameChar = xCountAfterSwapSameChar + yCountAfterSwapSameChar;
        return (countAfterSwapSameChar == 1)? -1 : minSwapCount + countAfterSwapSameChar; 
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String s1 = "xx";
        String s2 = "yy";
        System.out.println("s1:" + s1 + ", s2:" + s2);
        System.out.println("min:" + sol.minimumSwap(s1, s2));
    }
}
