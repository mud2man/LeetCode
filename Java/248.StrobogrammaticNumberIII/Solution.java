/* Math: O(n), n = digit# of high
 * 1. Caculate the number of strobogrammatic number between length of low and high
 * 2. Cut off the answer with the number of strobogrammatic number less than low
 * 3. Cut off the answer with the number of strobogrammatic number larger than high
 */

import java.util.*;
import java.math.*;

//Definition for singly-linked list.
public class Solution{
    private int[] lowerMap = {0, 1, 2, 2, 2, 2, 2, 3, 3, 4};
    private int[] higherMap = {4, 3, 3, 3, 3, 3, 2, 2, 1, 0};
    private int[] translation ={0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
    private int[] lowerMiddleMap = {0, 1, 2, 2, 2, 2, 2, 2, 2, 3};
    private int[] higherMiddleMap = {2, 1, 1, 1, 1, 1, 1, 1, 0, 0};
    
    public int evenAllStrobogrammaticCount(int m, int n){
        if(n < m){
            return 0;
        }
        
        if(n % 2 == 1){
            return evenAllStrobogrammaticCount(m, n - 1);
        }
        
        int base = 4 * (int)Math.pow(5, (m / 2) - 1);
        int count = 0;
        for(int i = m; i <= n; i+=2){
            count = count + base;
            base = base * 5;
        }
        return count;
    }
    
    public int oddAllStrobogrammaticCount(int m, int n){
        if(n < m){
            return 0;
        }
        
        if(n % 2 == 0){
            return oddAllStrobogrammaticCount(m, n - 1);
        }
        
        int count = 0;
        if(m == 1){
            count += 3;
            m = 3;
        }
        
        int base = 4 * (int)Math.pow(5, m / 2 - 1) * 3;
        for(int i = m; i <= n; i+=2){
            count = count + base;
            base = base * 5;
        }
        return count;
    }
    
    public int evenStrobogrammaticCount(int n, String edge, boolean lower){
        StringBuilder boundry = new StringBuilder("");
        char c;
        int count = 0;
        
        c = edge.charAt(0);
        int[] map = (lower == true)? lowerMap: higherMap;
        
        if(c == '1' || c == '6' || c == '8' || c == '9'){
            if(lower == true)
                count = (map[c - '0'] - 1) * (int)Math.pow(5, (n / 2)  - 1);
            else
                count = map[c - '0']* (int)Math.pow(5, (n / 2)  - 1);
            boundry.append(c);
        }
        else{
            if(lower == true)
                return (map[c - '0'] - 1) * (int)Math.pow(5, (n / 2)  - 1);
            else
                return map[c - '0'] * (int)Math.pow(5, (n / 2)  - 1);
        }
        System.out.println("count:" + count);
        for(int i = 1; i*2 < edge.length(); i++){
            c = edge.charAt(i);
            if(c == '0' || c == '1' || c == '6' || c == '8' || c == '9'){
                count = count + map[c - '0'] * (int)Math.pow(5, ((n - 2 * i) / 2)  - 1);
                System.out.println("count:" + count);
                boundry.append(c);
            }
            else{
                return count + map[c - '0'] * (int)Math.pow(5, ((n - 2 * i) / 2)  - 1);
            }
        }
        
        int end = boundry.length() - 1;
        int start = boundry.length();
        int i, j;
        for(i = end, j = start; i >= 0; --i, ++j){
            if(lower == true){
                if(translation[boundry.charAt(i) - '0'] < edge.charAt(j) - '0'){
                    return count + 1;
                } 
                if(translation[boundry.charAt(i) - '0'] > edge.charAt(j) - '0'){
                    return count;
                }
            }
            else{
                if(translation[boundry.charAt(i) - '0'] > edge.charAt(j) - '0'){
                    return count + 1;
                }
                if(translation[boundry.charAt(i) - '0'] < edge.charAt(j) - '0'){
                    return count;
                } 
            }
        }
        return count;
    }
    
    public int oddStrobogrammaticCount(int n, String edge, boolean lower){
        StringBuilder boundry = new StringBuilder("");
        char c;
        int count = 0;
        
        c = edge.charAt(0);
        int[] map = (lower == true)? lowerMap: higherMap;
        
        if(edge.length() == 1){
            if(lower == true){
                return lowerMiddleMap[c - '0'];
            }
            else{
                return higherMiddleMap[c - '0'];
            }
        }
        
        if(c == '1' || c == '6' || c == '8' || c == '9'){
            if(lower == true)
                count = (map[c - '0'] - 1) * (int)Math.pow(5, (n / 2)  - 1) * 3;
            else
                count = map[c - '0'] * (int)Math.pow(5, (n / 2)  - 1) * 3;
            boundry.append(c);
        }
        else{
            if(lower == true)
                return (map[c - '0'] - 1) * (int)Math.pow(5, (n / 2)  - 1) * 3;
            else
                return map[c - '0'] * (int)Math.pow(5, (n / 2)  - 1) * 3;
        }
        
        for(int i = 1; i*2 < (edge.length() - 2); i++){
            c = edge.charAt(i);
            if(c == '0' || c == '1' || c == '6' || c == '8' || c == '9'){
                count = count + map[c - '0'] * (int)Math.pow(5, ((n - 2 * i) / 2)  - 1) * 3;
                boundry.append(c);
            }
            else{
                return count + map[c - '0'] * (int)Math.pow(5, ((n - 2 * i) / 2)  - 1) * 3;
            }
        }
        
        c = edge.charAt(edge.length() / 2);
        boundry.append(c);
        int[] middleMap = (lower == true)? lowerMiddleMap: higherMiddleMap;
        count = count + middleMap[c - '0'];
        
        int end = boundry.length() - 2;
        int start = boundry.length();
        int i, j;
        for(i = end, j = start; i >= 0; --i, ++j){
            if(lower == true){
                if(translation[boundry.charAt(i) - '0'] < edge.charAt(j) - '0'){
                    return count + 1;
                }
                if(translation[boundry.charAt(i) - '0'] > edge.charAt(j) - '0'){
                    return count;
                }
            }
            else{
                if(translation[boundry.charAt(i) - '0'] > edge.charAt(j) - '0'){
                    return count + 1;
                }
                if(translation[boundry.charAt(i) - '0'] < edge.charAt(j) - '0'){
                    return count;
                } 
            }
        }
        return count;
    }
    
    public int strobogrammaticInRange(String low, String high) {
        int lowLen = low.length();
        int highLen = high.length();
        int count = 0;  
        BigInteger lowBig = new BigInteger(low);
        BigInteger highBig = new BigInteger(high);
        
        if(lowBig.compareTo(highBig) == 1)
            return 0;
        
        if(lowLen % 2 == 1){
            count += evenAllStrobogrammaticCount(lowLen + 1, highLen);
            count += oddAllStrobogrammaticCount(lowLen, highLen);
            count -= oddStrobogrammaticCount(lowLen, low, true);
        }
        else{
            count += evenAllStrobogrammaticCount(lowLen, highLen);
            count += oddAllStrobogrammaticCount(lowLen + 1, highLen);
            count -= evenStrobogrammaticCount(lowLen, low, true);
        }
        
        if(highLen % 2 == 1){
            count -= oddStrobogrammaticCount(highLen, high, false);
        }
        else{
            count -= evenStrobogrammaticCount(highLen, high, false);
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String low = "50";
        String high = "100";
        int count; 

        count = sol.strobogrammaticInRange(low, high);

        System.out.println("low: " + low);
        System.out.println("high: " + high);
        System.out.println("strobogrammaticWords#: " + count);
    }
}
