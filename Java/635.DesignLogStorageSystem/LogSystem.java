/* TreeMap: Time:O(n), Space:O(n). However, we can use naive solution, just do string compare to filter the id
 * 1. Use treemap store time-id key value
 * 2. Use floorKey and ceilingKey to get the lower bound, and upper bound
 * 3. Get the submap give floorKey and ceilingKey, and then check entry and return the answer
 */

import java.util.*;

public class LogSystem {
    TreeMap<Integer, Integer> timeToId;
    String[] granularities;
    int[] bases;
        
    public LogSystem() {
        timeToId = new TreeMap<Integer, Integer>();
        granularities = new String[]{"Year", "Month", "Day", "Hour", "Minute", "Second"};
        bases = new int[] {1, 13, 32, 24, 60 ,60};
    }
    
    public void put(int id, String timestamp) {
        String[] timeStrings = timestamp.split(":");
        int time = 0;
        int base = 1;
        for(int i = timeStrings.length - 1; i >= 0; --i){
            time += (i == 0)? base * (Integer.parseInt(timeStrings[i]) - 2000) : base * Integer.parseInt(timeStrings[i]);
            base = base * bases[i];
        }
        timeToId.put(time, id);
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        String[] startTimeStrings = s.split(":");
        String[] endTimeStrings = e.split(":");
        int startTime = 0;
        int endTime = 0;
        int base = 1;
        boolean hit = false;
        for(int i = startTimeStrings.length - 1; i >= 0; --i){
            hit = gra.equals(granularities[i])? true: hit;
            if(hit){
                int time = (i == 0)? (Integer.parseInt(startTimeStrings[i]) - 2000): Integer.parseInt(startTimeStrings[i]);
                startTime += base * time;
                time = (i == 0)? (Integer.parseInt(endTimeStrings[i]) - 2000): Integer.parseInt(endTimeStrings[i]);
                endTime += base * time;
            }
            else{
                int time = (i == 0)? 17: bases[i] - 1;
                endTime += base * time;
            }
            base = base * bases[i];
        }
        
        List<Integer> ids = new ArrayList<Integer>();
        int floor = (timeToId.floorKey(startTime) == null)? Integer.MIN_VALUE: timeToId.floorKey(startTime);
        int ceil = (timeToId.ceilingKey(endTime) == null)? Integer.MAX_VALUE: timeToId.ceilingKey(endTime);
        Map<Integer, Integer> candidates = timeToId.subMap(floor, true, ceil, true);
        for(Map.Entry<Integer, Integer> entry: candidates.entrySet()){
            int key = entry.getKey();
            if(key >= startTime && key <= endTime){
                ids.add(entry.getValue());
            }
        }
        return ids;
    }

    public static void main(String[] args){
        LogSystem sol = new LogSystem();;
        int id = 6;
        String timestamp;

        id = 1;
        timestamp = "2017:01:01:23:59:59";
        sol.put(id, timestamp);
        System.out.println("put(" + id + ", " + timestamp + ")");

        id = 2;
        timestamp = "2017:01:01:22:59:59";
        sol.put(id, timestamp);
        System.out.println("put(" + id + ", " + timestamp + ")");

        id = 3;
        timestamp = "2016:01:01:00:00:00";
        sol.put(id, timestamp);
        System.out.println("put(" + id + ", " + timestamp + ")");

        String start;
        String end;
        String granularity;
        start = "2016:01:01:01:01:01";
        end = "2017:01:01:23:00:00";
        granularity = "Year";
        System.out.println("retrieve(" + start + ", " + end + ", " + granularity + "): " + sol.retrieve(start, end, granularity));

        start = "2016:01:01:01:01:01";
        end = "2017:01:01:23:00:00";
        granularity = "Hour";
        System.out.println("retrieve(" + start + ", " + end + ", " + granularity + "): " + sol.retrieve(start, end, granularity));
    }
}
