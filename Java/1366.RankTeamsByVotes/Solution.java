/* Sort: Time:O(n) Space:O(1)
 * 1. Construct "char2Pos2Count" and transform it to the list of CharPosCountPairs and sort it by CharPosCountPair.posCounts.getKey()
 * 2. Sort CharPosCountPairs by the rank terms defined by description
 */

import java.util.*; // Stack

/* Definition for binary tree */
public class Solution {
    private class CharPosCountPair{
        char c;
        List<Map.Entry<Integer, Integer>> posCounts;
        CharPosCountPair(char c, Set<Map.Entry<Integer, Integer>> set){
            this.c = c; 
            posCounts = new ArrayList<>(set);
        }
    }
    
    public String rankTeams(String[] votes) {
        if(votes.length == 1){
            return votes[0];
        }
        
        Map<Character, Map<Integer, Integer>> char2Pos2Count = new HashMap<>();
        for(String vote: votes){
            for(int pos = 0; pos < vote.length(); pos++){
                Map<Integer, Integer> pos2Count = char2Pos2Count
                    .computeIfAbsent(vote.charAt(pos), key -> new HashMap<>());
                pos2Count.put(pos, pos2Count.getOrDefault(pos, 0) + 1);
            }
        }
        List<CharPosCountPair> charPosCountPairs = new ArrayList<>();
        for(Map.Entry<Character, Map<Integer, Integer>> entry: char2Pos2Count.entrySet()){
            CharPosCountPair charPosCountPair = new CharPosCountPair(
                entry.getKey(), entry.getValue().entrySet());
            Collections.sort(charPosCountPair.posCounts, (x, y) -> (x.getKey() - y.getKey()));
            charPosCountPairs.add(charPosCountPair);
        }
        Collections.sort(charPosCountPairs, (x, y) -> {
            int len = Math.min(x.posCounts.size(), y.posCounts.size());
            for(int i = 0; i < len; i++){
                if(x.posCounts.get(i).getKey() == y.posCounts.get(i).getKey()){
                    if(x.posCounts.get(i).getValue() == y.posCounts.get(i).getValue()){
                        continue;
                    }else{
                        return y.posCounts.get(i).getValue() - x.posCounts.get(i).getValue();
                    }
                }else{
                    return x.posCounts.get(i).getKey() - y.posCounts.get(i).getKey();
                }
            }
            return (x.posCounts.size() > len)? -1: (y.posCounts.size() > len)? 1:  (int)(x.c) - (int)(y.c);
        });
        StringBuilder sb = new StringBuilder("");
        for(CharPosCountPair charPosCountPair: charPosCountPairs){
            sb.append(charPosCountPair.c);
        }
        return sb.toString();
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] votes = {"ABC", "ACB", "ABC", "ACB", "ACB"};
        System.out.println("votes:" + Arrays.toString(votes));
        System.out.println("rank:" + sol.rankTeams(votes));
    }
}
