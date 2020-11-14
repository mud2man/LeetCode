/* Heap: Time:O(n*logn), Space:O(n)
 * 1. Put the unique starts into sortedStarts and traverse lastTryAttendDay interval by interval (interval = sortedStarts.get(i), sortedStarts.get(i + 1))
 * 2. During the interval traversal, pop out the outdated event and pick the event with earliest end if minHeap is not empty
 * 3. Step 2 is valid, since minHeap only keeps the valid interval
 */     

import java.util.*; // Stack

public class Solution {
    public int maxEvents(int[][] events) {
        Set<Integer> starts = new HashSet<>();
        Map<Integer, List<Integer>> start2Ends = new HashMap<>();
        for(int[] envent: events){
            starts.add(envent[0]);
            start2Ends.computeIfAbsent(envent[0], key -> new ArrayList<>()).add(envent[1]);
        }
        List<Integer> sortedStarts = new ArrayList<>(starts);
        Collections.sort(sortedStarts);
        int lastTryAttendDay = sortedStarts.get(0);
        int count = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i = 1; i <= sortedStarts.size(); i++){
            int end = (i == sortedStarts.size())? Integer.MAX_VALUE: sortedStarts.get(i);
            minHeap.addAll(start2Ends.get(lastTryAttendDay));
            while(lastTryAttendDay < end && !minHeap.isEmpty()){
                popOutDated(minHeap, lastTryAttendDay);
                if(!minHeap.isEmpty()){
                    count++;
                    minHeap.poll();
                }
                lastTryAttendDay++;
            }
            lastTryAttendDay = end;
        }
        return count;
    }
    
    private void popOutDated(PriorityQueue<Integer> minHeap, int lastTryAttendDay){
        while(!minHeap.isEmpty() && minHeap.peek() < lastTryAttendDay){
            minHeap.poll();
        }
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] events = {{1, 4}, {4, 4}, {2, 2}, {3, 4}, {1, 1}};
        System.out.print("events:");
        for(int[] event: events){
            System.out.print(Arrays.toString(event) + ",");
        }
        System.out.println("");
        System.out.println("max number of attendable event:" + sol.maxEvents(events));
    }
}
