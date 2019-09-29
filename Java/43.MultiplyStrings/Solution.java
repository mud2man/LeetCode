/* Time:O(n*m), Space:O(n + m)
 * 1. Caculate subAnswer = integerNum1[i] * integerNum2[j]
 * 2. integerNum3[index] = integerNum3[index] + (subAnswer % 10), where index = i + j
 * 3. integerNum3[index + 1] = integerNum3[index + 1] + (subAnswer / 10);
 * 4. Rferer the gpaph, https://www.pinterest.com/pin/653655333388608611/ 
 */

import java.util.*;
import java.math.*;

public class Solution{
    public String multiply(String num1, String num2) {
        int[] integerNum1 = new int[num1.length()];
        for(int i = num1.length() - 1; i >= 0; --i){
            integerNum1[num1.length() - i - 1] = num1.charAt(i) - '0';
        }
        
        int[] integerNum2 = new int[num2.length()];
        for(int i = num2.length() - 1; i >= 0; --i){
            integerNum2[num2.length() - i - 1] = num2.charAt(i) - '0';
        }
        
        int[] integerNum3 = new int[num1.length() + num2.length()];
        for(int i = 0; i < integerNum1.length; ++i){
            for(int j = 0; j < integerNum2.length; ++j){
                int index = i + j;
                int multiply = integerNum1[i] * integerNum2[j];
                integerNum3[index] = integerNum3[index] + multiply;
            }
        }
        
        int carry = 0;
        for(int i = 0; i < integerNum3.length; ++i){
            int digit = integerNum3[i] + carry;
            integerNum3[i] = digit % 10;
            carry = digit / 10;
        }
        
        StringBuilder answer = new StringBuilder("");
        for(int i = 0; i < integerNum3.length; ++i){
            answer.insert(0, integerNum3[i]);
        }
        while(answer.length() > 1 && answer.charAt(0) == '0'){
            answer.deleteCharAt(0);
        }
        
        return answer.toString();
    }
	
    public static void main(String[] args){
		String num1  = "123";
		String num2  = "454";
		String product  = "";
		Solution sol = new Solution();
		System.out.println("num1: " + num1);
		System.out.println("num2: " + num2);
		System.out.println("product: " + sol.multiply(num1, num2));
	}
}
