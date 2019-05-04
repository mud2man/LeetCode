/* Recursive: Time:O(n), Space:O(n)
 * 1. The strig must starts from '(', since s = "(" + s + ")";
 * 2. In decode, get the value 'val' first, and get root.left if s.charAt(index[0]) == '('
 * 3. Then get root.left if s.charAt(index[0]) == '('
 */

import java.util.*; // Stack

/* Definition for binary tree */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    //tree starts from '('
    private TreeNode decode(String s, int[] index){
        index[0]++;
        int val = 0;
        boolean isNegative = false;
        if(s.charAt(index[0]) == '-'){
            isNegative = true;
            index[0]++;
        }
        while(s.charAt(index[0]) != '(' && s.charAt(index[0]) != ')'){
            val = val * 10 + (s.charAt(index[0]++) - '0');
        }
        TreeNode root = new TreeNode(isNegative? -val: val);
        root.left = (s.charAt(index[0]) == '(')? decode(s, index): null;
        root.right = (s.charAt(index[0]) == '(')? decode(s, index): null;
        index[0]++;
        return root;
    }
    
    public TreeNode str2tree(String s) {
        if(s.equals("")){
            return null;
        }
        s = "(" + s + ")";
        int[] index = {0};
        return decode(s, index);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "4(2(3)(1))(6(5))";
        TreeNode root = sol.str2tree(s);
        System.out.println("s: " + s);
        sol.preOrder(root); 
        System.out.println("");
    }
}
