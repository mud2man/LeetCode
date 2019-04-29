import java.util.*;

public class Solution{
    private Boolean isLetterLog(String log){
        int index = log.indexOf(' ');
        char c = log.charAt(index + 1);
        return Character.isLetter(c);
    }
    
    private class LogComparator implements Comparator<String>{
        public int compare(String x , String y){
            int indexX = x.indexOf(' ');
            String identifierX = x.substring(0, indexX);
            String logX = x.substring(indexX + 1);
            int indexY = y.indexOf(' ');
            String identifierY = y.substring(0, indexY);
            String logY = y.substring(indexY + 1);
            return logX.equals(logY)? identifierX.compareTo(identifierY): logX.compareTo(logY);
        }
    }
    
    public String[] reorderLogFiles(String[] logs) {
        int ptr = -1;
        for(int i = 0; i < logs.length; ++i){
            String log = logs[i];
            if(isLetterLog(log)){
                String temp = logs[++ptr];
                logs[ptr] = log;
                logs[i] = temp;
            }
        }
        String[] letterLogs = Arrays.copyOf(logs, ptr + 1);
        Arrays.sort(letterLogs, new LogComparator());
        System.arraycopy(letterLogs, 0, logs, 0, letterLogs.length);
        return logs;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String[] logs = {"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};
        System.out.println("reorderLogFiles[]: " + Arrays.toString(sol.reorderLogFiles(logs)));
    }
}
