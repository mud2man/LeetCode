/* Map: Time:O(n), Space:O(1)
 * 1. Character:{'z', 'w', 'u', 'x', 'g'} in tier 0  can represent digits uniquely from {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
 * 2. Character:{'o', 't', 'f', 's'} in tier 1 can represent digits uniquely from {1, 3, 4, 7, 9}
 * 3. Character:{'n} in tier 2 can represent digits uniquely from {9}
 * 4. By rule 1, 2, and 3, we can determine the counts of {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
 */     

import java.util.*; // Stack

public class Solution {
    public String originalDigits(String s) {
        int[] char2Count = new int[26];
        for(char c: s.toCharArray()){
            char2Count[c - 'a']++;
        }
        
        Map<Character, String> char2Digit = new HashMap<>();
        char2Digit.put('z', "zero");
        char2Digit.put('w', "two");
        char2Digit.put('u', "four");
        char2Digit.put('x', "six");
        char2Digit.put('g', "eight");
        char2Digit.put('o', "one");
        char2Digit.put('t', "three");
        char2Digit.put('f', "five");
        char2Digit.put('s', "seven");
        char2Digit.put('i', "nine");
        
        Map<String, Integer> digit2Number = new HashMap<>();
        digit2Number.put("zero", 0);
        digit2Number.put("one", 1);
        digit2Number.put("two", 2);
        digit2Number.put("three", 3);
        digit2Number.put("four", 4);
        digit2Number.put("five", 5);
        digit2Number.put("six", 6);
        digit2Number.put("seven", 7);
        digit2Number.put("eight", 8);
        digit2Number.put("nine", 9);
        
        int[] counts = new int[10];
        List<List<Character>> tiers = new ArrayList<>();
        tiers.add(Arrays.asList('z', 'w', 'u', 'x', 'g'));
        tiers.add(Arrays.asList('o', 't', 'f', 's'));
        tiers.add(Arrays.asList('i'));
        for(List<Character> tier: tiers){
            for(char c: tier){
                if(char2Count[c - 'a'] > 0){
                    int count = char2Count[c - 'a'];
                    String digit = char2Digit.get(c);
                    int number = digit2Number.get(digit);
                    counts[number] = count;
                    for(char digitChar: digit.toCharArray()){
                        char2Count[digitChar - 'a'] -= count;
                    }
                }
            }
        }
        
        StringBuilder digits = new StringBuilder("");
        for(int i = 0; i < 10; ++i){
            int count = counts[i];
            if(count > 0){
                for(int j = 0; j < count; ++j){
                    digits.append(i);
                }
            }
        }
        return digits.toString();
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String s = "owoztneoer";
        System.out.println("s:" + s);
        System.out.println("original digits:" + sol.originalDigits(s));
    }
}
