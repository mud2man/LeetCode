/* Hash: Time:O(n), Space:O(1)
 * 1. Let hash = A[0]*P^2 + A[1]*P^1 + A[0]*P^0, where A.length() = 3
 * 2. We can prove multiply and moudle can be commutative, s.t. (a * b) % c = ((a % c) * b) % c
 * 3. If the two hash are diffenernt, then the two strings must be different
 * 4. We will check on in hashs are the same
 */          

import java.util.*; // Stack

public class Solution {
    public boolean rotateString(String A, String B) {
        if(A.length() != B.length()){
            return false;
        }
        else if(A.length() == 0 && B.length() == 0){
            return true;
        }
        else{
            long hashA = 0;
            long hashB = 0;
            long power = 1;
            long base = 1000000000;
            for(int i = 0; i < A.length(); ++i){
                hashA = (hashA * 256 + (long)A.charAt(i)) % base;
                hashB = (hashB * 256 + (long)B.charAt(i)) % base;
                if(i < A.length() - 1){
                    power = (power * 256) % base; 
                }
            }
        
            String doubleA = A + A;
            for(int i = A.length(); i < doubleA.length(); ++i){
                if(hashA == hashB){
                    if(B.equals(doubleA.substring(i - A.length(), i))){
                        return true;
                    }
                }
                char c = doubleA.charAt(i - A.length());
                // handle underflow, so need to add base back
                hashA = ((hashA - (long)c * power) % base + base) % base; 
                hashA = (hashA * 256 + (long)doubleA.charAt(i)) % base; 
            }
            return false;
        }
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String A = "abcde";
        String B = "cdeab";
        System.out.println("A: " + A);
        System.out.println("B: " + B);
        System.out.println("match? " + sol.rotateString(A, B));
    }
}
