/* MinMax: Time:O(n^2*logn), Space:O(n)
 * 1. Have a solution set candidates, keeping guess word until candidates is empty
 * 2. In "minMaxGuess", our goal is to find the word which has the minimum largest group size regarding the current candidates pool "candidates"
 * 3. Since the word character is picked randomly, we can say the each word's group size is similar
 * 4. We can reduce the size of "candidates" to 1/6 of its original size. So the time complexity is O(n^2*logn)
 */

import java.util.*;

public class Solution{
    interface Master {
         public int guess(String word);
    }

    private int minMaxGuess(List<Integer> candidates, int[][] matches){
        int selected = -1;
        int globalMaxGroupSize = Integer.MAX_VALUE;
        for(int candidate: candidates){
            List<Integer> groupSize = new ArrayList<>();
            for(int i = 0; i < 7; ++i){
                groupSize.add(0);
            }
            int localMaxGroupSize = 0;
            for(int other: candidates){
                int count = matches[candidate][other];
                groupSize.set(count, groupSize.get(count) + 1);
                localMaxGroupSize = (groupSize.get(count) > localMaxGroupSize)? groupSize.get(count): localMaxGroupSize;
            }
            if(localMaxGroupSize < globalMaxGroupSize){
                globalMaxGroupSize = localMaxGroupSize;
                selected = candidate;
            }
        }
        return selected;
    }
    
    public void findSecretWord(String[] wordlist, Master master) {
        int[][] matches = new int[wordlist.length][wordlist.length];
        List<Integer> candidates = new ArrayList<>();
        for(int i = 0; i < wordlist.length; ++i){
            for(int j = 0; j < wordlist.length; ++j){
                for(int k = 0; k < 6; ++k){
                    char c0 = wordlist[i].charAt(k);
                    char c1 = wordlist[j].charAt(k);
                    matches[i][j] += (c0 == c1)? 1: 0;
                }
            }
            candidates.add(i);
        }
        
        Set<Integer> guessed = new HashSet<>();
        while(!candidates.isEmpty()){
            int i = minMaxGuess(candidates, matches);
            guessed.add(i);
            String word = wordlist[i];
            int count = master.guess(word);
            if(count == 6){
                break;
            }
            List<Integer> nextCandidates = new ArrayList<>();
            for(int j: candidates){
                if(matches[i][j] == count && !guessed.contains(j)){
                    nextCandidates.add(j);
                }
            }
            candidates = nextCandidates;
        }
    }
 
    public static void main(String[] args){
        System.out.println("no example");
    }
}
