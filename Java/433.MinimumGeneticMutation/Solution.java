/* BFS: Time:O(n^2*k), Space:O(n*k), where n is the size of bank, and k us the length of gene
 * 1. Use BFS to find the minimum length
 */

import java.util.*;

public class Solution{
    private boolean isMutation(String source, String target){
        if(source.length() != target.length()){
            return false;
        }
        int count = 0;
        for(int i = 0; i < source.length(); ++i){
            count += (source.charAt(i) != target.charAt(i))? 1: 0;
            if(count > 1){
                return false;
            }
        }
        return (count == 1);
    }
    
    public int minMutation(String start, String end, String[] bank) {
        List<String> candidate = new ArrayList<String>();
        for(String word: bank){
            candidate.add(word);
        }
        
        int dis = 0;
        LinkedList<String> queue = new LinkedList<String>();
        queue.add(start);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                String s = queue.poll();
                if(s.equals(end)){
                    return dis;
                }
                Iterator<String> itr = candidate.iterator();
                while(itr.hasNext()){
                    String t = itr.next();
                    if(isMutation(s, t)){
                        queue.add(t);
                        itr.remove();
                    }
                }
            }
            dis++;
        }
        return -1;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        System.out.println("start: " + start);
        System.out.println("end: " + end);
        System.out.println("bank: " + Arrays.toString(bank));
        System.out.println("minimum distance: " + sol.minMutation(start, end, bank));
    }
}
