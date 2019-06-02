/* Greedy and Selection: Time:O(n), Space:O(n)
 * 1. The only way we reach target from prices is that we pick (max - target) = lowerRoundCount number from prices rounded floor
 * 2. To reach the minimum rounded error is to pick numbers with lowerRoundCount smallest rounded (to floor) error
 * 3. At the same time, the non-picked number have the (n - lowerRoundCount) smallest rounded (to ceil) error
 * 4. Use selection to get the k smallest errors partition, and caculate rounded error to get the total minimum error
 */

import java.util.*; 
import java.math.*;

public class Solution {
    private BigDecimal selectKthSmallest(List<BigDecimal> errors, int lb, int hb, int k){
        if(k < lb || k > hb){
            return null;
        }
        BigDecimal pilot = errors.get(hb);
        int smallEqualIdx = lb - 1;
        for(int i = lb; i < hb; ++i){
            BigDecimal curr = errors.get(i);
            if(curr.compareTo(pilot) <= 0){
                BigDecimal tmp = errors.get(smallEqualIdx + 1);
                errors.set(++smallEqualIdx, curr);
                errors.set(i, tmp);
            }
        }
        BigDecimal tmp = errors.get(smallEqualIdx + 1);
        errors.set(++smallEqualIdx, pilot);
        errors.set(hb, tmp);
        if(smallEqualIdx == k){
            return pilot;
        }else if(smallEqualIdx > k){
            return selectKthSmallest(errors, lb, smallEqualIdx - 1, k);
        }else{
            return selectKthSmallest(errors, smallEqualIdx + 1, hb, k);
        }
    }
    
    public String minimizeError(String[] prices, int target) {
        List<BigDecimal> errors = new ArrayList<>();
        double min = 0;
        double max = 0;
        for(int i = 0; i < prices.length; ++i){
            BigDecimal price = new BigDecimal(prices[i]);
            BigDecimal floor = new BigDecimal(Math.floor(Double.valueOf(prices[i])));
            BigDecimal ceil = new BigDecimal(Math.ceil(Double.valueOf(prices[i])));
            min += Math.floor(Double.valueOf(prices[i]));
            max += Math.ceil(Double.valueOf(prices[i]));
            if(!floor.equals(ceil)){
                errors.add(price.subtract(floor));
            }
        }

        if((double)target < min || (double)target > max){
            return "-1";
        }
        int lowerRoundCount = (int)max - target;
        selectKthSmallest(errors, 0, errors.size() - 1, lowerRoundCount - 1);
        BigDecimal minError = new BigDecimal("0.000");
        for(int i = 0; i < errors.size(); ++i){
            if(i < lowerRoundCount){
                minError = minError.add(errors.get(i));
            }else{
                minError = minError.add(new BigDecimal("1.000").subtract(errors.get(i)));
            }
        }
        return minError.toString();
    }
 
    public static void main(String[] args){
        String[] prices = {"0.700", "2.800", "4.900"};
        int target = 8;
        Solution sol = new Solution();
        System.out.println("prices:" + Arrays.toString(prices));
        System.out.println("target:" + target);
        System.out.println("minimun rounded error:" + sol.minimizeError(prices, target));
    }
}
