/* Backtrack: Time:O(1), Space:O(1)
 * 1. Have class "Operand" to express number as Fraction
 * 2. Have utility method "operate" to calculate two "Operand, given an operator
 * 3. '( )' pair can be defined by 'operate' order, and every operate is to select two numbers
 * 4. In the backtrack, find all the combinations of two operands, do operate, and pass the reduced numbers to the next backtrack
 * 5. When nums's size is 1, compare the last number with 24
 */

import java.util.*;

public class Solution{
    private class Operand{
        int numerator;
        int denominator;
        Operand(int n, int d){numerator = n; denominator = d;}
    }
    
    private Operand operate(Operand x, Character operator, Operand y){
        Operand z = new Operand(0, 0);
        switch (operator){
            case '+':
                z.numerator = x.numerator * y.denominator + y.numerator * x.denominator;
                z.denominator = y.denominator *  x.denominator;
                break;
            case '-':
                z.numerator = x.numerator * y.denominator - y.numerator * x.denominator;
                z.denominator = y.denominator *  x.denominator;
                break;
            case '*':
                z.numerator = x.numerator * y.numerator;
                z.denominator = y.denominator *  x.denominator;
                break;
            case '/':
                z.numerator = x.numerator * y.denominator;
                z.denominator = y.numerator *  x.denominator;
                break;
        }
        return z;
    }
    
    private boolean backtrack(List<Operand> nums){
        if(nums.size() == 1 && nums.get(0).numerator != 0 && nums.get(0).denominator != 0){
            return (nums.get(0).numerator == 24 * nums.get(0).denominator);
        }
        
        char[] oprs = {'+', '-', '*', '/'};
        for(int i = 0; i < nums.size(); ++i){
            for(int j = 0; j < nums.size(); ++j){
                if(i != j){   
                    for(char opr: oprs){
                        List<Operand> nextNums = new ArrayList<>();
                        Operand opd = operate(nums.get(i), opr, nums.get(j));
                        nextNums.add(opd);
                        for(int k = 0; k < nums.size(); ++k){
                            if(k != i && k != j){
                                nextNums.add(nums.get(k));
                            }
                        }
                        if(backtrack(nextNums)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean judgePoint24(int[] nums) {
        List<Operand> list = new ArrayList<>();
        for(int num: nums){
            list.add(new Operand(num, 1));
        }
        return backtrack(list);
    }

    public static void main(String[] args){
        int[] nums = {4, 1, 8, 7};
        Solution sol = new Solution();

        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("get 24: " + sol.judgePoint24(nums));
    }
}
