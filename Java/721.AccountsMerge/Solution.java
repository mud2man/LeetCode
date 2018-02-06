/* Union and Find: Time:O(nlogn), Space:O(n), where n is the number of mail
 * 1. Have "mailToUnion" to be a map between union and mail
 * 2. Have "mailToName" to map mail into name
 * 3. Let the first mail to be the representative, and have "roots" to store the parent
 * 2. In second loop, get the parent from the 1st mail, update roots of rest mails. s.t. have a parent to connect the previous union
 * 3. In the third loop, construct "mailToUnion" by "find" and "roots"
 * 4. Sort mergeAccount and append account name 
 */         

import java.util.*;

public class Solution {
    private String find(HashMap<String, String> roots, String id){
        return (roots.get(id).equals(id))? id: find(roots, roots.get(id));
    }
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, String> mailToName = new HashMap<String, String>();
        HashMap<String, List<String>> mailToUnion = new HashMap<String, List<String>>();
        HashMap<String, String> roots = new HashMap<String, String>();
        
        for(List<String> account: accounts){
            Iterator<String> itr = account.iterator();
            String name = itr.next();
            while(itr.hasNext()){
                String mail = itr.next();
                roots.put(mail, mail);
                mailToName.put(mail, name);
            }
        }

        for(List<String> account: accounts){
            Iterator<String> itr = account.iterator();
            itr.next();
            String parent = find(roots, itr.next());
            while(itr.hasNext()){
                //find and union
                roots.put(find(roots, itr.next()), parent);
            }
        }
        
        for(Map.Entry<String, String> entry: roots.entrySet()){
            String root = find(roots, entry.getValue());
            String mail = entry.getKey();
            mailToUnion.putIfAbsent(root, new ArrayList<String>());
            mailToUnion.get(root).add(mail);
        }
        
        ArrayList<List<String>> mergeAccount = new ArrayList<List<String>>();
        for(Map.Entry<String, List<String>> entry: mailToUnion.entrySet()){
            String mail = entry.getKey();
            List<String> list = entry.getValue();
            Collections.sort(list);
            list.add(0, mailToName.get(mail));
            mergeAccount.add(list);
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
