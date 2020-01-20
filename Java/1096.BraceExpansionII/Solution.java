/* Stack: Time:O(n!), Space:O(n!)
 * 1. Have a stack to store nodes level by level, where node contain "words" and "prefix"
 * 2. Traverse char by char, and handle them by 4 cases '{', '}', ',', and low case letter 
 * 3. For '{',  '}' and ',', we need to multiply word and "prefixs" on the current level and reset "word" to ""
 * 4. For '{', we push a new node to "stack"
 * 5. For '}', we multiply the "words" on the current level with the "prefixs" on the previous level and pop stack
 * 6. For ',', we add prefixs to words of top
 * 7. For other low case letter, concatenate the letter with "word"
 */

import java.util.*;


public class Solution{
    private class Node{
        Set<String> words;
        Set<String> prefixs;
        Node(){words = new HashSet<>(); prefixs = new HashSet<>();}
    }
    
    private void concatenateWords(Node top, Set<String> words){
        if(words.size() > 0){
            if(top.prefixs.isEmpty()){
                top.prefixs = words;
            }else{
                Set<String> nextPrefixs = new HashSet<>();
                for(String prefix: top.prefixs){
                    for(String word: words){
                        nextPrefixs.add(prefix + word);
                    }
                }
                top.prefixs = nextPrefixs;
            }
        }
    }
    
    public List<String> braceExpansionII(String expression) {
        expression = "{" + expression + "}";
        Deque<Node> stack = new LinkedList<>();
        stack.add(new Node());
        String word = "";
        for(char c: expression.toCharArray()){
            if(Character.isLowerCase(c)){
                word = word + Character.toString(c);
                continue;
            }
            Node top = stack.peekLast();
            Set<String> words = (word.length() > 0)? Collections.singleton(word): new HashSet<>();
            concatenateWords(top, words);
            word = "";
            if (c == '{'){
                stack.add(new Node());
            }else if(c == '}'){
                top.words.addAll(top.prefixs);
                top = stack.pollLast();
                Node nextTop = stack.peekLast();
                concatenateWords(nextTop, top.words);
            }else{
                top.words.addAll(top.prefixs);
                top.prefixs = new HashSet<>();
            }
        }
        Node top = stack.pollLast();
        top.words.addAll(top.prefixs);
        List<String> expressions = new ArrayList<>(top.words);
        Collections.sort(expressions);
        return expressions;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String expression = "{a,b}{c,{d,e}}";
        System.out.println("expression:" + expression);
        System.out.println("result:" + sol.braceExpansionII(expression));
    }
}
