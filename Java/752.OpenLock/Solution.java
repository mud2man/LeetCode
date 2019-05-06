/* BFS: Time:O(1), Space:O(1)
 * 1. Have a hashset "visited" to record the visited status and deadends
 * 2. Use BFS to find the shortest distance, without revisiting
 */

import java.util.*; // Stack

public class Solution {
    private List<String> getNextStatus(String currentStatus, HashSet<String> visited){
        List<String> ret = new LinkedList<String>();
        StringBuilder sb = new StringBuilder(currentStatus);
        int[] shifts = {1, -1};
        for(int i = 0; i < 4; ++i){
            char c = sb.charAt(i);
            for(int shift: shifts){
                int digit = (c - '0' + shift) % 10;
                digit = (digit == -1)? 9: digit;
                sb.setCharAt(i, (char)(digit + '0'));
                String nextStatus = sb.toString();
                if(!visited.contains(nextStatus)){
                    visited.add(nextStatus);
                    ret.add(nextStatus);
                }
            }
            sb.setCharAt(i, c);
        }
        return ret;
    }
    
    public int openLock(String[] deadends, String target) {
        HashSet<String> visited = new HashSet<String>();
        for(String deadend: deadends){
            if(deadend.equals("0000")){
                return -1;
            }else{
                visited.add(deadend); 
            }
            
        }
        visited.add("0000");
        int distance = 0;
        Deque<String> queue = new LinkedList<String>();
        queue.add("0000");
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                String currentStatus = queue.pollFirst();
                visited.add(currentStatus);
                if(currentStatus.equals(target)){
                    return distance;
                }else{
                    queue.addAll(getNextStatus(currentStatus, visited));
                }
            }
            distance++;
        }
        return -1;
    }

    public static void main(String[] args){
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        Solution sol = new Solution();
        System.out.println("deadends: " + Arrays.toString(deadends));
        System.out.println("target: " + target);
        System.out.println("shortest distance: " + sol.openLock(deadends, target));
    }
}
