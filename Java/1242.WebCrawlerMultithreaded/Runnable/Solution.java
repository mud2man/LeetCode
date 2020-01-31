/* Multi-thread: 
 * 1. Implement Runnable and use join to wait
 */

import java.util.*;
import java.util.concurrent.*;

interface HtmlParser { 
    public List<String> getUrls(String url);
}

public class Solution{
    private static String getHostName(String url) {
        int index = url.indexOf('/', 7);
        return (index != -1) ? url.substring(0, index): url;
    }
    
    private class Crawler implements Runnable {
        private ConcurrentSkipListSet<String> visitedUrls;
        private String startUrl;
        private HtmlParser htmlParser;
        private String hostName;
        
        public Crawler(ConcurrentSkipListSet<String> visitedUrls, String startUrl, HtmlParser htmlParser, String hostName){
            this.visitedUrls = visitedUrls;
            this.startUrl = startUrl;
            this.htmlParser = htmlParser;
            this.hostName = hostName;
        }
        
        @Override
        public void run(){
            if(!visitedUrls.contains(startUrl)){
                visitedUrls.add(startUrl);
                List<Thread> threads = new ArrayList<>();
                for(String nextUrl: htmlParser.getUrls(startUrl)){
                    if(hostName.equals(getHostName(nextUrl)) && !visitedUrls.contains(nextUrl)){
                        Thread thread = new Thread(new Crawler(visitedUrls, nextUrl, htmlParser, hostName));
                        thread.start();
                        threads.add(thread);
                    }
                }
                
                for(Thread thread: threads){
                    try {
                        thread.join();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        ConcurrentSkipListSet<String> visitedUrls = new ConcurrentSkipListSet<>(); 
        Thread thread = new Thread(new Crawler(visitedUrls, startUrl, htmlParser, getHostName(startUrl)));
        thread.start();
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(visitedUrls);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println("we have no test case");
    }
}
