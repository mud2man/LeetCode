/* Multi-thread: 
 * 1. Implement Callable and use future get to wait
 */

import java.util.*;
import java.util.concurrent.*;

interface HtmlParser { 
    public List<String> getUrls(String url);
}

public class Solution{
    private ConcurrentSkipListSet<String> visitedUrls = new ConcurrentSkipListSet<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(100000);
    private String getHostName(String url){
        int index = url.indexOf('/', 7);
        return (index != -1) ? url.substring(0, index): url;
    }
    
    private Callable<Void> crawlCallable(String startUrl, HtmlParser htmlParser, String hostName) throws InterruptedException {
        return () -> {
            if(visitedUrls.contains(startUrl)){
                return null; 
            }else{
                visitedUrls.add(startUrl);
                List<String> nextUrls = htmlParser.getUrls(startUrl);
                List<String> filteredUrls = new ArrayList<>();
                for(String nextUrl: nextUrls){
                    if(hostName.equals(getHostName(nextUrl))){
                        filteredUrls.add(nextUrl);
                    }
                }
                List<Future<Void>> rets = new ArrayList<>();
                for(String filteredUrl: filteredUrls){
                    Callable<Void> crawler = crawlCallable(filteredUrl, htmlParser, hostName);
                    rets.add(executor.submit(crawler));
                }
                for(Future<Void> ret: rets){
                    ret.get();
                }
                return null;
            }  
        };
    }
    
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        try {
            Callable<Void> crawler = crawlCallable(startUrl, htmlParser, getHostName(startUrl));
            Future<Void> ret = executor.submit(crawler);
            ret.get();
        } catch (Throwable e) {
            throw new IllegalStateException("task interrupted", e);
        }
        return new ArrayList<>(visitedUrls);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println("we have no test case");
    }
}
