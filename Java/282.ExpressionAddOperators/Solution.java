/* Backtrack: Time:O(3^n), Space:O(n)
 * 1. In helper, check if result == target. If so, add path into "results"
 * 2. Then, retrieve the next number from (index + 1) to num.length() of num
 * 3. In '+'/'-', update result with result +/- currentNumber, and update multiply with (-)currentNumber
 * 4. In '*', update result with result - multiply + (multiply * currentNumber), and update multiply with multiply * currentNumber
 */

import java.util.*;
public class Solution{
    private void helper(String num, int index, int target, String path, long result, long multiply, List<String> results){
        if(index == num.length()) {
            if((long)target == result){
                results.add(path);
            }
            return;
        }   
        
        for(int i = index + 1; i <= num.length(); ++i){
            String currentString = num.substring(index, i);
            long currentNumber = Long.parseLong(currentString);
            
            if(num.charAt(index) == '0' && i > (index + 1)){
                break;
            }
            
            if(index == 0){
                helper(num, i, target, path + currentString, currentNumber, currentNumber, results);
            }
            else{
                helper(num, i, target, path + "+" + currentString, result + currentNumber, currentNumber, results);
                helper(num, i, target, path + "-" + currentString, result - currentNumber, -currentNumber, results);
                long nextMultiply = multiply * currentNumber;
                helper(num, i, target, path + "*" + currentString, result - multiply + nextMultiply, nextMultiply, results);
            }
        }
    }
    
    public List<String> addOperators(String num, int target) {
        List<String> results = new ArrayList<String>();
        helper(num, 0, target, "", 0, 0, results);
        return results;
    }

    public static void main(String[] args){
        Solution sol;
        String num = "123";
        int target = 6;

        sol = new Solution();
        System.out.println("num: " + num);
        System.out.println("target: " + target);
        System.out.println("expressions: " + sol.addOperators(num, target));
    }
}
