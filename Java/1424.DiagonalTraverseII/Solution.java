/* BFS: Time:O(n*m), Space:(n + m)
 * 1. Keep a pointers of diagonal level
 * 2. Retrieve the current value(nums.get(front[0]).get(front[1])) of current pointer(front), and insert to "numbers"
 * 3. Shift front[1] to right and add front to level
 * 4. Repeat step2/3 until level empty
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<Integer> numbers = new ArrayList<>();
        Deque<int[]> level = new LinkedList<>();
        level.add(new int[]{0, 0});
        while(!level.isEmpty()){
            int size = level.size();
            for(int i = 0; i < size; ++i){
                int[] front = level.pollFirst();
                numbers.add(nums.get(front[0]).get(front[1]));
                if(front[1] == 0 && front[0] + 1 < nums.size()){
                    level.add(new int[]{front[0] + 1, 0});
                }
                if(front[1] + 1 < nums.get(front[0]).size()){
                    front[1]++;
                    level.add(front);
                }
            }
        }
        int[] diagonalOrderNumbers = new int[numbers.size()];
        for(int i = 0; i < numbers.size(); ++i){
            diagonalOrderNumbers[i] = numbers.get(i);
        }
        return diagonalOrderNumbers;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(1, 2, 3));
        nums.add(Arrays.asList(4, 5, 6));
        nums.add(Arrays.asList(7, 8, 9));
        System.out.println("nums:" + nums);
        System.out.println("diagonal order:" + Arrays.toString(sol.findDiagonalOrder(nums)));
    }
}
