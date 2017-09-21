/* Dynamic programming: O(sequence#), but backtrack is better
 * statistics[i] = all sub sequences ended with i-th smallest number
 * First, have a sorted set comming from nums
 * Second, tarverse nums and all the sequences in statistics[i] with append current num, where 0<=i<=endIdx to statics[endIdx]
 * FInally, retrieve the result with the length of list > 1
 */

import java.util.*;

public class Solution{
    private class Statistics{
        int biggest;
        List<List<Integer>> increasingLists;
        Statistics(int b){biggest = b; increasingLists = new ArrayList<List<Integer>>();}
    }
    
    private String getId(List<Integer> list){
        String str = "";
        for(int num: list){
            str = str + Integer.toString(num);
            str = str + ",";
        }
        return str;
    }
    
    public List<List<Integer>> findSubsequences(int[] nums) {
        SortedSet<Integer> set = new TreeSet<Integer>();
        for(int num: nums){
            set.add(num);
        }
        
        Statistics[] statistics = new Statistics[set.size()];
        HashMap<Integer, Integer> positionMap = new HashMap<Integer, Integer>();
        int index = 0;
        for(int num: set){
            statistics[index] = new Statistics(num);
            positionMap.put(num, index);
            index++;
        }
        
        for(int num: nums){
            int endIdx = positionMap.get(num);
            List<List<Integer>> newLists = new ArrayList<List<Integer>>();
            for(int i = 0; i <= endIdx; ++i){
                List<List<Integer>> currLists = statistics[i].increasingLists;
                List<List<Integer>> cloneLists = new ArrayList<List<Integer>>();
                for(List<Integer> currlist: currLists){
                    List<Integer> cloneList = new ArrayList(currlist);
                    cloneList.add(num);
                    cloneLists.add(cloneList);
                }
                newLists.addAll(cloneLists);
            }
            
            List<Integer> singleList = new ArrayList<Integer>();
            singleList.add(num);
            newLists.add(singleList);
            statistics[endIdx].increasingLists.addAll(newLists);
        }
        
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        Set<String> used = new HashSet<String>();
        for(Statistics statistic: statistics){
            List<List<Integer>> currLists = statistic.increasingLists;
            for(List<Integer> currList: currLists){
                String listId = getId(currList);
                if(!used.contains(listId) && currList.size() > 1){
                    used.add(listId);
                    answer.add(currList);
                }
            }
        }
        
        return answer;
    }

    public static void main(String[] args){
        Solution sol;
        List<Integer> topK;
        int[] nums = {4, 6, 7, 7};
        List<List<Integer>> subSeqs;

        sol = new Solution();

        System.out.println("nums: " + Arrays.toString(nums));

        subSeqs = sol.findSubsequences(nums);
        
        System.out.println("subSeqs: ");
        for(List<Integer>subSeq : subSeqs)
            System.out.println(subSeq);
    }
}
