/* Stack: O(n)
 * 1. Have a Stack, and store pair {startTime, period}
 * 2. end time will be aligned with start time as the start of second
 * 3. When the log is start, do push, and update the "period" of the current "top"
 * 4. When the log is end, do pop, and update the "startTime" of the current "top" with endTime
 */

import java.util.*;

public class Solution{
    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<int[]> stack = new LinkedList<>(); //{startTime, period}
        int[] times = new int[n];
        for(String log: logs){
            String[] filed = log.split(":");
            if(filed[1].equals("start")){
                int[] pair = new int[]{Integer.valueOf(filed[2]), 0};
                if(!stack.isEmpty()){
                    stack.peekLast()[1] += (pair[0] - stack.peekLast()[0]);
                }
                stack.addLast(pair);
            }
            else{
                int id = Integer.valueOf(filed[0]);
                int endTime = Integer.valueOf(filed[2]) + 1;
                int[] top = stack.pollLast();
                int execTime = top[1] + (endTime - top[0]);
                times[id] += execTime;
                if(!stack.isEmpty()){
                    stack.peekLast()[0] = endTime;
                }
            }
        }
        return times;
    }

    public static void main(String[] args){
        Solution sol;
        int n = 2;
        List<String> logs = new ArrayList<String>(Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6"));
        sol = new Solution();

        System.out.println("n: " + n);
        System.out.println("logs: " + logs);
        int[] times = sol.exclusiveTime(n, logs);
        System.out.println("times: " + Arrays.toString(times));
    }
}
