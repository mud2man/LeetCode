/*Stack: Time:O(n), Space:O(n)
 * 1. Have a "variableMap" to store the assigned variables, with key is variable name, value is the list of assigned value
 * 2. Hvae a another polymorphism "evaluate" to evaluate the 5 types of expression, incldsing integer, variable, let, add, and mult 
 * 3. In let, do assign new value to "variableMap", and remove them when in the end of let
 * 4. For others, simply call "evaluate" and do operation like add and multiply
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
   private String getNextToken(String expr, int[] idx){
        String token = "";
        while(idx[0] < expr.length() && expr.charAt(idx[0]) != ' ' && expr.charAt(idx[0]) != ')'){
            token += Character.toString(expr.charAt(idx[0]++));
        }
        idx[0] += (idx[0] < expr.length() && expr.charAt(idx[0]) == ' ')? 1: 0;
        return token;
    }

    private int evaluate(String expr, int[] idx, Map<String, Deque<Integer>> variableMap){
        idx[0] += (expr.charAt(idx[0]) == '(')? 1: 0;
        String operator = getNextToken(expr, idx);
        int ret = 0;
        switch (operator){
            case "let":
                Set<String> variables = new HashSet<>();
                while(expr.charAt(idx[0]) != ')'){
                    String variable = "";
                    if(Character.isLetter(expr.charAt(idx[0]))){
                        variable = getNextToken(expr, idx);
                    }
                    else{
                        ret = evaluate(expr, idx, variableMap);
                        idx[0]++;
                        break;
                    }
                    
                    if(expr.charAt(idx[0]) == ')'){
                        ret = variableMap.get(variable).peekLast();
                        idx[0]++;
                        break;
                    }
                            
                    int value = evaluate(expr, idx, variableMap);
                    variableMap.putIfAbsent(variable, new LinkedList<>());
                    variableMap.get(variable).add(value);
                    variables.add(variable);
                    idx[0] += (idx[0] < expr.length() && expr.charAt(idx[0]) == ' ')? 1: 0;
                }
                
                for(String variable: variables){
                    variableMap.get(variable).pollLast();
                }
                break;
            case "mult":
            case "add":
                int expr0 = evaluate(expr, idx, variableMap);
                idx[0] += (idx[0] < expr.length() && expr.charAt(idx[0]) == ' ')? 1: 0;
                int expr1 = evaluate(expr, idx, variableMap);
                idx[0] ++;
                ret = operator.equals("mult")? expr0 * expr1: expr0 + expr1;
                break;
            default:
                if(Character.isDigit(operator.charAt(0)) || operator.charAt(0) == '-'){
                    ret = Integer.valueOf(operator);
                }
                else{
                    ret = variableMap.get(operator).peekLast();
                    idx[0] += (idx[0] < expr.length() && expr.charAt(idx[0]) == ' ')? 1: 0;
                }
        }
        return ret;
    }
    
    public int evaluate(String expr) {
        Map<String, Deque<Integer>> variableMap = new HashMap<>();
        int[] idx = {0};
        return evaluate(expr, idx, variableMap);
    }  
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String expr = "(let x 2 (mult x 5))";
        System.out.println("expr: " + expr);
        System.out.println("result: " + sol.evaluate(expr));
    }
}
