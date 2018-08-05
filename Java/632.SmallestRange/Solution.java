/* Slide window + min heap: Time:O(nlogn), Space:O(n), LeetCode has O(nlogm) solution
 * 1. Have min heaps "candidates", and "window"
 * 2. Have "count" to record the number of element in "window", where count[i] is the number of elements from nums[i] in "window"
 * 3. Everytime, pick the smallest number from candidates, and update range[1] 
 * 4. Poll element from "window", and shift range[0] to right
 */

import java.util.*;

public class Solution{
    private class Cell{
        int num;
        int outerIdx;
        int innerIdx;
        Cell(int n, int o, int i){num = n; outerIdx = o; innerIdx = i;}
    }
    
    private class MinHeapComparator implements Comparator<Cell>{
        public int compare(Cell x, Cell y){
            return x.num - y.num;
        }    
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        int length = nums.size();
        int[] range = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] count = new int[length];
        PriorityQueue<Cell> candidates = new PriorityQueue<>(new MinHeapComparator());
        PriorityQueue<Cell> window = new PriorityQueue<>(new MinHeapComparator());
        
        for(int i = 0; i < length; ++i){
            int num = nums.get(i).get(0);
            count[i]++;
            range[0] = Math.min(range[0], num);
            range[1] = Math.max(range[1], num);
            window.add(new Cell(nums.get(i).get(0), i, 0));
            if(nums.get(i).size() > 1){
                candidates.add(new Cell(nums.get(i).get(1), i, 1));
            }
        }
        
        int[] minRange = {range[0], range[1]};
        while(!candidates.isEmpty()){
            Cell top = candidates.poll();
            if(nums.get(top.outerIdx).size() > top.innerIdx + 1){
                candidates.add(new Cell(nums.get(top.outerIdx).get(top.innerIdx + 1), top.outerIdx, top.innerIdx + 1));
            }
            
            window.add(top);
            range[1] = (top.num > range[1])? top.num: range[1];
            count[top.outerIdx]++;
            while(count[window.peek().outerIdx] > 1){
                count[window.poll().outerIdx]--;
            }
            range[0] = window.peek().num;

            if(range[1] - range[0] < minRange[1] - minRange[0]){
                minRange[0] = range[0];
                minRange[1] = range[1];
            }
        }
        
        return minRange;
    }

    public static void main(String[] args){
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(4, 10, 15, 24, 26));
        nums.add(Arrays.asList(0, 9, 12, 20));
        nums.add(Arrays.asList(5, 18, 22, 30));
        Solution sol = new Solution();

        System.out.println("nums:" + nums);
        System.out.println("smallest range:" + Arrays.toString(sol.smallestRange(nums)));
    }
}
