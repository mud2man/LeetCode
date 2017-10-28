/* Sliding Window: Time:O(n) Space:O(n)
 * 1. Have a helper function "threeDigit" to cover a three digits number to a string. e.g., 123 => One Hundred Twenty Three
 * 2. Slide window from MSB with the window size is three digit
 */

import java.util.*;

public class Solution{
    String[] digits = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten","Eleven", 
                       "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen","Nineteen"};
    String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] threeDigitUnit = {"", "Thousand", "Million", "Billion"};
    
    private String threeDigit(int num){ 
        String numStr = "";
        int hundred = num / 100;
        if(hundred > 0){
            numStr += digits[hundred] + " ";
            numStr += "Hundred ";
        }

        num = num % 100;
        if(num < 20 && num > 0){
            numStr += digits[num] + " ";
        }
        else if(num > 0){
            int ten = num / 10;
            numStr += tens[ten] + " ";
            num = num % 10;
            if(num > 0){
                numStr += digits[num] + " ";
            }
        }
        return numStr;
    }
    
    public String numberToWords(int num) {
        String numStr = (num == 0)? "Zero ": "";
        int weight = 1000000000;
        for(int i = 3; i >= 0; --i){
            if((num / weight) > 0){
                if(i > 0){
                    numStr = numStr + threeDigit(num / weight) + threeDigitUnit[i] + " ";
                }
                else{
                    numStr = numStr + threeDigit(num / weight);
                }      
            }
            num = num % weight;
            weight = weight / 1000;
        }
        
        return numStr.substring(0, numStr.length() - 1);
    }

    public static void main(String[] args){
        int num = 1234567;
        Solution sol = new Solution();
        
        System.out.println("num:" + num);
        System.out.println("words:" + sol.numberToWords(num));
    }
}
