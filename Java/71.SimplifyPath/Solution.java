/* Stack: O(n)
 * 1. Visit the path with respect to "/"
 * 2. Create a stack pathStackto record the path
 * 3. If visit "..", pop the pathStack
 * 4. If visit "." or "", do no thing
 * 5. If visit others, push the string into stack
 * 
 * ex: path: "/a/./b/../../c/"
 * time[0]: stack = {}
 * time[1]: visit "a", push "a" => stack = {"a"}, 
 * time[2]: visit ".", do nothing => stack = {"a"}
 * time[3]: visit "b", push "b" => stack = {"a". "b"}
 * time[4]: visit "..", pop => stack = {"a"}
 * time[5]: visit "..", pop => stack = {}
 * time[6]: visit "c", push "c" => stack = {"c"}
 */

import java.util.*;

public class Solution{
    public String simplifyPath(String path) {
        String shortPath = "";
        Deque<String> stack = new LinkedList<>();
        
        for (String folder: path.split("/")){
            if(folder.equals(".")){
                continue;
            }
            else if(folder.equals("..")){
                if(!stack.isEmpty()){
                    stack.pollLast();
                }                    
            }
            else if(folder.length() > 0){
                stack.addLast(folder);
            }
        }
        
        while(!stack.isEmpty()){
            shortPath = stack.pollLast() + shortPath;
            shortPath = "/" + shortPath;
        }
        return (shortPath.length() == 0)? "/": shortPath;
    }
 
	public static void main(String[] args){
        String path;
        Solution sol;
        
        path = "/a/./b/../../c/";
        sol = new Solution();
	
        System.out.println("before simplify:" + path);
        path = sol.simplifyPath(path);
        System.out.println("after simplify: " + path);
	}
}
