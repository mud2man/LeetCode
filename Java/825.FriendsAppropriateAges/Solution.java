/* Time:O(n), Space:O(1)
 * 1. Retrieve input "ages", and get "ageMap", and "ageList"
 * 2. Sort "ageList", and traverse from left
 * 3. Use binary search to find the lower bound, and accumulate the qulified count
 *
 * ex: ages = {20, 30, 100, 100, 120}
 *     ageMap = {20: 1, 30: 1, 100: 2, 120: 1}
 *     ageList = {20, 30, 100, 120}
 *     peopleCount = {1, 2, 4, 5}
 */

import java.util.*;

public class Solution{
    public int numFriendRequests(int[] ages) {
        HashMap<Integer, Integer> ageMap = new HashMap<Integer, Integer>();
        List<Integer> ageList = new ArrayList<Integer>();
        for(int age: ages){
            if(ageMap.containsKey(age)){
                ageMap.put(age, ageMap.get(age) + 1);
            }
            else{
                ageMap.put(age, 1);
                ageList.add(age);
            }
        }
        
        Collections.sort(ageList);
        int count = 0;
        int[] peopleCount = new int[ageList.size()];
        for(int i = 0; i < ageList.size(); ++i){
            int age = ageList.get(i);
            int lb = (age % 2 == 0)? age / 2 + 8 :  (age + 1) / 2 + 7;
            count += (age >= lb)? (ageMap.get(age) - 1) * ageMap.get(age): 0;
            peopleCount[i] = (i == 0)? ageMap.get(age): peopleCount[i - 1] + ageMap.get(age);
            int index = Collections.binarySearch(ageList, lb);
            index = (index >= 0)? index: -(index + 1);
            if(index < i){
                int tooYoungCount = (index > 0)? peopleCount[index - 1]: 0;
                count += (peopleCount[i]- ageMap.get(age) - tooYoungCount) * ageMap.get(age);
            }
        }
        
        return count;
    }

    public static void main(String[] args){
        Solution sol;
        int[] ages = {20, 30, 100, 100, 120};

        sol = new Solution();
        System.out.println("ages: " + Arrays.toString(ages));
        System.out.println("friend requests#: " + sol.numFriendRequests(ages));
    }
}
