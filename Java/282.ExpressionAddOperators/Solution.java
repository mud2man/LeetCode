/* Backtrack: Time:O(n^2 * 3^n), Space:O(n), n^2:for loop and string operation; 3^n: branch of plus, minus, and multiply
 * 1. In the first time, retrieve the first number, and assign it to "multiply", and assign "sum" with 0
 * 2. Theni call backtarck, retrieve the next number from (start + 1) to num.length() of num, and maintain "sum" and "multiply"
 * 3. For '+', update sum with sum + multiply, and multiply with "nextNum", since '+' terminate multiply chain
 * 4. For '-', update sum with sum + multiply, and multiply with "-nextNum", since '-' terminate multiply chain
 * 5. For '*', keep sum the same, and multiply with (multiply * nextNum), since '*' expend multiply chain
 */

import java.util.*;
public class Solution{
    private void backtrack(String num, int start, long sum, long multiply, long target, String path, List<String> answer){
        if(start == num.length()){
            if(sum + multiply == target){
                answer.add(path);
            }
            return;
        }
        
        for(int i = start + 1; i <= num.length(); ++i){
            long nextNum = Long.valueOf(num.substring(start, i));
            backtrack(num, i, sum + multiply, nextNum, target, path + "+" + num.substring(start, i), answer); //'+'
            backtrack(num, i, sum + multiply, -nextNum, target, path + "-" + num.substring(start, i), answer); //'-'
            backtrack(num, i, sum, multiply * nextNum, target, path + "*" + num.substring(start, i), answer); //'*'
            if(nextNum == 0){
                break;
            }
        }
    }
    
    public List<String> addOperators(String num, int target) {
        List<String> answer = new LinkedList<>();
        for(int i = 1; i <= num.length(); ++i){
            long multiply = Long.valueOf(num.substring(0, i));
            backtrack(num, i, 0, multiply, (long)target, num.substring(0, i), answer);
            if(multiply == 0){
                break;
            }
        }
        return answer;
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
