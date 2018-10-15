/* Backtrack: Time:O(1), Space:O(1). LeetCode has iteration version
 * 1. Use backtrack to find all the combination
 */

import java.util.*;

public class Solution{
    private boolean isValid(String subnet){
        if(subnet.length() > 3 || subnet.length() == 0 || (subnet.charAt(0) == '0' && subnet.length() > 1)){
            return false;
        }
        int ip = Integer.valueOf(subnet);
        return (ip >= 0 && ip < 256);
    }
    
    private void backtrack(String s, int idx, int subNetIdx, String path, List<String> ips){
        if(subNetIdx == 3){
            String lastSubnet = s.substring(idx);
            if(isValid(lastSubnet)){
                ips.add(path + lastSubnet);
            }
            return;
        }
        
        for(int i = 1; i < 4; ++i){
            int end = idx + i;
            if(end <= s.length() && isValid(s.substring(idx, end))){
                backtrack(s, end, subNetIdx + 1, path + s.substring(idx, end) + ".", ips);
            }
            else{
                break;
            }
        }
    }
    
    public List<String> restoreIpAddresses(String s) {
        List<String> ips = new ArrayList<>();
        backtrack(s, 0, 0, "", ips);
        return ips;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();;
        String s =  "25525511135";
        System.out.println("s: " + s);
        System.out.println("ips: " + sol.restoreIpAddresses(s));
    }
}
