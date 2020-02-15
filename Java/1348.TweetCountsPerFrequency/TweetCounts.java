/* TreeMap: Time:O(nlogn), Space:O(n)
 * 1. Have a map "tweetName2Time2Count" to store the time-to-count map given tweetId 
 * 2. In getTweetCountsPerFrequency, we slide the window(intervalStartTime, intervalStartTime + delta) and get the submap from tweetName2Time2Count given the range
 * 3. Repeat step2 until intervalStartTime > endTime
 */

import java.util.*;

public class TweetCounts{
    Map<String, TreeMap<Integer, Integer>> tweetName2Time2Count;
    public TweetCounts() {
        tweetName2Time2Count = new HashMap<>();
    }
    
    public void recordTweet(String tweetName, int time) {
        tweetName2Time2Count.computeIfAbsent(tweetName, key -> new TreeMap<>()).putIfAbsent(time, 0);
        tweetName2Time2Count.get(tweetName).put(time, tweetName2Time2Count.get(tweetName).get(time) + 1);
    }
    
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        TreeMap<Integer, Integer> time2Count = tweetName2Time2Count.get(tweetName);
        if(time2Count == null){
            return Collections.singletonList(0);
        }
        
        int delta = 0;
        if(freq.equals("minute")){
            delta = 60;
        }else if(freq.equals("hour")){
            delta = 3600;
        }else{
            delta = 86400;
        }
        List<Integer> tweetCountsPerFrequency = new ArrayList<>();
        int intervalStartTime = startTime;
        while(intervalStartTime <= endTime){
            int count = 0;
            Map<Integer, Integer> map = time2Count.subMap(intervalStartTime, Math.min(intervalStartTime + delta, endTime + 1));
            for(Map.Entry<Integer, Integer> entry: map.entrySet()){
                count += entry.getValue();
            }
            tweetCountsPerFrequency.add(count);
            intervalStartTime += delta;
        }
        return tweetCountsPerFrequency.isEmpty()? Collections.singletonList(0): tweetCountsPerFrequency;
    }

    public static void main(String[] args){
        TweetCounts sol = new TweetCounts();
        sol.recordTweet("tweet3", 0);
        System.out.println("recordTweet(tweet3, 0)");
        sol.recordTweet("tweet3", 60);
        System.out.println("recordTweet(tweet3, 0)");
        sol.recordTweet("tweet3", 10);
        System.out.println("recordTweet(tweet3, 0)");
        System.out.println("getTweetCountsPerFrequency(minute, tweet3, 0, 59)" + sol.getTweetCountsPerFrequency("minute", "tweet3", 0, 59));
        System.out.println("getTweetCountsPerFrequency(minute, tweet3, 0, 60)" + sol.getTweetCountsPerFrequency("minute", "tweet3", 0, 60));
        System.out.println("recordTweet(tweet3, 120)");
        sol.recordTweet("tweet3", 120);
        System.out.println("getTweetCountsPerFrequency(hour, tweet3, 0, 210)" + sol.getTweetCountsPerFrequency("hour", "tweet3", 0, 210));
    }
}
