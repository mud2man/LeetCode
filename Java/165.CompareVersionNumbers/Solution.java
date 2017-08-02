/* Math: O(n)
 * 1. split with ".", and convert to BigInteger to compare
 */

import java.util.*;
import java.math.*;

public class Solution{
    public int compareVersion(String version1, String version2) {
        String[] version1Seq = version1.split("\\.");
        String[] version2Seq = version2.split("\\.");
        int version1Len = version1Seq.length;
        int version2Len = version2Seq.length;
        
        for(int i = 0; i < Math.min(version1Len, version2Len); ++i){
            BigInteger subVersion1 = new BigInteger(version1Seq[i]);
            BigInteger subVersion2 = new BigInteger(version2Seq[i]);
            if(subVersion1.compareTo(subVersion2) == 1){
                return 1;
            }
            else if(subVersion1.compareTo(subVersion2) == -1){
                return -1;
            }
        }
        
        if(version1Len == version2Len){
            return 0;
        }
        else if(version1Len > version2Len){
            for(int j = version2Len; j < version1Len; ++j){
                BigInteger subVersion1 = new BigInteger(version1Seq[j]);
                BigInteger subVersion2 = new BigInteger("0");
                if(subVersion1.compareTo(subVersion2) != 0){
                    return 1;
                }
            }
            return 0;
        }
        else{
            for(int j = version1Len; j < version2Len; ++j){
                BigInteger subVersion1 = new BigInteger("0");
                BigInteger subVersion2 = new BigInteger(version2Seq[j]);
                if(subVersion2.compareTo(subVersion1) != 0){
                    return -1;
                }
            }
            return 0;
        }
    }
 
    public static void main(String[] args){
        String version1 = "1.0";
        String version2 = "1.1";
        Solution sol;

        sol = new Solution();
        System.out.println("version1:" + version1);
        System.out.println("version2:" + version2);
        System.out.println("result: " + sol.compareVersion(version1, version2));
    }
}
