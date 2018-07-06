/* Stack: Time:O(n^2), Space:O(n)
 * 1. Use recursive method "helper" to simulate stack, it return word-count map blocked by '(' and ')'
 * 2. Sort the entries of the word-count map, and append all of them
 */         

import java.util.*;

public class Solution {
    private Map<String, Integer> helper(String formula, int[] idx){
        Map<String, Integer> map = new HashMap<String, Integer>();
        while(idx[0] < formula.length()){
            if(formula.charAt(idx[0]) == '('){
                idx[0]++;
                Map<String, Integer> innerMap = helper(formula, idx);
                for(Map.Entry<String, Integer> entry: innerMap.entrySet()){
                    String word = entry.getKey();
                    int count = entry.getValue();
                    if(map.containsKey(word)){
                        map.put(word, map.get(word) + count);   
                    }
                    else{
                        map.put(word, count); 
                    }
                }
            }
            else if(formula.charAt(idx[0]) == ')'){
                idx[0]++;
                int multiply = 0;
                while(idx[0] < formula.length() && Character.isDigit(formula.charAt(idx[0]))){
                    multiply = multiply * 10 + (formula.charAt(idx[0]++) - '0');
                }
                multiply = Math.max(1, multiply);

                for(Map.Entry<String, Integer> entry: map.entrySet()){
                    String word = entry.getKey();
                    int count = entry.getValue();
                    map.put(word, count * multiply);
                }
                return map;
            }
            else{
                int count = 0;
                StringBuilder sb = new StringBuilder(); 
                sb.append(formula.charAt(idx[0]++));
                while(idx[0] < formula.length() && Character.isLowerCase(formula.charAt(idx[0]))){
                    sb.append(formula.charAt(idx[0]++));
                }
                
                while(idx[0] < formula.length() && Character.isDigit(formula.charAt(idx[0]))){
                    count = count * 10 + (formula.charAt(idx[0]++) - '0');
                }
                count = (count == 0)? 1: count;
                
                String word = sb.toString();
                if(map.containsKey(word)){
                    map.put(word, map.get(sb.toString()) + count);   
                }
                else{
                    map.put(word, count); 
                }
            }
        }
        return map;
    }
    
    private class AtomComparator implements Comparator<Map.Entry<String, Integer>>{
        public int compare(Map.Entry<String, Integer> x, Map.Entry<String, Integer> y){
            return x.getKey().compareTo(y.getKey());
        }
    }

    public String countOfAtoms(String formula) {
        int[] idx = new int[1];
        Map<String, Integer> map = helper(formula, idx);
        List<Map.Entry<String, Integer>> atoms = new ArrayList<Map.Entry<String, Integer>>();
        atoms.addAll(map.entrySet());        
        Collections.sort(atoms, new AtomComparator());
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> entry: atoms){
            sb.append(entry.getKey());
            if(entry.getValue() > 1){
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }
 
    public static void main(String[] args){
        Solution sol= new Solution();
        String formula = "K4(ON(SO3)2)2";
        System.out.println("formula: " + formula);
        System.out.println("attom count: " + sol.countOfAtoms(formula));
    }
}
