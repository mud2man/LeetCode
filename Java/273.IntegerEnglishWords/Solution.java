/* Sliding Window: Time:O(n) Space:O(n)
 * 1. Have a helper function "threeDigit" to cover a three digits number to a string. e.g., 123 => One Hundred Twenty Three
 * 2. Slide window from MSB with the window size is three digit
 */

import java.util.*;

public class Solution{
   class Solution {
    private String helper(int num){
        if(num == 0){
            return "";
        }
        
        String[] digits = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven"
                           ,"Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] twoDigits = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        
        String ret = "";
        int tail = num % 100;
        num = num / 100;
        if(tail < 20){
            ret = digits[tail];
        }
        else{
            ret = (tail % 10 > 0)? (twoDigits[tail / 10] + " " + digits[tail % 10]): twoDigits[tail / 10];
        }
        
        if(num > 0){
            ret = (ret.length() > 0)? (digits[num] + " Hundred ") + ret: (digits[num] + " Hundred");
        }
        return ret;
    }
    
    public String numberToWords(int num) {
        if(num == 0){
            return "Zero";
        }
        
        String[] weight = {"", "Thousand", "Million", "Billion"};
        String word = "";
        int weightIdx = 0;
        while(num > 0){
            int tail = num % 1000;
            String tailResult = helper(tail);
            if(tailResult.length() > 0){
                word = (" " + tailResult + " " + weight[weightIdx]) + word;
            }
            num = num / 1000;
            weightIdx++;
        }
        return word.trim();
    }
 
    public static void main(String[] args){
        int num = 1234567;
        Solution sol = new Solution();
        
        System.out.println("num:" + num);
        System.out.println("words:" + sol.numberToWords(num));
    }
}
