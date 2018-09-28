/* TrieTree: Time:O(n), Space:O(n), where n is the number of chars of all words
 * 1. Sort words given its length
 * 2. Insert the word into trie tree, and update "longest" if all the middle nodes along the path has non-empty word
 */

import java.util.*;

public class Solution{
    private class StringComparator implements Comparator<String>{
        public int compare(String x, String y){
            return x.length() - y.length();
        }
    }
    
    private class TrieTree{
        String word;
        TrieTree[] child;
        TrieTree(){child = new TrieTree[26]; word = "";}
    }
    TrieTree root;
    
    private void insert(TrieTree root, String word, String[] longest){
        boolean update = true;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(root.child[c - 'a'] == null){
                root.child[c - 'a'] = new TrieTree();
            }
            root = root.child[c - 'a'];
            if(i < word.length() - 1 && root.word.length() == 0){
                update = false;
            }
        }
        root.word = word; 
        if(update){
            longest[0] = (longest[0].length() < word.length() || longest[0].compareTo(word) > 0)? word: longest[0];
        }
    }
    
    public String longestWord(String[] words) {
        Arrays.sort(words, new StringComparator());
        System.out.println(Arrays.toString(words));
        if(words[0].length() > 1){
            return "";
        }
        
        root = new TrieTree();
        String[] longest = {""};
        for(String word: words){
            insert(root, word, longest);
        }
        return longest[0];
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        System.out.println("words: " + Arrays.toString(words));
        System.out.println("longest: " + sol.longestWord(words));
    }
}
