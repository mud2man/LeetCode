/* Map: Time:O(n*k), Space:O(n*k), where k is the average length of path
 * 1. Get the map content2Paths, and add the according entry value to "ret", if its size > 1
 */

import java.util.*;

public class Solution {
    private void helper(String path, Map<String, Set<String>> content2Paths){
        String[] s = path.split(" ");
        String p = s[0];
        for(int i = 1; i < s.length; ++i){
            StringBuilder sb = new StringBuilder("");
            int idx = 0;
            while(s[i].charAt(idx) != '('){
                sb.append(s[i].charAt(idx++));
            }
            String file = sb.toString();
            idx++;
            sb = new StringBuilder("");
            while(s[i].charAt(idx) != ')'){
                sb.append(s[i].charAt(idx++));
            }
            String content = sb.toString();
            
            content2Paths.putIfAbsent(content, new HashSet<>());
            content2Paths.get(content).add(p + "/" + file);
        }
    }
    
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, Set<String>> content2Paths = new HashMap<>();
        for(String path: paths){
            helper(path, content2Paths);
        }
        
        List<List<String>> ret = new ArrayList<>();
        for(Map.Entry<String, Set<String>> entry: content2Paths.entrySet()){
            if(entry.getValue().size() > 1){
                ret.add(new ArrayList<>(entry.getValue()));
            }
        }
        return ret; 
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] paths = {"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        System.out.println("paths: " + Arrays.toString(paths));
        System.out.println("duplicates:" + sol.findDuplicate(paths));
    }
}
