/* BFS + Divide&Conquer with memorization
 * 1. Construct an offer map "offers" to record the maximum reduction of every offer
 * 2. The key is encoded as ten's digit
 * 3. Use BFS to deduct the remaining items, and update the priceMap and minPrice  as well
 */         

import java.util.*;

public class Solution {
    private boolean isValid(int need, int offer){
        int big = Math.max(need, offer);
        while(big > 0){
            if((offer % 10) > (need % 10)){
                return false;
            }
            else{
                offer = offer / 10;
                need = need / 10;
                big = big / 10;
            }
        }
        return true;
    }
    
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        HashMap<Integer, Integer> offers = new HashMap<Integer, Integer>();
        for(List<Integer> list: special){
            int originalPrice = 0;
            int offerKey = 0;
            for(int i = 0; i < list.size() - 1; ++i){
                originalPrice += list.get(i) * price.get(i);
                offerKey = offerKey * 10 + list.get(i);
            }
            if(!offers.containsKey(offerKey)){
                offers.put(offerKey, originalPrice - list.get(list.size() - 1));
            }
            else{
                offers.put(offerKey, Math.max(offers.get(offerKey), originalPrice - list.get(list.size() - 1)));
            }
        }
        
        int minPrice = 0;
        int remain = 0;
        for(int i = 0; i < needs.size(); ++i){
            minPrice += needs.get(i) * price.get(i);
            remain = remain * 10 + needs.get(i); 
        }
        
        HashMap<Integer, Integer> priceMap = new HashMap<Integer, Integer>();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(remain);
        priceMap.put(remain, minPrice);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                int currentRemain = queue.poll();
                int currentPrice = priceMap.get(currentRemain);
                for(Map.Entry<Integer, Integer> offer: offers.entrySet()){
                    int offerList = offer.getKey();
                    int offerReduction = offer.getValue();
                    if(isValid(currentRemain, offerList)){
                        int nextRemain = currentRemain - offerList;
                        int nextPrice = currentPrice - offerReduction;
                        if(!priceMap.containsKey(nextRemain) || priceMap.get(nextRemain) > nextPrice){
                            priceMap.put(nextRemain, nextPrice);
                            minPrice = Math.min(minPrice, nextPrice);
                            queue.add(nextRemain);
                        }
                    }
                }
            }
        }
        
        return minPrice;
    }
  
    public static void main(String[] args){
        Solution sol;
        List<Integer> price = new ArrayList<Integer>(Arrays.asList(2, 5));
        List<List<Integer>> special = new ArrayList<List<Integer>>();
        special.add(new ArrayList<Integer>(Arrays.asList(3, 0, 5)));
        special.add(new ArrayList<Integer>(Arrays.asList(1, 2, 10)));
        List<Integer> needs = new ArrayList<Integer>(Arrays.asList(3, 2));
        sol = new Solution();

        System.out.println("price: " + price);
        System.out.println("special: " + special);
        System.out.println("needs: " + needs);
        System.out.println("minimum price: " + sol.shoppingOffers(price, special, needs));
    }
}
