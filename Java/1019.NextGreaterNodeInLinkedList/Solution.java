/* Monotonous stack: Time:O(n), Space:O(n)
 * 1. Transform list to array list "nums", and traverse from tail
 * 2. Use monotonous to store the visited node in increasing order
 * 3. Since each node can be visied at most twice (pop on the second visit), the time complexity is O(n)
 */          

import java.util.*; 

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution {
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        ListNode node = head;
        while(node != null){
            nums.add(node.val);
            node = node.next;
        }
        
        Deque<Integer> stack = new LinkedList<>();
        int[] nextLargerNums = new int[nums.size()];
        stack.add(nums.get(nums.size() - 1));
        for(int i = nextLargerNums.length - 2; i >= 0; --i){
            int num = nums.get(i);
            while(!stack.isEmpty() && num >= stack.peekLast()){
                stack.pollLast();
            }
            nextLargerNums[i] = stack.isEmpty()? 0: stack.peekLast();
            stack.add(num);
        }
        return nextLargerNums;
    }
  
    public static void main(String[] args){
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        head.next.next = new ListNode(5);
        Solution sol = new Solution();
        System.out.println("nextLargerNums: " + Arrays.toString(sol.nextLargerNodes(head)));
    }
}
