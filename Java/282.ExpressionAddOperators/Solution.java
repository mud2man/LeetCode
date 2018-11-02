/* Backtrack: Time:O(n^2 * 3^n), Space:O(n), n^2:for loop and string operation; 3^n: branch of plus, minus, and multiply
 * 1. In the first time, retrieve the first number, and assign it to "multiply", and assign "sum" with 0
 * 2. Theni call backtarck, retrieve the next number from (start + 1) to num.length() of num, and maintain "sum" and "multiply"
 * 3. For '+', update sum with sum + multiply, and multiply with "nextNum", since '+' terminate multiply chain
 * 4. For '-', update sum with sum + multiply, and multiply with "-nextNum", since '-' terminate multiply chain
 * 5. For '*', keep sum the same, and multiply with (multiply * nextNum), since '*' expend multiply chain
 */

import java.util.*;
public class Solution{
    private void backtarck(long sum, long multiply, String num, int start, long target, String path, List<String> ret){
        if(start == num.length()){
            if(sum + multiply == target){
                ret.add(path);
            }
            return;
        }
        
        for(int end = start + 1; end <= num.length(); ++end){
            String nextNum = num.substring(start, end);
            //plus
            String plusPath = path + "+" + nextNum;
            long nextSum = sum + multiply;
            long nextMultiply = Long.valueOf(nextNum);
            backtarck(nextSum, nextMultiply, num, end, target, plusPath, ret);
            
            //minus
            String minusPath = path + "-" + nextNum;
            nextSum = sum + multiply;
            nextMultiply = -Long.valueOf(nextNum);
            backtarck(nextSum, nextMultiply, num, end, target, minusPath, ret);
            
            //multiply
            String multilpyPath = path + "*" + nextNum;
            nextSum = sum;
            nextMultiply = multiply*Long.valueOf(nextNum);
            backtarck(nextSum, nextMultiply, num, end, target, multilpyPath, ret);
            
            if(num.charAt(start) == '0'){
                break;
            }
        }
    }
    
    public List<String> addOperators(String num, int target) {
        List<String> ret = new ArrayList<>();
        for(int i = 1; i <= num.length(); ++i){
            long multiply = Long.valueOf(num.substring(0, i));
            backtarck(0L, multiply, num, i, (long)target, num.substring(0, i), ret);
            if(num.charAt(0) == '0'){
                break;
            }
        }
        return ret;
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
