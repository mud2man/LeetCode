/* Math: O(n), n = digit# of high
 * 1. Caculate the number of strobogrammatic number between length of low and high
 * 2. Cut off the answer with the number of strobogrammatic number less than low
 * 3. Cut off the answer with the number of strobogrammatic number larger than high
 */

import java.util.*;
import java.math.*;

public class Solution{
    private String fill(String left, int endIdx){
        char[] map = {'0', '1', ' ', ' ', ' ', ' ', '9', ' ', '8', '6'};
        StringBuilder right = new StringBuilder("");
        for(int i = endIdx; i >= 0; --i){
            char c = left.charAt(i);
            right.append(map[c - '0']);
        }
        return left + right.toString();
    }
    
    private int getBeyondLimit(String boundry, Set<Integer> ends, Set<Integer> middles, Set<Integer> centers, boolean isLower){
        int[][] middleCount = {{0, 1, 2, 2, 2, 2, 2, 3, 3, 4, 5}, {4, 3, 3, 3, 3, 3, 2, 2, 1, 0}};
        int[][] endCount = {{0, 0, 1, 1, 1, 1, 1, 2, 2, 3, 4}, {4, 3, 3, 3, 3, 3, 2, 2, 1, 0}};
        int[][] cneterCount = {{0, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3}, {2, 1, 1, 1, 1, 1, 1, 1, 0, 0}};
        int count = 0;
        int base = (boundry.length() % 2 == 1)? 3: 5;
        int len = boundry.length();
        int y = isLower? 0: 1;
        for(int i = 0; i < (len + 1) / 2 ; ++i){
            int digit = boundry.charAt(i) - '0';
            int remain = (len - (i + 1) * 2 + 1) / 2 - 1;
            if(i == 0){
                count += (remain >= 0)? endCount[y][digit] * (int)Math.pow(5, remain) * base: endCount[y][digit];
                if(!ends.contains(digit)){
                    return count;
                }
            }
            else if(len % 2 == 0 || i < (len / 2)){
                count += (remain >= 0)?middleCount[y][digit]*(int)Math.pow(5, remain)*base: middleCount[y][digit];
                if(!middles.contains(digit)){
                    return count;
                }
            }
            else{
                count += cneterCount[y][digit];
                if(!centers.contains(digit)){
                    return count;
                }
            }
        }
        String s = (len%2 == 0)? fill(boundry.substring(0, len/2), len/2 - 1): fill(boundry.substring(0, len/2 + 1), len/2 - 1);
        return (isLower)? (s.compareTo(boundry) < 0)? count + 1: count: (s.compareTo(boundry) > 0)? count + 1: count;
    }
    
    public int strobogrammaticInRange(String low, String high) {
        int[] middleCount = {1, 2, 2, 2, 2, 2, 2, 2, 3, 3};
        int count = 0;
        if(low.length() > high.length() || (low.length() == high.length() && low.compareTo(high) > 0)){
            return 0;
        }
        if(low.length() == 1 && high.length() == 1){
            count += middleCount[Integer.valueOf(high)];
            count -= (Integer.valueOf(low) == 0)? 0: middleCount[Integer.valueOf(low) - 1];
            return count;
        }
        else if(low.length() == 1){
            count += middleCount[9];
            count -= (Integer.valueOf(low) == 0)? 0: middleCount[Integer.valueOf(low) - 1];
            low = "10";
        }
        
        for(int i = low.length(); i <= high.length(); ++i){
            count += (i % 2 == 1)? 4 * (int)Math.pow(5, i / 2 - 1) * 3: 4 * (int)Math.pow(5, i / 2 - 1);
        }
        Set<Integer> ends = new HashSet<>(Arrays.asList(new Integer[]{1, 6, 8, 9}));
        Set<Integer> middles = new HashSet<>(Arrays.asList(new Integer[]{0, 1, 6, 8, 9}));
        Set<Integer> centers = new HashSet<>(Arrays.asList(new Integer[]{0, 1, 8}));
        count -= getBeyondLimit(low, ends, middles, centers, true);
        count -= getBeyondLimit(high, ends, middles, centers, false);
        return count;
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
