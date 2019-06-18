/* Math: O(n), n = digit# of high
 * 1. Caculate the number of strobogrammatic number between length of low and high
 * 2. Cut off the answer with the number of strobogrammatic number less than low
 * 3. Cut off the answer with the number of strobogrammatic number larger than high
 * 4. If the nearest strobogrammatic of low is smaller than low, decrease total count
 * 5. If the nearest strobogrammatic of high is bigger than high, decrease total count
 */

import java.util.*;
import java.math.*;

public class Solution{
    private int getBoundryCount(String boundry, boolean isLower){
        char[] map = {'0', '1', '#', '#', '#', '#', '9', '#', '8', '6'};
        int[][] medianCount = {{0, 1, 2, 2, 2, 2, 2, 2, 2, 3}, {2, 1, 1, 1, 1, 1, 1, 1, 0, 0}};
        int[][] lsbCount = {{0, 0, 1, 1, 1, 1, 1, 2, 2, 3}, {4, 3, 3, 3, 3, 3, 2, 2, 1, 0}};
        int[][] middleCount = {{0, 1, 2, 2, 2, 2, 2, 3, 3, 4}, {4, 3, 3, 3, 3, 3, 2, 2, 1, 0}};
        int len = boundry.length();
        int end = (len % 2 == 1)? len / 2: len / 2 - 1;
        int right = (len % 2 == 1)? 3: 1;
        len /= 2;
        right *= (len > 0)? (int)Math.pow(5, len - 1): 1;
        right *= (len > 0)? 4: 1;
        int left = 0;
        int tableIdx = isLower? 0: 1;
        int totalCount = 0;
        for(int i = 0; i <= end; ++i){
            char c = boundry.charAt(i);
            if(i == end && boundry.length() % 2 == 1){
                left = medianCount[tableIdx][c - '0'];
                right /= 3;
            }else if(i == 0){
                left = lsbCount[tableIdx][c - '0'];
                right /= 4;
            }else{
                left = middleCount[tableIdx][c - '0'];
                right /= 5;
            }
            totalCount += left * right;
            if(map[c - '0'] == '#'){
                break;
            }
        }
        return totalCount;
    }
    
    private String genStrobogrammatic(String number){
        char[] map = {'0', '1', '#', '#', '#', '#', '9', '#', '8', '6'};
        StringBuilder strobogrammatic = new StringBuilder("");
        for(int i = (number.length() + 1) / 2 - 1; i >= 0; --i){
            if(map[number.charAt(i) - '0'] == '#'){
                return "#";
            }else if(i == (number.length() + 1) / 2 - 1 && number.length() % 2 == 1){
                strobogrammatic.append(number.charAt(i));
            }else{
                char left = number.charAt(i);
                char right = map[left - '0'];
                strobogrammatic.insert(0, left);
                strobogrammatic.append(right);
            }
        }
        return strobogrammatic.toString();
    }
    
    public int strobogrammaticInRange(String low, String high) {
        if(new BigInteger(low).compareTo(new BigInteger(high)) > 0){
            return 0;
        }
        int totalCount = 0;
        int lowLen = low.length();
        int highLen = high.length();
        for(int l = lowLen; l <= highLen; ++l){
            int len = l;
            int count = (len % 2 == 1)? 3: 1;
            len /= 2;
            count *= (len > 0)? (int)Math.pow(5, len - 1): 1;
            count *= (len > 0)? 4: 1;
            totalCount += count; 
        }
        totalCount -= getBoundryCount(low, true);
        totalCount -= getBoundryCount(high, false);
        totalCount -= (!genStrobogrammatic(low).equals("#") && low.compareTo(genStrobogrammatic(low)) > 0)? 1: 0;
        totalCount -= (!genStrobogrammatic(high).equals("#") && high.compareTo(genStrobogrammatic(high)) < 0)? 1: 0;
        return totalCount;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String low = "50";
        String high = "100";
        int count = sol.strobogrammaticInRange(low, high);
        System.out.println("low: " + low);
        System.out.println("high: " + high);
        System.out.println("strobogrammaticWords#: " + count);
    }
}
