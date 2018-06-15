/* Time:O(n), Space:O(1)
 * 1. Check every rule carefuly
 */

import java.util.*;

public class Solution{
    private boolean checkIpv4(String ip){
        if(ip.length() > 3){
            return false;
        } 
        for(int i = 0; i < ip.length(); ++i){
            char c = ip.charAt(i);
            if(!Character.isDigit(c) || (i == 0 && c == '0' && ip.length() > 1)){
                return false;
            }
        }
        return (ip.length() > 0 && Integer.parseInt(ip) < 256);
    }
    
    private boolean checkIpv6(String ip, Set<Character> letters){
        if(ip.length() > 4){
            return false;
        }
        for(int i = 0; i < ip.length(); ++i){
            char c = ip.charAt(i);
            if(Character.isDigit(c) || letters.contains(c)){
                continue;
            }
            return false;
        }
        return (ip.length() > 0);
    }
    
    public String validIPAddress(String IP) {
        if(IP.indexOf(".") != -1){
            if(IP.indexOf(":") != -1 || IP.charAt(IP.length() - 1) == '.' || IP.charAt(0) == '.'){
                return "Neither";
            }
            String[] ipv4 = IP.split("\\.");
            if(ipv4.length != 4){
                return "Neither";
            }
            for(String ip: ipv4){
                if(!checkIpv4(ip)){
                    return "Neither";
                }
            }
            return "IPv4";
        }
        else if(IP.indexOf(":") != -1){
            if(IP.indexOf(".") != -1 || IP.charAt(IP.length() - 1) == ':' || IP.charAt(0) == ':'){
                return "Neither";
            }
            String[] ipv6 = IP.split(":");
            if(ipv6.length != 8){
                return "Neither";
            }
            Set<Character> letters = new HashSet<Character>();
            for(int i = 0; i < 6; i++){
                letters.add((char)((int)'a' + i));
                letters.add((char)((int)'A' + i));
            }
            for(String ip: ipv6){
                if(!checkIpv6(ip, letters)){
                    return "Neither";
                }
            }
            return "IPv6";
        }
        else{
            return "Neither";
        }
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String IP = "172.16.254.1";
        System.out.println("ip: " + IP);
        System.out.println("type: " + sol.validIPAddress(IP));
    }
}
