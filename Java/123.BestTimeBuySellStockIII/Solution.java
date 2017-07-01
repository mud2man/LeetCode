/* Dynamic programming: O(n)
 * 1. Use a list lastTwoTransactions to store the best two transactions before the current idx
 * 2. If prices[idx] < localMin, update localMin as prices[idx] and continue
 * 3. If prices[idx] is local maximum update the last and best transactions lastTwoTransactions
 * 4. If lastTwoTransactions.size() == 2, mergeThreeTransactions and upadte localMin
 * 5. Otherwise, put the current transaction into list lastTwoTransactions, and reset localMin = Integer.MAX_VALUE
 * 
 * ex: prices = 0, 2, 1, 7, 3, 6, lastTwoTransactions = {}, localMin = MAX
                0  1  2  3  4  5
 * time[0]: lastTwoTransactions = {}, localMin = 0
 * time[1]: lastTwoTransactions = {{0, 2}}, localMin = MAX
 * time[2]: lastTwoTransactions = {{0, 2}}, localMin = 1
 * time[3]: lastTwoTransactions = {{0, 2}, {1, 7}}, localMin = MAX
 * time[4]: lastTwoTransactions = {{0, 2}, {1, 7}}, localMin = 3
 * time[5]: lastTwoTransactions = {{0, 7}, {3, 6}}, localMin = MAX
 */

import java.util.*;

public class Solution{
    private class Transaction{
        int low;
        int high;
        Transaction(int l, int h){low = l; high = h;}
    }
    
    public int mergeThreeTransactions(List<Transaction> transactions){
        Transaction mergeHead = new Transaction(transactions.get(0).low, transactions.get(1).high);
        Transaction mergeTail = new Transaction(transactions.get(1).low, transactions.get(2).high);
        int mergeHeadProfit = mergeHead.high - mergeHead.low + transactions.get(2).high - transactions.get(2).low;
        int mergeTailrofit = mergeTail.high - mergeTail.low + transactions.get(0).high - transactions.get(0).low;
        List<Transaction> mergeTransactions = new LinkedList<Transaction>();
        List<Transaction> pickTransactions = new LinkedList<Transaction>();
        Transaction worstTransaction;
        int pickProfit, mergeProfit, localMin;
        
        localMin = Integer.MAX_VALUE;
        
        // pick the best merged transaction
        if(mergeHeadProfit > mergeTailrofit){
            mergeTransactions.add(mergeHead);
            mergeTransactions.add(transactions.get(2));
        }
        else{  
            mergeTransactions.add(transactions.get(0));
            mergeTransactions.add(mergeTail); 
        }
        
        // pick the best two transactions
        worstTransaction = transactions.get(0);
        if(worstTransaction.high - worstTransaction.low > transactions.get(1).high - transactions.get(1).low){
            worstTransaction = transactions.get(1);
        }
        if(worstTransaction.high - worstTransaction.low > transactions.get(2).high - transactions.get(2).low){
            worstTransaction = transactions.get(2);
            localMin = transactions.get(2).low;
        }
        
        for(int idx = 0; idx < transactions.size(); ++idx){
            if(transactions.get(idx) != worstTransaction){
                pickTransactions.add(transactions.get(idx));
            }
        }
        
        //compare mergeTransactions and pickTransactions
        pickProfit = pickTransactions.get(0).high - pickTransactions.get(0).low;
        pickProfit += pickTransactions.get(1).high - pickTransactions.get(1).low;
        mergeProfit = mergeTransactions.get(0).high - mergeTransactions.get(0).low;
        mergeProfit += mergeTransactions.get(1).high - mergeTransactions.get(1).low;
        if(mergeProfit > pickProfit){
            transactions.clear();
            transactions.add(mergeTransactions.get(0));
            transactions.add(mergeTransactions.get(1));
            localMin = Integer.MAX_VALUE;
        }
        else{
            transactions.clear();
            transactions.add(pickTransactions.get(0));
            transactions.add(pickTransactions.get(1));
        }
        
        return localMin;
    }
    
    public int maxProfit(int[] prices) {
        List<Transaction> lastTwoTransactions = new LinkedList<Transaction>();
        int localMin = Integer.MAX_VALUE;
        int profit, currentProfit, firstProfit, secondProfit;
        
        for(int idx = 0; idx < prices.length; idx++){
            // local minimum
            if(prices[idx] < localMin){
                localMin = prices[idx];
                continue;
            }
            
            // local maximum
            if( idx == prices.length - 1 || prices[idx] >  prices[idx + 1]){
                if(lastTwoTransactions.size() == 2){
                    lastTwoTransactions.add(new Transaction(localMin, prices[idx]));
                    localMin = mergeThreeTransactions(lastTwoTransactions);
                }
                else{
                    lastTwoTransactions.add(new Transaction(localMin, prices[idx]));
                    localMin = Integer.MAX_VALUE;
                }
            }
        }
        
        // reap profit
        profit = 0;
        for(Transaction transaction: lastTwoTransactions){
            profit += transaction.high - transaction.low;
        }
        return profit; 
    }

    public static void main(String[] args){
        Solution sol;
        int[] prices = {0,2,1,7,3,6};

        sol = new Solution();

        System.out.println("prices: " + Arrays.toString(prices));
        System.out.println("max profit: " + sol.maxProfit(prices));
    }
}
