/* Slide window + min heap: Time:O(nlogm), Space:O(m), where m is the nums's size
 * 1. Have min heaps "candidates", and "window"
 * 2. Have "values" to record the value in "window", where values[i] is the number from nums[i]
 * 3. Everytime, pick the smallest number "top" from candidates, and update range[1] 
 * 4. Update values[i] with "top", and shift range[0] to right by "window.peek()"
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
        int[] values = new int[length];
        PriorityQueue<Cell> candidates = new PriorityQueue<>(new MinHeapComparator());
        PriorityQueue<Integer> window = new PriorityQueue<>();
        
        for(int i = 0; i < length; ++i){
            int num = nums.get(i).get(0);
            values[i] = num;
            range[0] = Math.min(range[0], num);
            range[1] = Math.max(range[1], num);
            window.add(num);
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
            
            window.add(top.num);
            range[1] = (top.num > range[1])? top.num: range[1];
            window.remove(values[top.outerIdx]);
            values[top.outerIdx] = top.num;
            range[0] = window.peek();

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
