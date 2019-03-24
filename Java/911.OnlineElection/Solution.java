/* Map: Time:O(n + mlogn), Space:O(n), where n is vote# and m is query# 
 * 1. In "TopVotedCandidate", have personToVote to record person's vote and update timeToLeader which record the leader given time
 * 2. In "q", use "floorKey" to binary search the winner given time
 */

import java.util.*;

public class Solution{
    TreeMap<Integer, Integer> timeToLeader;
    
    public Solution(int[] persons, int[] times) {
        timeToLeader = new TreeMap<>();
        int[] leader = {-1, 0};
        Map<Integer, Integer> personToVote = new HashMap<>();
        for(int i = 0; i < persons.length; ++i){
            int candidate = persons[i];
            personToVote.putIfAbsent(candidate, 0);
            personToVote.put(candidate, personToVote.get(candidate) + 1);
            if(personToVote.get(candidate) >= leader[1]){
                leader[0] = candidate;
                leader[1] = personToVote.get(candidate);
            }
            timeToLeader.put(times[i], leader[0]);
        }
    }
    
    public int q(int t) {
        Integer floorKey = timeToLeader.floorKey(t);
        return timeToLeader.get(floorKey);
    }
  
    public static void main(String[] args){
        int[] persons = {0, 1, 1, 0, 0, 1, 0};
        int[] times = {0, 5, 10, 15, 20, 25, 30};
        int[] query = {3, 12, 25, 15, 24, 8};
        System.out.println("persons: " + Arrays.toString(persons));
        System.out.println("times: " + Arrays.toString(times));
        System.out.println("query: " + Arrays.toString(query));
        Solution sol = new Solution(persons, times);
        for(int q: query){
            System.out.println(String.format("time=%d, leader=%d", q, sol.q(q)));
        }
    }
}
