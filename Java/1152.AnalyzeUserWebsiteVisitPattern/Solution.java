/* Hash: Time:O(n^3), Space:O(n)
 * 1. Sort tuples(username, time, website) and construct user2Websites
 * 2. Get all 3-sequence per user and construct sequence2Count where sequence was hased by websites.get(i) + "#" + websites.get(j) + "#" + websites.get(k);
 * 3. Get the lexicographically smallest 3-sequence from the list which has the most count
 *
 */     

import java.util.*; // Stack

public class Solution {
    private class Tuple{
        String user;
        Integer time;
        String website;
        Tuple(String u, int t, String w){user = u; time = t; website = w;}
    }
    
    private List<String> hash2Sequence(String hash){
        List<String> sequence = new ArrayList<>();
        int idxFirstPound = hash.indexOf('#');
        int idxSecondPound = hash.indexOf('#', idxFirstPound + 1);
        return Arrays.asList(hash.substring(0, idxFirstPound), hash.substring(idxFirstPound + 1, idxSecondPound),
                             hash.substring(idxSecondPound + 1));
    }
    
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Tuple> tuples = new ArrayList<>();
        for(int i = 0; i < username.length; ++i){
            tuples.add(new Tuple(username[i], timestamp[i], website[i]));
        }
        Collections.sort(tuples, (x, y) -> (x.user.equals(y.user)? x.time.compareTo(y.time): x.user.compareTo(y.user)));
        Map<String, List<String>> user2Websites = new HashMap<>();
        for(Tuple tuple: tuples){
            user2Websites.computeIfAbsent(tuple.user, key -> new ArrayList<>()).add(tuple.website);
        }
        
        Map<String, Integer> sequence2Count = new HashMap<>();
        int max = 0;
        for(Map.Entry<String, List<String>> entry: user2Websites.entrySet()){
            List<String> websites = entry.getValue();
            Set<String> seen = new HashSet<>();
            for(int i = 0; i < websites.size() - 2; ++i){
                for(int j = i + 1; j < websites.size() - 1; ++j){
                    for(int k = j + 1; k < websites.size(); ++k){
                        String hash = websites.get(i) + "#" + websites.get(j) + "#" + websites.get(k);
                        if(seen.contains(hash)){
                            continue;
                        }
                        seen.add(hash);
                        sequence2Count.put(hash, sequence2Count.getOrDefault(hash, 0) + 1);
                        max = Math.max(max, sequence2Count.get(hash));
                    }
                }
            }
        }
        
        List<String> candidate = null;
        for(Map.Entry<String, Integer> entry: sequence2Count.entrySet()){
            if(entry.getValue() == max){
                if(candidate == null){
                    candidate = hash2Sequence(entry.getKey());
                }else{
                    List<String> curr = hash2Sequence(entry.getKey());
                    if(curr.get(0).compareTo(candidate.get(0)) < 0){
                        candidate = curr;
                    }else if(curr.get(0).compareTo(candidate.get(0)) == 0 && 
                             curr.get(1).compareTo(candidate.get(1)) < 0){
                        candidate = curr;
                    }else if(curr.get(0).compareTo(candidate.get(0)) == 0 && 
                             curr.get(1).compareTo(candidate.get(1)) == 0 && 
                             curr.get(2).compareTo(candidate.get(2)) < 0){
                        candidate = curr;
                    }
                }
            }
        }
        return candidate;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] username = {"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"};
        int[] timestamp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] website = {"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"}; 
        System.out.println("username:" + Arrays.toString(username));
        System.out.println("timestamp:" + Arrays.toString(timestamp));
        System.out.println("website:" + Arrays.toString(website));
        System.out.println("3-sequence most visited:" + sol.mostVisitedPattern(username, timestamp, website));
    }
}
