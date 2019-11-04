/* DFS + Memory: Time:O(n), Space:O(n)
 * 1. Use DFS and set "visited" to prevent revisit url
 */

import java.util.*;

interface HtmlParser {
    public List<String> getUrls(String url);
}

public class Solution{
    private String getHostName(String url){
        int endIdx = url.indexOf('/', 7);
        endIdx = (endIdx == -1)? url.length(): endIdx;
        return url.substring(7, endIdx);
    }
    
    private void crawlRecursive(String startUrl, HtmlParser htmlParser, Set<String> visited, String hostName){
        if(visited.contains(startUrl) || !hostName.equals(getHostName(startUrl))){
            return;
        }
        visited.add(startUrl);
        List<String> nextUrls = htmlParser.getUrls(startUrl);
        for(String nextUrl: nextUrls){
            crawlRecursive(nextUrl, htmlParser, visited, hostName);
        }
    }
    
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Set<String> visited = new HashSet<>();
        crawlRecursive(startUrl, htmlParser, visited, getHostName(startUrl));
        List<String> urls = new ArrayList<>();
        urls.addAll(visited);
        return urls;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println("No test this time");
    }
}
