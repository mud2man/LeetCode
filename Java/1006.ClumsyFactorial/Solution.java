/* Recursive: Time:O(n), Space:O(n)
 * 1. We need to determine the sign of the first number, and take absolute value as necessary
 */

import java.util.*;

public class Solution{
    public int clumsy(int N) {
        boolean positive = (N > 0)? true: false;
        N = (positive)? N: -N;
        if(N == 1){
            return (positive)? 1: -1;
        }else if(N == 2){
            return (positive)? 2 * 1: -2;
        }else if(N == 3){
            return (positive)? 3 * 2 / 1: (-3) * 2 / 1;
        }else if(N == 4){
            return (positive)? 4 * 3 / 2 + 1: (-4) * 3 / 2 + 1;
        }else{
            if(positive){
                return N * (N - 1) / (N - 2) + (N - 3) + clumsy(-(N - 4));
            }else{
                return (-N) * (N - 1) / (N - 2) + (N - 3) + clumsy(-(N - 4));
            }
        }
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int N = 5;
        System.out.println(String.format("factorial(%d)=%d", N, sol.clumsy(N)));
    }
}
