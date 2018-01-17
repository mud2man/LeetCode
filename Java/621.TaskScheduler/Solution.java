/* Greedy + MaxHeap: Time:O(n), Space:O(n), LeetCode has a tricky solution
 * 1. Have a maximum heap "tasksQueue" to store the task which denoted as a pair = (taskId, count)
 * 2. Service tasks every period(n + 1), and update tasksQueue every loop
 * 3. In update, take the most frequency task for "period" time, and decrease the count of selected task 1 each time
 */         

import java.util.*;

public class Solution {
    private void update(PriorityQueue<int[]> tasksQueue, int period){
        List<int[]> servicedTasks = new ArrayList<int[]>();
        
        while(tasksQueue.size() > 0 && period > 0){
            int[] task = tasksQueue.poll();
            task[1]--;
            if(task[1] > 0){
                servicedTasks.add(task);
            }
            period--;
        }
        
        for(int[] task: servicedTasks){
            tasksQueue.add(task);
        }
    }
    
    private class TaskComparator implements Comparator<int[]>{
        public int compare(int[] x, int[] y){
            return y[1] - x[1];
        }
    }
    
    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        PriorityQueue<int[]> tasksQueue = new PriorityQueue<int[]>(new TaskComparator());
        int period = n + 1;
        int cpuCycles = 0;
        
        for(char task: tasks){
            counts[task - 'A']++;
        }
        
        for(int i = 0; i < counts.length; ++i){
            if(counts[i] > 0){ 
                tasksQueue.add(new int[]{i, counts[i]});
            }
        }
        
        while(tasksQueue.peek()[1] > 1){
            cpuCycles += period;
            update(tasksQueue, period);
        }
        
        cpuCycles += tasksQueue.size();
        return cpuCycles;
    }

    public static void main(String[] args){
        Solution sol;
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        sol = new Solution();

        System.out.println("tasks: " + Arrays.toString(tasks));
        System.out.println("n: " + n);
        System.out.println("least intervals: " + sol.leastInterval(tasks, n));
    }
}
