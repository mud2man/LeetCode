/* Use Dynamic Programing: O(n^2)
 * 1. Create a 2-D array to store the relationship
 * 2. Iterate all the words to confirm if they have the same relationship
 */

import java.util.*;

public class Solution{

	/* Relationship */
	private Vector< Integer > patRelationship;
	private Vector< Integer > strRelationship;
	
	/* string list */
	private Vector< String > strList;
	
    Solution(){
		patRelationship = new Vector< Integer >();
		strRelationship = new Vector< Integer >();
		strList = new Vector< String >();
	}
    
	public void parse(String pattern, String str) {
		char target;
		String strTarget;
		String strSource;
		int size;
		int i;
		int j;
		String item;

		size = pattern.length();
		
		/* Translate pattern into a relationship list */
		for(i = 0; i < size; i++){
			target = pattern.charAt(i);
			for(j = 0; j <= i; j++){
				if(target == pattern.charAt(j)){
					patRelationship.add(i, j);
					break;
				}
			}
			if(j > i){
				patRelationship.add(i, i);
			}
		}
		
		/* Parse input string into a list of strins "strList" */
		item = new String();
		for(i = 0; i < str.length(); i++){
			if(str.charAt(i) != ' '){
				item += str.charAt(i);			
			}
			else{
				strList.add(item);
				item = new String();
			}
		}
		strList.add(item);
		
		/* Translate strList into a relationship list */
		size = strList.size();
		for(i = 0; i < size; i++){
			strTarget = strList.get(i);
			for(j = 0; j <= i; j++){
				if(strTarget.compareTo(strList.get(j)) == 0){
					strRelationship.add(i, j);
					break;
				}
			}
			if(j > i){
				strRelationship.add(i, i);
			}
		}
	}

    public boolean wordPattern(String pattern, String str) {
		int idx;
		int strSize;
		int patSize;
		int strVal;
		int patVal;

		parse(pattern, str);
		strSize = strRelationship.size();
		patSize = patRelationship.size();
	
		if(strSize != patSize){
			return false;
		}
		
		for(idx = 0; idx < patSize; idx++){
			strVal = strRelationship.get(idx);
			patVal = patRelationship.get(idx);
			if(strVal != patVal){
				return false;
			}
		}

		return true;
	}
    
	public void dump() {
		System.out.println(patRelationship);
		System.out.println(strList);
		System.out.println(strRelationship);
	}

	public static void main(String[] args){

		Solution sol;
		String pattern = "itwas";
		String str = "i tttttttttttttttt wwww aa sssss";
		boolean isMatch;
		
		sol = new Solution();
		isMatch = sol.wordPattern(pattern, str);
		sol.dump();

		System.out.println("isMatch: " + isMatch);
	}
}
