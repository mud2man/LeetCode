/* Recursive: Time:O(logn), Space:O(logn)
 * 1. If lsb <= n, then range will be log2(lsb)
 * 2. Otherwise, range will be ceil(log2(n))
 * 3. After step1 or step2, invoke helper with n = n - count
 */

import java.util.*;

public class Solution {
    private String longToIp(long ip){
        String ret = "";
        for(int i = 0; i < 4; ++i){
            ret = "." + Long.toString(ip % 256) + ret;
            ip = ip / 256;
        }
        return ret.substring(1);
    }
    
    private void helper(List<String> cidrs, long ipNum, int n){
        if(n == 0){
            return;
        }
        
        long lsb = ipNum & (-ipNum);
        int exp = (lsb <= n)? (int)(Math.log(lsb) / Math.log(2)): (int)(Math.log(n) / Math.log(2));
        int range = 32 - exp;
        int count = (lsb <= n)? (int)lsb: (int)Math.pow(2, exp);
        n = n - count;
        String ip = longToIp(ipNum);
        cidrs.add(ip + "/" + range);
        helper(cidrs, ipNum + count, n);
    }
    
    public List<String> ipToCIDR(String ip, int n) {
        List<String> cidrs = new ArrayList<String>();
        String[] ipv4 = ip.split("\\.");
        long ipNum = 0;
        for(int i = 0; i < 4; ++i){
            ipNum = ipNum * 256 + Long.valueOf(ipv4[i]);
        }
        helper(cidrs, ipNum, n);
        return cidrs;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String ip = "255.0.0.7";
        int n = 10;
        System.out.println("ip: " + ip + ", n:" + n);
        System.out.println("CIDRs: " + sol.ipToCIDR(ip, n));
    }
}
