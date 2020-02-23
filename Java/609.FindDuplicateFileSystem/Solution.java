/* Map: Time:O(n*k), Space:O(n*k), where k is the average length of path
 * 1. Get the map content2Paths, and add the according entry value to "ret", if its size > 1
 *
 * FOLLOWUP:
 * 1. Imagine you are given a real file system, how will you search files? DFS or BFS ?
 *    In general, BFS will use more memory then DFS. 
 *    However BFS can take advantage of the locality of files in inside directories, and therefore will probably be faster
 * 2. If the file content is very large (GB level), how will you modify your solution?
 *    In a real life solution we will not hash the entire file content, since it's not practical. 
 *    Instead we will first map all the files according to size. Files with different sizes are guaranteed to be different. 
 *    We will than sha256 a small part of the files plus file size to be a hash. Only if the small sha256 is the same, we will compare the files byte by byte
 * 3. If you can only read the file by 1kb each time, how will you modify your solution?
 *    This won't change the solution. We can create the hash from the 1kb chunks, and then read the entire file if a full byte by byte comparison is required.
 * 4. What is the time complexity of your modified solution? What is the most time consuming part and memory consuming part of it? How to optimize?
 *    Time complexity is O(n^2 * k) since in worse case we might need to compare every file to all others. k is the file size
 * 5. How to make sure the duplicated files you find are not false positive?
 *    We will do byte-by-byte comparisons if they have the same hash to prevent false positive
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
            content2Paths.computeIfAbsent(content, key -> new HashSet<>()).add(p + "/" + file);
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
