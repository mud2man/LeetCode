/* Use recursive method 
 * 1. Parse the string, and transfer characters as integers 
 * 2. Pick up all the legal unused operators, and push them push it into orderList
 * 3. Define legal operator:
 * 3.1. Increasing order 
 * 3.2. Decreasing order, but the current distance between the previous operator is 1
 */

#include <iostream>
#include <vector>
#include <string>
#include <vector>
#include <utility>
#include <stack>

using namespace std;

class Solution{

public:
	Solution(void);
	~Solution(void);
	void parser(string input);
	int operation(int&, int&, char& );
	unsigned int distence(unsigned int&, unsigned int& );
	void expandandCaculate();
	vector< int > diffWaysToCompute(string input);
	void dump();

private:
	/* The list of operants */
	vector< int > operants;
	
	/* The list of operators */
	vector< char > operators; 

	/* The opperator order list */
	vector< int > orderList;
	
	/* The usage information list */
	vector< bool > usedList;

	/* Results */
	vector<int> results;
};/*End of class Solution */

Solution::Solution(void){
}

Solution::~Solution(void){
}

vector<int> Solution::diffWaysToCompute(string input){
	
	unsigned int operatorIdx;

	parser(input);

	if(operants.size() == 1){
		results.push_back(operants[0]);
	}

	for(operatorIdx = 0; operatorIdx < operators.size(); operatorIdx++){
		orderList.push_back(operatorIdx);
		usedList[operatorIdx] = 1;
		expandandCaculate();
	}

	return results;
}

void Solution::parser(string input){
	unsigned int idx;
	int digit;
	int operant;
	
	operant = 0;
	for(idx = 0; idx < input.length(); idx++){
		digit = input[idx] - '0';

		/* Parse operant */
		if((digit < 10) && (digit >= 0)){
			operant = operant*10 + digit;
		}
		/* Parse operator */
		else{
			operants.push_back(operant);
			operators.push_back(input[idx]);
			operant = 0;
		}
	}
	operants.push_back(operant);
	
	usedList.resize(operators.size());
	for(idx = 0; idx < usedList.size(); idx++)
	{
		usedList[idx] = 0;
	}
}

unsigned int Solution::distence(unsigned int& prevOperatorId, unsigned  int& currOperatorId){
	unsigned int idx;
	unsigned int distence;

	distence = 0;
	
	for(idx = currOperatorId; idx <= prevOperatorId; idx++){
		distence += (usedList[idx])? 0: 1;
	}

	return distence;
}

int Solution::operation(int& leftOperant, int& rightOperant, char& optr){
	int result; 
	
	switch(optr){
		case '+':
			result = leftOperant + rightOperant;
			break;
		
		case '-' :
			result = leftOperant - rightOperant;
			break;
				
		case '*' :
			result = leftOperant * rightOperant;
			break;
	}

	return result;
}

void Solution::expandandCaculate(){
	unsigned int idx;
	unsigned int orderListSize;
	unsigned int operantsSize;
	unsigned int operatorsSize;
	unsigned int previousOperatorId;
	int result;
	char optr;
	int leftOperant;
	int rightOperant;
	int usedOptrId;
	int operatorIdx;
	vector<bool> operantsFlag;
	stack<int> calcStack; 
	
	result = 0;
	orderListSize = orderList.size();
	operantsSize = operants.size();
	operatorsSize = operators.size();

	/* Cacluate the result based on orderList, operants, operators*/
	if(orderListSize == operatorsSize){
		/* Reset operantsFlag */
		operantsFlag.resize(operantsSize);
		for(idx = 0; idx < operantsSize; idx++){
			operantsFlag[idx] = 0;
		}

		for(idx = 0; idx < orderListSize; idx++){
			operatorIdx = orderList[idx];
			optr = operators[operatorIdx];
	
			/* Acquire the right operant */
			if(!operantsFlag[operatorIdx + 1]){
				rightOperant = operants[operatorIdx + 1];
				operantsFlag[operatorIdx + 1] = 1;
			}
			else{
				rightOperant = calcStack.top();
				calcStack.pop();
			}

			/* Acquire the left operant */
			if(!operantsFlag[operatorIdx]){
				leftOperant = operants[operatorIdx];
				operantsFlag[operatorIdx] = 1;
			}
			else{
				leftOperant = calcStack.top();
				calcStack.pop();
			}

			result = operation(leftOperant, rightOperant, optr);
			calcStack.push(result);
		}
		results.push_back(calcStack.top());
		usedOptrId = orderList[orderList.size() - 1];
		usedList[usedOptrId] = 0;
		orderList.pop_back();

		return ;
	} // end of if(orderListSize == operators.size())

	for(idx = 0; idx < operatorsSize; idx++){
		previousOperatorId = orderList[orderList.size() - 1];

		if((previousOperatorId < idx) && (usedList[idx] == 0)){
			orderList.push_back(idx);
			usedList[idx] = 1; 
			expandandCaculate();
		}
		else if((distence(previousOperatorId, idx) == 1) && (usedList[idx] == 0)){
			orderList.push_back(idx);
			usedList[idx] = 1; 
			expandandCaculate();
		}
	}
	usedOptrId = orderList[orderList.size() - 1];
	usedList[usedOptrId] = 0;
	orderList.pop_back();

	return;
}

void Solution::dump(void){
	unsigned int idx;

	cout << "operants: ";
	for(idx = 0; idx < operants.size(); idx++){
		cout << operants[idx] << ",";
	}
	cout << endl;
	
	cout << "operators: ";
	for(idx = 0; idx < operators.size(); idx++){
		cout << operators[idx] << ",";
	}
	cout << endl;

	cout << "results: ";
	for(idx = 0; idx < results.size(); idx++){
		cout << results[idx] << ",";
	}
	cout << endl;
}

int main(){

	string s = "21*3-4*5+1*3-3+11*4-21*3-111";
	Solution sol;
	
	cout << "string: " << s << endl;

	sol.diffWaysToCompute(s);
}
