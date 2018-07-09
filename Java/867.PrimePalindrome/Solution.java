/* Math: Time:O(1), Space:O(1)
 * 1. Since answer is guaranteed to exist and be less than 2 * 10^8, the number of palidrome <= 9(10^0 + 10^0 + 10^1 + 10^1 + 10^2 + 10^2 + 10^3 + 10^3 + 10^4) < 20000
 * 2. Every palidrom has a root (ex: root of 123321 is 123), we can generate the palidrom given the root and if it's odd length
 * 3. We can traverse the half length of palidrome(from 1 to 5). 
 * 4. Then traverse the least root of odd length and even length, if the palidrom number is prime return it
 */         

import java.util.*;

public class Solution {
    private  boolean isPrime(int num){
        if(num < 2){
            return false;
        }
        int root = (int)Math.sqrt(num);
        for(int i = 2; i <= root; ++i){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
    
    public int primePalindrome(int N) {
        for(int halfLength = 1; halfLength <= 5; ++halfLength){
            //check odd length
            for(int root = (int)Math.pow(10, halfLength - 1); root < (int)Math.pow(10, halfLength); ++root){
                String left = Integer.toString(root);
                StringBuilder right = new StringBuilder("");
                for(int i = left.length() - 2; i >= 0; --i){
                    right.append(left.charAt(i));
                }
                left += right.toString();
                int p = Integer.parseInt(left);
                if(p >= N && isPrime(p)){
                    return p;
                }
            }
            
            //check even length
            for(int root = (int)Math.pow(10, halfLength - 1); root < (int)Math.pow(10, halfLength); ++root){
                String left = Integer.toString(root);
                StringBuilder right = new StringBuilder("");
                for(int i = left.length() - 1; i >= 0; --i){
                    right.append(left.charAt(i));
                }
                left += right.toString();
                int p = Integer.parseInt(left);
                if(p >= N && isPrime(p)){
                    return p;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Solution sol= new Solution();
        int n = 13;
        System.out.println("n: " + n);
        System.out.println("least prime palindrome: " + sol.primePalindrome(n));
    }
}
