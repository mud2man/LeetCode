/* TreeMap: Time:O(logn), Space:O(n).
 * 1. Have a treeMap to store all the intervals
 * 2. When addNum, merge the previous interval if possible, otherwise have a new interval (val, val)
 * 3. Then, merge the next interval if possible
 */

import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class SummaryRanges {
    TreeMap<Integer, Integer> map;
    
    /** Initialize your data structure here. */
    public SummaryRanges() {
        map = new TreeMap<>();
    }
    
    public void addNum(int val) {
        //merge the previous interval if possible
        Integer prevStart = map.floorKey(val);
        if(prevStart == null){
            map.put(val, val);
            prevStart = val;
        }
        else{
            int prevEnd = map.get(prevStart);
            if(prevStart <= val && prevEnd >= val){
                return;
            }
            else if(prevEnd == val - 1){
                map.put(prevStart, val);
            }
            else{
                map.put(val, val);
                prevStart = val;
            }
        }
        
        //merge the next interval if possible
        int prevEnd = map.get(prevStart);
        if(map.containsKey(prevEnd + 1)){
            map.put(prevStart, map.get(prevEnd + 1));
            map.remove(prevEnd + 1);
        }
    }
    
    public List<Interval> getIntervals() {
        List<Interval> intervals = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            intervals.add(new Interval(entry.getKey(), entry.getValue()));
        }
        return intervals;
    }

    private void dump(List<Interval> intervals){
        for(Interval i: intervals){
            System.out.print("[" + i.start + ", " + i.end + "], ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        SummaryRanges sol = new SummaryRanges();
        int num;

        num = 1;
        sol.addNum(num);
        System.out.println("addNum(" + num + ")");
        System.out.print("getIntervals(): ");
        sol.dump(sol.getIntervals());

        num = 3;
        sol.addNum(num);
        System.out.println("addNum(" + num + ")");
        System.out.print("getIntervals(): ");
        sol.dump(sol.getIntervals());
        
        num = 7;
        sol.addNum(num);
        System.out.println("addNum(" + num + ")");
        System.out.print("getIntervals(): ");
        sol.dump(sol.getIntervals());
        
        num = 2;
        sol.addNum(num);
        System.out.println("addNum(" + num + ")");
        System.out.print("getIntervals(): ");
        sol.dump(sol.getIntervals());
        
        num = 6;
        sol.addNum(num);
        System.out.println("addNum(" + num + ")");
        System.out.print("getIntervals(): ");
        sol.dump(sol.getIntervals());
    }
}
