/* Stack and greedy algo.
 * 1. Chechk every char "c" in the input string
 * 2. Pop the top until its value is samller or equal to "c"
 * 3. Pop the top until the deleted number is equal to k
 */

import java.util.*; // Stack

public class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack;
        int idx;
        char c;
        StringBuilder min;
        
        stack = new Stack<Character>();
        min = new StringBuilder("");
        
        for(idx = 0; idx < num.length(); idx++){
            c = num.charAt(idx);
            
            while(!stack.isEmpty() && stack.peek() > c && k > 0){
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        
        while(k > 0){
            stack.pop();
            k--;
        }
        
        while(!stack.isEmpty()){
            min.append(stack.pop());
        }
        min.reverse();
        
        while(min.length() > 0 && min.charAt(0) == '0'){
            min.deleteCharAt(0);
        }
        
        if(min.length() == 0){
            return "0";
        }
        else{
            return min.toString();
        }
    }
 
    public static void main(String[] args){
        Solution sol;
        String num = "1432219";
        String rNum ;
        int k;

        sol = new Solution();
        k = 3;

        System.out.println("num: " + num);

        rNum = sol.removeKdigits(num, k);
        
        System.out.println("rNum: " + rNum);
	}
}
