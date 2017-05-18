/* O(log(n))
 * 1. Create a 2D array to store all roman character
 * 2. Convert integer from LSB
 *
 * ex: num = 2132
 * time = 0, digit = num % 10 - num % 1 = 2
 * time = 1, digit = num % 100 - num % 10 = 30
 * time = 2, digit = num % 1000 - num % 100 = 100
 * time = 3, digit = num % 10000 - num % 1000 = 2000
 *
 */          

import java.util.*; // Stack

public class Solution {
    public String intToRoman(int num) {
        String roman = "";
        String[][] romans = {{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                             {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                             {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                             {"", "M", "MM", "MMM","MMMM"}};
        int digit, weight;
        
        for(weight = 0; weight < 4; weight++){
            digit = num % (int)Math.pow(10, weight + 1) - num % (int)Math.pow(10, weight);
            digit = digit / (int)Math.pow(10, weight);
            roman = romans[weight][digit] + roman;
        }
        return roman;
    }
 
    public static void main(String[] args){
        Solution sol;
        int num = 2132;

        sol = new Solution();

        System.out.println("num: " + num);
        System.out.println("roman: " + sol.intToRoman(num));
    }
}
