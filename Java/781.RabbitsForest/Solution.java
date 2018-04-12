/* Math + Hash: Time:O(n), Space:O(n)
 * 1. Record the group number in terms of the group size
 * 2. All groups are independent, so the minimum number can be the sum of the minimum number of each group
 * 3. And the minimum number of the group is ("the number of answer" - 1) / (answer + 1) + 1;
 */

import java.util.*; // Stack

public class Solution {
    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int answer: answers){
            map.putIfAbsent(answer, 0);
            map.put(answer, map.get(answer) + 1);
        }
        
        int minCount = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            int groupNum = (value - 1) / (key + 1) + 1;
            minCount += (key + 1) * groupNum;
        }
        
        return minCount;
    }

    public static void main(String[] args){
        Solution sol;
        int[] answers = {1, 1, 2};

        sol = new Solution();
        System.out.println("answers: " + Arrays.toString(answers));
        System.out.println("minimum number of rabbits: " + sol.numRabbits(answers));
    }
}
