/* Slide window + min heap: Time:O(nlogm), Space:O(m), where m is the nums's size
 * 1. Have a min heap "candidates"
 * 2. In the begining, put the first num from each list
 * 3. Everytime, take out the smallest number "top" from candidates, and put in its successor
 * 4. Update range[0/1]
 * 5. Repeat step3/4 as long as the candidates's size is equal to K
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
        PriorityQueue<Cell> candidates = new PriorityQueue<>(new MinHeapComparator());
        
        for(int i = 0; i < length; ++i){
            int num = nums.get(i).get(0);
            range[0] = Math.min(range[0], num);
            range[1] = Math.max(range[1], num);
            candidates.add(new Cell(num, i, 0));
        }
        
        int[] minRange = {range[0], range[1]};
        while(candidates.size() == length){
            Cell top = candidates.poll();
            if(top.innerIdx == nums.get(top.outerIdx).size() - 1 || candidates.isEmpty()){
                break;
            }
            range[0] = candidates.peek().num;
            Cell next = new Cell(nums.get(top.outerIdx).get(top.innerIdx + 1), top.outerIdx, top.innerIdx + 1);
            candidates.add(next);
            range[1] = Math.max(range[1], next.num);
            range[0] = Math.min(range[0], next.num);
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
