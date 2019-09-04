/* Pull model: Time:O(1), Space:O(n)
 * 1. Have "user2Tweets" to record the user->tweets, and keep up to 10 tweets
 * 2. Have "user2Followees" to record user->followees
 * 3. Have "timeStamp" in Tweet, and sorting by it
 * 4. Output a list of tweets acording followees and user2Tweets with decending order of timeStamp 
 * 5. Each user need to follow herself, and cannot be unfollowed
 */

import java.util.stream.Collectors;
import java.util.*;
import java.math.*;

public class Twitter{
    private class Tweet{
        int id;
        Integer timeStamp;
        Tweet(int i, int t){id = i; timeStamp = t;}
    }
    Map<Integer, Deque<Tweet>> user2Tweets;
    Map<Integer, Set<Integer>> user2Followees;
    int timeStamp;
    /** Initialize your data structure here. */
    public Twitter() {
        user2Tweets = new HashMap<>();
        user2Followees = new HashMap<>();
        timeStamp = 0;
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        user2Tweets.computeIfAbsent(userId, key -> new LinkedList<>()).add(new Tweet(tweetId, timeStamp++));
        user2Followees.computeIfAbsent(userId, key -> new HashSet<>()).add(userId);
        if(user2Tweets.get(userId).size() > 10){
            user2Tweets.get(userId).pollFirst();
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Tweet> news = new ArrayList<>();
        Set<Integer> followees = user2Followees.getOrDefault(userId, new HashSet<>());
        for(int followee: followees){
            news.addAll(user2Tweets.getOrDefault(followee, new LinkedList<>()));
            Collections.sort(news, (x, y) -> {return y.timeStamp.compareTo(x.timeStamp);});
            if(news.size() > 10) {
                news = news.subList(0, 10);
            }
        }
        return news.stream().map(tweet -> tweet.id).collect(Collectors.toList());
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        user2Followees.computeIfAbsent(followerId, key -> new HashSet<>()).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(user2Followees.containsKey(followerId) && followerId != followeeId){
            user2Followees.get(followerId).remove(followeeId);
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
