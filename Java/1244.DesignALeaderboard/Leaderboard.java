/* Map + Heap: Time:O(KlogK), Space:O(n). We can use TreeMap to improve "top" to O(K), but need to sacrifice "addScore" and "romove" with O(logn)
 * 1. Use id2Score and score2Count to store id and score
 * 2. Use maxHeap to store top N scores
 */

import java.util.*; // Stack

public class Leaderboard {
    Map<Integer, Integer> id2Score;
    Map<Integer, Integer> score2Count;
    PriorityQueue<Integer> maxHeap;
    
    public Leaderboard() {
        id2Score = new HashMap<>();
        score2Count = new HashMap<>();
        maxHeap = new PriorityQueue<>((x, y) -> (y - x));
    }
    
    public void addScore(int playerId, int score) {
        removeScore(playerId);
        int newScore = id2Score.getOrDefault(playerId, 0) + score;
        id2Score.put(playerId, newScore);
        score2Count.put(newScore, score2Count.getOrDefault(newScore, 0) + 1);
        if(score2Count.get(newScore) == 1){
            maxHeap.add(newScore);
        }
    }
    
    public int top(int K) {
        int kSum = 0;
        List<Integer> popScores = new ArrayList<>();
        while(!maxHeap.isEmpty() && K > 0){
            int topScore = maxHeap.poll();
            int count = score2Count.get(topScore);
            popScores.add(topScore);
            kSum += topScore * Math.min(K, count);
            K -= Math.min(K, count);
        }
        for(int popScore: popScores){
            maxHeap.add(popScore);
        }
        return kSum;
    }
    
    private void removeScore(int playerId) {
        if(id2Score.containsKey(playerId) && id2Score.get(playerId) > 0){
            int oldScore = id2Score.get(playerId);
            score2Count.put(oldScore, score2Count.get(oldScore) - 1);
            if(score2Count.get(oldScore) == 0){
                maxHeap.remove(oldScore);
            }
        }
    }
    
    public void reset(int playerId) {
        removeScore(playerId);
        id2Score.put(playerId, 0);
    }
 
    public static void main(String[] args){
        Leaderboard sol = new Leaderboard();
        int playerId = 1;
        int score = 73;
        System.out.println(String.format("addScore(%d, %d)", playerId, score));
        sol.addScore(playerId, score);
        playerId = 2;
        score = 56;
        System.out.println(String.format("addScore(%d, %d)", playerId, score));
        sol.addScore(playerId, score);
        playerId = 3;
        score = 39;
        System.out.println(String.format("addScore(%d, %d)", playerId, score));
        sol.addScore(playerId, score);
        playerId = 4;
        score = 51;
        System.out.println(String.format("addScore(%d, %d)", playerId, score));
        sol.addScore(playerId, score);
        playerId = 5;
        score = 4;
        System.out.println(String.format("addScore(%d, %d)", playerId, score));
        sol.addScore(playerId, score);
        int top = 1;
        System.out.println(String.format("top(%d): %d", top, sol.top(1)));
        playerId = 1;
        System.out.println(String.format("reset(%d)", playerId));
        playerId = 2;
        System.out.println(String.format("reset(%d)", playerId));
        playerId = 2;
        score = 51;
        System.out.println(String.format("addScore(%d, %d)", playerId, score));
        top = 3;
        System.out.println(String.format("top(%d): %d", top, sol.top(1)));
    }
}
