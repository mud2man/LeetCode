/* Pull model
 # 1. Have a time stamp to record the post time for every tweet
 # 2. Use minHeap the collect the last 10 tweets from folowee's tweet 
 */

import java.util.*;
import java.math.*;

public class Twitter{
    private class Tweet{
        int timeStamp;
        int tweetId;
        Tweet(int time, int id) {timeStamp = time; tweetId = id;}
    }
    
    private class TweetComparator implements Comparator<Tweet>{
        public int compare(Tweet o1, Tweet o2){
            return o1.timeStamp - o2.timeStamp;
        }
    }
    
    private class TweetComparator2 implements Comparator<Tweet>{
        public int compare(Tweet o1, Tweet o2){
            return o2.timeStamp - o1.timeStamp;
        }
    }

    HashMap<Integer, Set<Integer>> followeesMap;
    HashMap<Integer, List<Tweet>> tweetsMap;
    int timeStamp;
    
    /** Initialize your data structure here. */
    public Twitter() {
        followeesMap = new HashMap<Integer, Set<Integer>>();
        tweetsMap = new HashMap<Integer, List<Tweet>>();
        timeStamp = 0;
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        timeStamp++;
        Set<Integer> followees;
        List<Tweet> tweets;
        Tweet tweet;
       
        if(!followeesMap.containsKey(userId)){
            followees = new HashSet<Integer>();
            followees.add(userId);
            followeesMap.put(userId, followees);
        }
        else{
            followees = followeesMap.get(userId);
            followees.add(userId);
        }
        
        if(!tweetsMap.containsKey(userId)){
            tweet = new Tweet(timeStamp, tweetId);
            tweets = new LinkedList<Tweet>();
            tweets.add(tweet);
            tweetsMap.put(userId, tweets);
        }
        else{
            tweet = new Tweet(timeStamp, tweetId);
            tweets = tweetsMap.get(userId);
            if(tweets.size() == 10){
                tweets.remove(0);
            }
            tweets.add(tweet);
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> minHeap;
        Set<Integer> followees;
        List<Tweet> tweets;
        List<Integer> news;
        int i;
        
        minHeap = new PriorityQueue<Tweet>(new TweetComparator());
        news = new ArrayList<Integer>();
        
        if(!followeesMap.containsKey(userId)){
            return news;
        }
        
        followees = followeesMap.get(userId);
        if(followees == null){
            return news;
        }
        for(int followee: followees){
            tweets = tweetsMap.get(followee);
            if(tweets == null){
                continue;
            }
            for(Tweet tweet: tweets){
                if(minHeap.size() < 10){
                   minHeap.add(tweet); 
                }
                else{
                    if(minHeap.peek().timeStamp < tweet.timeStamp){
                        minHeap.poll();
                        minHeap.add(tweet);
                    }
                }
            }
        }
        
        Tweet tweetArray[] = new Tweet[minHeap.size()];
        tweetArray = minHeap.toArray(tweetArray);
        Arrays.sort(tweetArray, new TweetComparator2());
        
        news = new ArrayList<Integer>();
        for(i = 0; i < tweetArray.length; ++i){
            news.add(tweetArray[i].tweetId);
        }
        
        return news;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> followees;
        
        if(!followeesMap.containsKey(followerId)){
            followees = new HashSet<Integer>();
            followees.add(followeeId);
            followeesMap.put(followerId, followees);
        }
        else{
            followees = followeesMap.get(followerId);
            followees.add(followeeId);
        }
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followees;
        
        if(followerId == followeeId){
            return;
        }
        
        if(followeesMap.containsKey(followerId)){
            followees = followeesMap.get(followerId);
            if(followees.contains(followeeId)){
                followees.remove(followeeId);
            }
        }
    }

    public static void main(String[] args){
        int userId0;
        int userId1;
        int tweetId0;
        int tweetId1;
        Twitter obj = new Twitter();
        List<Integer> news;
        
        userId0 = 0;
        tweetId0 = 5;
        obj.postTweet(userId0, tweetId0);
        System.out.println("user:" + userId0 + " post tweet:" + tweetId0);
        
        userId0 = 1;
        tweetId0 = 2;
        obj.postTweet(userId0, tweetId0);
        System.out.println("user:" + userId0 + " post tweet:" + tweetId0);
        
        userId0 = 0;
        userId1 = 1;
        obj.follow(userId0, userId1);
        System.out.println("user:" + userId0 + " follow user:" + userId1);

        userId0 = 0;
        news = obj.getNewsFeed(userId0);
        System.out.println("user:" + userId0 + " have tweets:" + news);
        
        userId0 = 1;
        news = obj.getNewsFeed(userId0);
        System.out.println("user:" + userId0 + " have tweets:" + news);
    }
}
