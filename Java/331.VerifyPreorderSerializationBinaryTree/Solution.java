/* Stack: O(n)
 * 1. Traverse the node in the input string and store them into stack
 * 2. If the topest two elements are "#", pop two "#" and third topest node 
 * 3. Then push "#", and check repeatedly until the topest two elements are not "#"
 *
 * ex: 9,3,4,#,#,1,#,#,2,#,6,#,#"
 *    time[0]: 9
 *    time[1]: 9,3
 *    time[2]: 9,3,4
 *    time[3]: 9,3,4,#
 *    time[4]: 9,3,4,#,#
 *    time[5]: 9,3,#
 *    time[6]: 9,1
 *    time[7]: 9,1,#
 *    time[8]: 9,1,#,#
 *    time[9]: 9,#
 *    time[10]: 9,#,2
 *    time[11]: 9,#,2,#
 *    time[12]: 9,#,2,#,6
 *    time[13]: 9,#,2,#,6,#
 *    time[14]: 9,#,2,#,6,#,#
 *    time[15]: 9,#,2,#,#
 *    time[16]: 9,#,#
 *    time[17]: #
 */

import java.util.*;

public class Solution{
    private void pop (LinkedList<String> stack){
        while((stack.size() >= 3) && (stack.peekLast().equals("#")) && 
              (stack.get(stack.size() - 2).equals("#")) && (!stack.get(stack.size() - 3).equals("#"))){
            stack.pollLast();
            stack.pollLast();
            stack.pollLast();
            stack.add("#");
        }    
    }
    
    public boolean isValidSerialization(String preorder) {
        LinkedList<String> stack;
        
        stack = new LinkedList<String>();
        
        for (String node: preorder.split(",")){
            stack.add(node);

            if(node.equals("#")){
                pop(stack);
            }
        }
        
        if((stack.poll().equals("#")) && (stack.size() == 0)){
            return true;
        }
        else{
            return false;
        }
    }

	public static void main(String[] args){
		int i;
		Solution sol;
		boolean isTree;
		String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";

		sol = new Solution();

		System.out.println("preorder: " + preorder);

		isTree = sol.isValidSerialization(preorder);
		
		System.out.println("isTree: " + isTree);
	}
}
