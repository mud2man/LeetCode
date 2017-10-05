/* Trie: O(max(character# of dict, character# of sentence))
 * 1. Construct a trie with the dictionary
 * 2. Split the sentence, and get the words
 * 3. Get the root word by getRoot with the input = word in the sentence
 */

import java.util.*;

public class Solution{
    private class TrieNode{
        boolean isLeave;
        TrieNode[] children;
        TrieNode(){isLeave = false; children = new TrieNode[26];}
    }
    
    private void insert(TrieNode root, String word){
        TrieNode node = root;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(node.children[c - 'a'] == null){
                node.children[c - 'a'] = new TrieNode(); 
            }
            node = node.children[c - 'a'];
        }
        node.isLeave = true;
    }
    
    private String getRoot(TrieNode root, String word){
        TrieNode node = root;
        int length = 0;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(node.children[c - 'a'] != null){
                length++;
                if(node.children[c - 'a'].isLeave){
                    return word.substring(0, length);
                }
                node = node.children[c - 'a'];
            }
            else{
                return word;
            }
        }
        return word;
    }
    
    public String replaceWords(List<String> dict, String sentence) {
        TrieNode root = new TrieNode();
        
        for(String word: dict){
            insert(root, word);
        }
        
        StringBuilder answer = new StringBuilder("");
        for (String word: sentence.split(" ")){
            String rootWord = getRoot(root, word);
            answer.append(" ");
            answer.append(rootWord);
        }
        answer.deleteCharAt(0);
        
        return answer.toString();
    }

    public static void main(String[] args){
        Solution sol;
        String sentence = "the cattle was rattled by the battery";
        List<String> dict = new ArrayList<String>(Arrays.asList("cat", "bat", "rat"));
        sol = new Solution();

        System.out.println("sentence: " + sentence);
        System.out.println("dict: " + dict);
        System.out.println("output: " + sol.replaceWords(dict, sentence));
    }
}
