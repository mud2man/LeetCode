/* Union and Find: Time:O(nlogn), Space:O(n), where n is the number of mail
 * 1. Have "mailToId" to be a map between union id and mail
 * 2. In the loop, create a new union id, and update the root id of all mails. s.t. have a parent to connect the previous union
 * 3. Have a indexMap to map union id to the index of mergeAccount, and append the mail
 * 4. Sort mergeAccount and append account name 
 */         

import java.util.*;

public class Solution {
    private int find(int[] roots, int id){
        if(roots[id] == id){
            return id;
        }    
        else{
            //compression
            roots[id] = roots[roots[id]];
            return find(roots, roots[roots[id]]);
        }
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, String> mailToName = new HashMap<String, String>();
        HashMap<String, Integer> mailToId = new HashMap<String, Integer>();
        int[] roots = new int[10000];
        
        for(int i = 0; i < roots.length; ++i){
            roots[i] = i;
        }
        
        int id = -1;
        for(List<String> account: accounts){
            id++;
            Iterator<String> itr = account.iterator();
            String name = itr.next();
            while(itr.hasNext()){
                String mail = itr.next();
                mailToId.putIfAbsent(mail, id);
                //find and union
                roots[find(roots, mailToId.get(mail))] = id;
                mailToName.put(mail, name);
            }
        }
        
        int index = 0;
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        ArrayList<List<String>> mergeAccount = new ArrayList<List<String>>();
        for(Map.Entry<String, Integer> entry: mailToId.entrySet()){
            String mail = entry.getKey();
            id = entry.getValue();
            int root = find(roots, id);
            
            if(indexMap.containsKey(root)){
                mergeAccount.get(indexMap.get(root)).add(mail);
            }
            else{
                indexMap.put(root, index++);
                mergeAccount.add(new ArrayList<String>());
                mergeAccount.get(index - 1).add(mail);
            }
        }
        
        for(List<String> account: mergeAccount){
            Collections.sort(account);
            account.add(0, mailToName.get(account.get(0)));
        }
        
        return mergeAccount;
    }

    public static void main(String[] args){
        Solution sol;
        List<List<String>> accounts = new ArrayList<List<String>>();
        accounts.add(new ArrayList<String>(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com")));
        accounts.add(new ArrayList<String>(Arrays.asList("John", "johnnybravo@mail.com")));
        accounts.add(new ArrayList<String>(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com")));
        accounts.add(new ArrayList<String>(Arrays.asList("Mary", "mary@mail.com")));
        
        sol = new Solution();
        System.out.println("accounts before merge:" + accounts);
        System.out.println("accounts before merge:" + sol.accountsMerge(accounts));
    }
}
