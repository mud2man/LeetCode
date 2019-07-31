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

    private int minMaxGuess(List<Integer> candidates, int[][] matchCounts){
        int max = Integer.MAX_VALUE;
        int candidate = -1;
        for(int x: candidates){
            int[] group = new int[7];
            int localMax = 0;
            for(int y: candidates){
                group[matchCounts[x][y]]++;
                localMax = Math.max(localMax, group[matchCounts[x][y]]);
            }
            if(localMax < max){
                max = localMax;
                candidate = x;
            }
        }
        return candidate;
    }
    
    public void findSecretWord(String[] wordlist, Master master) {
        int length = wordlist.length;
        int[][] matchCounts = new int[length][length];
        List<Integer> candidates = new ArrayList<>();
        for(int i = 0; i < length; ++i){
            candidates.add(i);
            for(int j = 0; j < length; ++j){
                int count = 0;
                for(int k = 0; k < 6; ++k){
                    count += (wordlist[i].charAt(k) == wordlist[j].charAt(k))? 1: 0;
                }
                matchCounts[i][j] = count;
            }
        }
        
        while(!candidates.isEmpty()){
            int candidate = minMaxGuess(candidates, matchCounts);
            int matchCount = master.guess(wordlist[candidate]);
            if(matchCount == 6){
                return;
            }
            List<Integer> nextCandidates = new ArrayList<>();
            for(int other: candidates){
                int count = matchCounts[candidate][other];
                if(count == matchCount){
                    nextCandidates.add(other);
                }
            }
            candidates = nextCandidates;
        }
    }
 
    public static void main(String[] args){
        System.out.println("no example");
    }
}
