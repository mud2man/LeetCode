/* Map: Time:O(n), Space:O(n). LeetCode has O(n) solution
 * 1. Construct needLowerLetterCount, needUpperLetterCount, needDigitCount and repeatingCounts
 * 2. If s.length() < 6, we consider replacing and inserting. Return max(6 - s.length(), needLowerLetterCount + needUpperLetterCount + needDigitCount)
 * 3. Otherwise, get deleteCount from (s.length() - 20), and get "module3ToCounts"
 * 4. We delete char form module3ToCounts with the priority module:0 > module:1 > module:2
 * 5. Accumulate replaceCount by accumualting counts.pollFirst() / 3
 * 6. Return (deleteCount + replaceCount) 
 */

import java.util.*;


public class Solution{
    public int strongPasswordChecker(String s) {
        int needLowerLetterCount = 1;
        int needUpperLetterCount = 1;
        int needDigitCount = 1;
        List<Integer> repeatingCounts = new ArrayList<>();
        char prevChar = '#';
        int repeatingCount = 0;
        for(int i = 0; i < s.length(); ++i){
            needLowerLetterCount = Character.isLowerCase(s.charAt(i))? 0: needLowerLetterCount;
            needUpperLetterCount = Character.isUpperCase(s.charAt(i))? 0: needUpperLetterCount;
            needDigitCount = Character.isDigit(s.charAt(i))? 0: needDigitCount;
            if(prevChar != s.charAt(i) && repeatingCount >= 3){
                repeatingCounts.add(repeatingCount);
            }
            repeatingCount = (prevChar == s.charAt(i))? repeatingCount + 1: 1;
            prevChar = s.charAt(i);
        }
        if(repeatingCount >= 3){
            repeatingCounts.add(repeatingCount);
        }
        
        int changeCount = 0;
        if(s.length() < 6){
            changeCount = Math.max(6 - s.length(), needLowerLetterCount + needUpperLetterCount + needDigitCount);
        }else{
            Map<Integer, Deque<Integer>> module3ToCounts = new HashMap<>();
            for(int count: repeatingCounts){
                module3ToCounts.computeIfAbsent(count % 3, key -> new LinkedList<>()).add(count);
            }
            int deleteCount = s.length() - 20;
            while(!module3ToCounts.isEmpty() && deleteCount > 0){
                Deque<Integer> counts = module3ToCounts.getOrDefault(0, module3ToCounts.getOrDefault(1, module3ToCounts.get(2)));
                int count = counts.pollFirst();
                count--;
                deleteCount--;
                if(count > 2){
                    module3ToCounts.computeIfAbsent(count % 3, key -> new LinkedList<>()).add(count);
                }
                if(counts.isEmpty()){
                    module3ToCounts.remove((count + 1) % 3);
                }
            }
            Deque<Integer> counts = new LinkedList<>();
            for(int i = 0; i < 3; ++i){
                counts.addAll(module3ToCounts.getOrDefault(i, new LinkedList<>()));
            }
            int replaceCount = 0;
            while(!counts.isEmpty()){
                replaceCount += (counts.pollFirst() / 3);
            }
            deleteCount = s.length() - 20;
            replaceCount = Math.max(replaceCount, needLowerLetterCount + needUpperLetterCount + needDigitCount);
            changeCount =(deleteCount > 0)? deleteCount + replaceCount: replaceCount;
        }
        return changeCount;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String password = "abca";
        System.out.println("password:" + password);
        System.out.println("minimum change:" + sol.strongPasswordChecker(password));
    }
}
