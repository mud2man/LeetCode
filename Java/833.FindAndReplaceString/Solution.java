/* Sort: Time:O(n*logn + m), Space:O(k), n:indexes number, m:the length of S, k:the characters count
 * 1. Have a "list" to store index, source, and target. And sort based on index
 * 2. Traverse index and do replacement if pattern match
 */

import java.util.*;

public class Solution{
    private class Node{
        int index;
        String source;
        String target;
        Node(int i, String s, String t){index = i; source = s; target = t;}
    }
    
    private class IndexComparator implements Comparator<Node>{
        public int compare(Node x, Node y){
            return x.index - y.index;
        }
    }
    
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        List<Node> list = new ArrayList<Node>();
        for(int i = 0; i < indexes.length; ++i){
            list.add(new Node(indexes[i], sources[i], targets[i]));
        }
        Collections.sort(list, new IndexComparator());
        
        String ret = S.substring(0, list.get(0).index);
        
        for(int i = 0; i < (list.size() - 1); ++i){
            int index = list.get(i).index;
            String source = list.get(i).source;
            String target = list.get(i).target;
            
            if(S.startsWith(source, index)){
                ret += target;
                ret += S.substring(index + source.length(), list.get(i + 1).index);
            }
            else{
                ret += S.substring(index, list.get(i + 1).index);
            }
        }
        
        int lastIndex = list.get(list.size() - 1).index;
        if(S.startsWith(list.get(list.size() - 1).source, lastIndex)){
            ret += list.get(list.size() - 1).target;
            ret += S.substring(lastIndex + list.get(list.size() - 1).source.length());
        }
        else{
            ret += S.substring(lastIndex);
        }
        
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String S = "abcd";
        int[] indexes = {0, 2};
        String[] sources = {"a","cd"};
        String[] targets = {"eee","ffff"};
        System.out.println("S: " + S);
        System.out.println("indexes: " + Arrays.toString(indexes));
        System.out.println("sources: " + Arrays.toString(sources));
        System.out.println("targets: " + Arrays.toString(targets));
        System.out.println("result: " + sol.findReplaceString(S, indexes, sources, targets));
    }
}
