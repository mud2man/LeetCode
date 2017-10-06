/* Stack: O(n)
 */

import java.util.*;

public class Solution{
    private class Log{
        int id;
        boolean isEnd;
        int time;
        Log(int i, boolean is, int t){id = i; isEnd = is; time = t;}
    }
    
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] times = new int[n];
        Stack<Log> stack = new Stack<Log>();

        for(String log: logs){
            String[] parameters = log.split(":");
            int id = Integer.parseInt(parameters[0]);
            boolean isEnd = parameters[1].equals("end")? true: false;
            int time = Integer.parseInt(parameters[2]);
            
            if(isEnd){
                Log top = stack.pop();
                times[id] = times[id] + (time - top.time + 1);

                if(!stack.isEmpty()){
                    stack.peek().time = time + 1;
                }
            }
            else{
                if(!stack.isEmpty()){
                    Log top = stack.peek();
                    times[top.id] = times[top.id] + (time - top.time);
                    top.time = time;
                }
                
                stack.push(new Log(id, isEnd, time));
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
