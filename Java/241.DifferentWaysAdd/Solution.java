/* Use Dynamic Programing 
 * 1. Caculate and store all results with single operant
 * 2. Caculate and store all results with two operants, based on previous results
 * 3. Repeat until the number of input operants times
 */

import java.util.*; // Stack

public class Solution{

	/* The list of operators */
	private List< Character > operators; 

	/* Results */
	private List< List< List< Integer > > > results;
	
    Solution(){
		operators = new ArrayList< Character >();
		results = new ArrayList< List< List< Integer > > >();
	}

	public void parse(String input){
		List< Integer > operant;
		List< List< Integer > > operants;
		int digit;
		int total;
		
		operants = new ArrayList< List< Integer > >();
		total = 0;
    
		for(char c: input.toCharArray()){
			digit = Character.getNumericValue(c);
    
			/* Accumulate operant */
			if((digit < 10) && (digit >= 0)){
				total = total*10 + digit;
			}
			/* Parse operator and operant */
			else{
				operant = new ArrayList< Integer >();
				operant.add(total);
				operants.add(operant);
				operators.add(c);
				total = 0;
			}
		}

		operant = new ArrayList< Integer >();
		operant.add(total);
		operants.add(operant);
		results.add(operants);

		System.out.println(results);
		System.out.println(operators);
	}

	List< Integer > operation(List< Integer > lefts, List< Integer > rights, char optr){
	
		List< Integer > results;
		int result;

		results = new ArrayList< Integer>();
    
		switch(optr){
			case '+':
				for(int left: lefts){
					for(int right: rights){
						result = left + right;
						results.add(result);
					}
				}
				break;
			
			case '-' :
				for(int left: lefts){
					for(int right: rights){
						result = left - right;
						results.add(result);
					}
				}
				break;
					
			case '*' :
				for(int left: lefts){
					for(int right: rights){
						result = left * right;
						results.add(result);
					}
				}
				break;
		}
		return results;
	}

	public List<Integer> diffWaysToCompute(String input){

		int operatorNum;
		int operatorsCount;
		int idx;
		int offset;
		List< Integer > result;
		List< Integer > subresult;
		List< Integer > left;
		List< Integer > right;
		List <List< Integer > > lastrow;

		parse(input);

		operatorsCount = operators.size();
		
		for(operatorNum = 1; operatorNum <= operatorsCount; operatorNum++){

			results.add(operatorNum, new ArrayList< List <Integer > >());

			for(idx = 0; idx <= (operatorsCount - operatorNum); idx++){
				
				result = new ArrayList< Integer>();
    
				for(offset = 0; offset < operatorNum; offset++){
					left = results.get(offset).get(idx); 
					right = results.get(operatorNum - offset - 1).get(idx + offset + 1);
					subresult = operation(left, right, operators.get(idx + offset));
					
					/* Merge results */
					result.addAll(subresult);
				}
    
				results.get(operatorNum).add(result);
			}
		}

		return results.get(operatorsCount).get(0);
	}

	public static void main(String[] args)
	{
		List<Integer> answer;
		String s = "21*3-4*5+1";
		Solution sol;
	
		System.out.println("Input: " +  s);
		
		sol = new Solution();
		answer = sol.diffWaysToCompute(s);
		
		System.out.println("Answer: " +  answer);
	}
}
