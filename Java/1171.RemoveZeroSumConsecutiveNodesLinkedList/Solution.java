/* Stack: Time:O(n), Space:O(n)
 * 1. Have a stack to store the elements which not contains zero-sum consecutive nodes
 * 2. Have a map to record of the sum-count values
 * 3. If nums[i] can contribute a zero-sum ending at nums[i], then {nums of left part} - nums[i] = sum.
 * 4. So the target we are looking for is sum + nums[i]
 * 5. If target exist, we pop the nums from tail where their sum == -nums[i]
 * 6. Reconstruct list from stack
 */

import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Solution{
    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, Integer> sum2Count = new HashMap<>();
        sum2Count.put(0, 1);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode itr = head;
        int sum = 0;
        while(itr != null){
            int currVal = itr.val;
            if(currVal != 0){
                int target = sum + currVal;
                if(sum2Count.containsKey(target) && sum2Count.get(target) > 0){
                    int sumFromTail = 0;
                    while(!stack.isEmpty()){
                        sum2Count.put(sum - sumFromTail, sum2Count.get(sum - sumFromTail) - 1);
                        sumFromTail += stack.pollLast().val;
                        if(sumFromTail == -currVal){
                            sum -= sumFromTail;
                            break;   
                        }
                    }
                }else{
                    sum += currVal;
                    sum2Count.putIfAbsent(sum, 0);
                    sum2Count.put(sum, sum2Count.get(sum) + 1);
                    stack.add(itr);
                }
            }
            itr = itr.next;
        }
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        while(!stack.isEmpty()){
            prev.next = stack.peekFirst();
            prev = stack.pollFirst();
            prev.next = null;
        }
        return dummy.next;
    }
    
    private void dump(ListNode head){
        while(head != null){
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("");
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(-3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(1);
        sol.dump(head);
        head = sol.removeZeroSumSublists(head);
        System.out.println("after remove:");
        sol.dump(head);
    }
}
