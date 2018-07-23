/* Backtrack: Time:O(1), Space:O(1). Need LeetCode's shorter answer
 * 1. Use backtrack to find all the combinations, and get the result
 */

import java.util.*;

public class Solution{
    String[] oprs = {"+", "-", "*", "/"};
    
    private class Cell{
        String expr;
        int numerator;
        int denominator;
        String opr;
        Cell(String s){expr = s;}
    }
    
    private void operate(Cell cell){
        String expr = cell.expr;
        Stack<int[]> stack = new Stack<>();
        char prevOpr = '+';
        for(int i = 0; i < expr.length(); ++i){
            char c = expr.charAt(i);
            if(Character.isDigit(c)){
                int n = c - '0';
                switch (prevOpr){
                    case '+':
                        stack.push(new int[]{n, 1});
                        break;
                    case '-':
                        stack.push(new int[]{-n, 1});
                        break;
                    case '*':
                        stack.peek()[0] =  stack.peek()[0] * n;
                        break;
                    case '/':
                        stack.peek()[1] =  stack.peek()[1] * n;
                        break;
                }
            }
            else{
                prevOpr = c;
            }
        }
        
        cell.denominator = 1;
        cell.numerator = 0;
        while(!stack.isEmpty()){
            int[] top = stack.pop();
            cell.numerator = cell.numerator * top[1] + top[0] * cell.denominator;
            cell.denominator =  top[1] * cell.denominator;
        }
    }
    
    private int calculate(LinkedList<Cell> stack){
        Cell prevTop = null;
        while(!stack.isEmpty()){
            Cell top = stack.pollLast();
            operate(top);
            if(prevTop != null){
                String opr = top.opr;
                if(opr.equals("+")){
                    top.numerator = top.numerator * prevTop.denominator + prevTop.numerator * top.denominator;
                    top.denominator = top.denominator * prevTop.denominator;
                }
                else if(opr.equals("-")){
                    top.numerator = top.numerator * prevTop.denominator - prevTop.numerator * top.denominator;
                    top.denominator =  top.denominator * prevTop.denominator;
                }
                else if(opr.equals("*")){
                    top.denominator =  top.denominator * prevTop.denominator;
                    top.numerator = top.numerator * prevTop.numerator;
                }
                else{
                    top.denominator =  top.denominator * prevTop.numerator;
                    top.numerator = top.numerator * prevTop.denominator;
                }
            }
            prevTop = top;
        }
        if(prevTop.denominator == 0 || (24 * prevTop.denominator) != prevTop.numerator){
            return 0;
        }
        else{
            return 24;
        }
    }

    private boolean backtrack(int[] nums, int idx, LinkedList<Cell> stack){
        if(idx == nums.length){
            int ret = calculate(stack);
            return (ret == 24);
        }
        
        LinkedList<String> exprs = new LinkedList<>();
        for(int i = idx; i < nums.length; ++i){
            if(i == idx){
                exprs.add(Integer.toString(nums[i]));
            }
            else{
                int size = exprs.size();
                for(int j = 0; j < size; ++j){
                    String top = exprs.poll();
                    for(String opr: oprs){
                        exprs.add(top + opr + Integer.toString(nums[i]));
                    }
                }
            }
            
            for(String expr: exprs){
                boolean ret = false;
                if(i == nums.length - 1){
                    Cell cell = new Cell(expr);
                    LinkedList<Cell> nextStack = new LinkedList<>(stack);
                    nextStack.add(cell);
                    if(backtrack(nums, i + 1, nextStack)){
                        return true;
                    }
                }
                else{
                    for(String opr: oprs){
                        Cell cell = new Cell(expr);
                        cell.opr = opr;
                        LinkedList<Cell> nextStack = new LinkedList<>(stack);
                        nextStack.add(cell);
                        if(backtrack(nums, i + 1, nextStack)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean helper(List<Integer> list, List<Integer> path){
        if(path.size() == 4){
            int[] nums = new int[4];
            for(int i = 0; i < 4; ++i){
                nums[i] = path.get(i);
            }
            if(backtrack(nums, 0, new LinkedList<Cell>())){
                System.out.println(Arrays.toString(nums));
                return true;
            }
            else{
                return false;
            }
        }
        
        for(int i = 0; i < list.size(); ++i){
            int num = list.get(i);
            list.remove(i);
            path.add(num);
            if(helper(list, path)){
                return true;
            }
            path.remove(path.size() - 1);
            list.add(i, num);
        }
        return false;
    }
    
    public boolean judgePoint24(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int num: nums){
            list.add(num);
        }
        return helper(list, new ArrayList<>());
    }
 
    public static void main(String[] args){
        int[] nums = {4, 1, 8, 7};
        Solution sol = new Solution();

        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("get 24: " + sol.judgePoint24(nums));
    }
}
