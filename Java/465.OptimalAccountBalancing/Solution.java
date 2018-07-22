/* Backtrack:  
 * 1. Get the mapping, where key is account, value is the debt
 * 2. Remove the duplicates, where appear in both sendMap and receiveMap
 * 3. Use backtrack to find all the transaction order and keep the minimum 
 */

import java.util.*;

public class Solution{
    private void backtrack(LinkedList<Integer> sendList, LinkedList<Integer> receiveList, int count, int[] min){
        if(sendList.isEmpty() && receiveList.isEmpty()){
            min[0] = Math.min(min[0], count);
            return;
        }
        
        if(min[0] <= count){
            return;
        }
        
        int sendLen = sendList.size();
        int receiveLen = receiveList.size();
        for(int i = 0; i < sendLen; ++i){
            int sendMoney = sendList.poll();
            for(int j = 0; j < receiveLen; j++){
                int receiveMoney = receiveList.poll();
                if(sendMoney > receiveMoney){
                    sendList.addFirst(sendMoney - receiveMoney);
                    backtrack(sendList, receiveList, count + 1, min);
                    sendList.poll();
                }
                else if(sendMoney < receiveMoney){
                    receiveList.addFirst(receiveMoney - sendMoney);
                    backtrack(sendList, receiveList, count + 1, min);
                    receiveList.poll();
                }
                else{
                    backtrack(sendList, receiveList, count + 1, min);
                }
                receiveList.add(receiveMoney);
            }
            sendList.add(sendMoney);
        }
    }
    
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> account = new HashMap<>();
        for(int[] transaction: transactions){
            int sender = transaction[0];
            int receiver = transaction[1];
            int money = transaction[2];
            account.putIfAbsent(sender, 0);
            account.put(sender, account.get(sender) - money);
            account.putIfAbsent(receiver, 0);
            account.put(receiver, account.get(receiver) + money);
        }
        
        //remove duplicates
        Map<Integer, Integer> sendMap = new HashMap<>();
        Map<Integer, Integer> receiveMap = new HashMap<>();
        for(Map.Entry<Integer, Integer> entry: account.entrySet()){
            int money = entry.getValue();
            if(money < 0){
                receiveMap.putIfAbsent(-money, 0);
                receiveMap.put(-money, receiveMap.get(-money) + 1);
            }
            else if(money > 0){
                sendMap.putIfAbsent(money, 0);
                sendMap.put(money, sendMap.get(money) + 1);
            }
        }

        int base = 0;
        LinkedList<Integer> sendList = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: sendMap.entrySet()){
            int money = entry.getKey();
            int count = entry.getValue();
            if(receiveMap.containsKey(money)){
                base += Math.min(count, receiveMap.get(money));
                count = (count > receiveMap.get(money))? count - receiveMap.get(money): 0;
                receiveMap.put(money, receiveMap.get(money) - base);
            }
            for(int i = 0; i < count; ++i){
                sendList.add(money);
            }
        }

        LinkedList<Integer> receiveList = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: receiveMap.entrySet()){
            int money = entry.getKey();
            int count = entry.getValue();
            for(int i = 0; i < count; ++i){
                receiveList.add(money);
            }
        }

        int[] min = {Integer.MAX_VALUE};
        backtrack(sendList, receiveList, base, min);
        return min[0];
    }

    public static void main(String[] args){
        int[][] transactions = {{0, 1, 10}, {1, 0, 1}, {1, 2, 5}, {2, 0, 5}};
        Solution sol = new Solution();

        System.out.println("transactions:");
        for(int[] transaction: transactions){
            System.out.println(Arrays.toString(transaction));
        }
        System.out.println("minimum transactions: " + sol.minTransfers(transactions));
    }
}
