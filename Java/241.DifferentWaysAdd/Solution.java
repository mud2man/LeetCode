/* Dynamic Programing:
 * 1. dp[y][x] = the combinations between operand#y and operand#x
 * 2. dp[y][x] = dp[y][y]-operator#2-dp[y - 1][x] | dp[y][y - 1]-operator#2-dp[y - 2][x] | ...
 * ex: input: 0 + 1 - 2 * 3
 *     dp[3][0] = (dp[3][3] * dp[2][0]) | (dp[3][2] - dp[1][0]) | (dp[3][1] - dp[0][0])
 */

import java.util.*;

public class Solution{
    private List<Integer> caculate(List<Integer> operands0, char operator, List<Integer> operands1){
        List<Integer> results = new ArrayList<Integer>();
        
        switch (operator){
            case '+':
                for(Integer operand0: operands0){
                    for(Integer operand1: operands1){
                        results.add(operand0 + operand1);
                    }                       
                }
                break;
            case '-':
                for(Integer operand0: operands0){
                    for(Integer operand1: operands1){
                        results.add(operand0 - operand1);
                    }                       
                }
                break;
            case '*':
                for(Integer operand0: operands0){
                    for(Integer operand1: operands1){
                        results.add(operand0 * operand1);
                    }                       
                }
                break;
            default:
                break;
        }
        return results;
    }
    
    public List<Integer> diffWaysToCompute(String input) {
        List<Character> operators = new ArrayList<Character>();
        List<Integer> operands = new ArrayList<Integer>();
        List<Integer> results = new ArrayList<Integer>();
        
        StringBuilder operand = new StringBuilder("");
        for(int i = 0; i < input.length(); ++i){
            char c = input.charAt(i);
            if(c == '+' || c == '-' || c == '*'){
                operators.add(c);
                operands.add(Integer.parseInt(operand.toString()));
                operand = new StringBuilder("");
            }
            else{
                operand.append(c);
            }
        }
        operands.add(Integer.parseInt(operand.toString()));
        
        int size = operands.size();
        List[][] dp = new List[size][size];
        
        for(int y = 0; y < size; ++y){
            dp[y][y] = new ArrayList<Integer>();
            dp[y][y].add(operands.get(y));
            for(int x = y - 1; x >= 0; --x){
                dp[y][x] = new ArrayList<Integer>();
                for(int z = y; z > x; --z){
                    char operator = operators.get(z - 1);
                    dp[y][x].addAll(caculate(dp[z - 1][x], operator, dp[y][z]));
                }
            }
        }
        return dp[size - 1][0];
    }

    public static void main(String[] args){
        List<Integer> answer;
        String s = "21*3-4*5+1";
        Solution sol;
    
        System.out.println("Input: " +  s);
        
        sol = new Solution();
        answer = sol.diffWaysToCompute(s);
        
        System.out.println("Answer: " +  answer);
    }
}
