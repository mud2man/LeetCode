/* Push model
 * 1. Have a time stamp to record the post time for every tweet
 * 2. Have a put list for all user
 * 3. When a tweet post by a user u, push the tweet into the putList of user u by addTweet which is based on binary search
 * 4. When user a follow user b, add the tweet of user b with the userId = b into the tweetList of user a
 * 5. When user a unfollow user b, delete the tweet with userId = b from the tweetList of user a
 * 6. When getNewsFeed, just Retrieve the first 10 tweets from tweetList because it order by timeStamp
 */

import java.util.*;
import java.math.*;

public class Twitter{
    private class Tweet{
        int userId;
        int tweetId;
        int timeStamp;
        Tweet(int p, int t, int time){userId = p; tweetId = t; timeStamp = time;}
    }

    private class TimeStampComparator implements Comparator<Tweet>{
        @Override
        public int compare(Tweet x, Tweet y){
            return y.timeStamp - x.timeStamp;
        }
    }
    
    private int timeStamp;
    private TimeStampComparator timeStampComparator;
    private HashMap<Integer, HashSet<Integer>> putLists;
    private HashMap<Integer, LinkedList<Tweet>> tweetLists;
    
    /** Initialize your data structure here. */
    public Twitter() {
        this.putLists = new HashMap<Integer, HashSet<Integer>>();
        this.tweetLists = new HashMap<Integer, LinkedList<Tweet>>();
        this.timeStamp = 0;
        this.timeStampComparator = new TimeStampComparator();
    }
    
    private void addTweet(LinkedList<Tweet> tweetList, Tweet tweet){
        int index = Collections.binarySearch(tweetList, tweet, timeStampComparator);
        index = -(index + 1);
        tweetList.add(index, tweet);    
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        //add myself as my friend
        if(!putLists.containsKey(userId)){
            putLists.put(userId, new HashSet<Integer>());
        }
        putLists.get(userId).add(userId);
        
        //put my twitter into my friends' tweetList
        Tweet tweet = new Tweet(userId, tweetId, timeStamp++);
        for(int follower: putLists.get(userId)){
            if(!tweetLists.containsKey(follower)){
                tweetLists.put(follower, new LinkedList<Tweet>());
            }
            addTweet(tweetLists.get(follower), tweet);
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Tweet> tweetList = (tweetLists.containsKey(userId))? tweetLists.get(userId): new LinkedList<Tweet>();
        
        List<Integer> lastTen = new LinkedList<Integer>();
        for(int i = 0; i < tweetList.size() && i < 10; ++i){
            lastTen.add(tweetList.get(i).tweetId);
        }

        return lastTen;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followerId == followeeId){
            return;
        }
        
        if(!putLists.containsKey(followeeId)){
            putLists.put(followeeId, new HashSet<Integer>());
        }
        
        if(putLists.get(followeeId).contains(followerId)){
            return;
        }
        else{
            putLists.get(followeeId).add(followerId);
        }
        
        if(!tweetLists.containsKey(followerId)){
            tweetLists.put(followerId, new LinkedList<Tweet>());
        }
        LinkedList<Tweet> tweetListFollower = tweetLists.get(followerId);
        
        if(!tweetLists.containsKey(followeeId)){
            tweetLists.put(followeeId, new LinkedList<Tweet>());
        }
        LinkedList<Tweet> tweetListFollowee = tweetLists.get(followeeId);
        
        for(Tweet tweet: tweetListFollowee){
            if(tweet.userId == followeeId){
                addTweet(tweetListFollower, tweet);
            }
        }
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!putLists.containsKey(followeeId) || followerId == followeeId){
            return;
        }
        //delete the followerId from followerId's friend list
        putLists.get(followeeId).remove(followerId);
        
        //delete the unfollowing tweet from followerId's tweet List
        LinkedList<Tweet> tweetList = (tweetLists.containsKey(followerId))? tweetLists.get(followerId): new LinkedList<Tweet>();
        Iterator<Tweet> itr = tweetList.iterator();
        while (itr.hasNext()) {
            Tweet tweet = itr.next();
            if(tweet.userId == followeeId){
                itr.remove();
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
