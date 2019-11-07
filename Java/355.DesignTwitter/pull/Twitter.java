/* Pull model: Time:O(1), Space:O(n)
 * 1. Have "user2Posts" to record the user->tweets, and keep up to 10 tweets
 * 2. Have "user2Followees" to record user->followees
 * 3. Have "timeStamp" in Tweet, and sorting by it
 * 4. Output a list of tweets acording followees and user2Tweets with decending order of timeStamp 
 */

import java.util.stream.Collectors;
import java.util.*;
import java.math.*;

public class Twitter{
    int time;
    Map<Integer, Set<Integer>> user2Followees;
    Map<Integer, Deque<int[]>> user2Posts;
    
    /** Initialize your data structure here. */
    public Twitter() {
        time = 0;
        user2Followees = new HashMap<>();
        user2Posts = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        user2Posts.computeIfAbsent(userId, key -> new LinkedList<>()).add(new int[]{tweetId, time++});
        Deque<int[]> posts = user2Posts.get(userId);
        if(posts.size() > 10){
            posts.pollFirst();
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> followees = new HashSet<>();
        followees.add(userId);
        followees.addAll(user2Followees.getOrDefault(userId, new HashSet<>()));
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        for(int followee: followees){
            Deque<int[]> posts = user2Posts.getOrDefault(followee, new LinkedList<>());
            for(int[] post: posts){
                if(minHeap.size() < 10){
                    minHeap.add(post);
                }else{
                    if(minHeap.peek()[1] < post[1]){
                        minHeap.poll();
                        minHeap.add(post);
                    }
                }
            }
        }
        LinkedList<Integer> newsFeed = new LinkedList<>();
        while(!minHeap.isEmpty()){
            newsFeed.addFirst(minHeap.poll()[0]);
        }
        return newsFeed;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        user2Followees.computeIfAbsent(followerId, key -> new HashSet<>()).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        user2Followees.computeIfAbsent(followerId, key -> new HashSet<>()).remove(followeeId);
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
