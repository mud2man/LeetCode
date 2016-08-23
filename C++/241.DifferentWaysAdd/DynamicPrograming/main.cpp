/* Use Dynamic Programing 
 * 1. Caculate and store all results with single operant
 * 2. Caculate and store all results with two operants, based on previous results
 * 3. Repeat until the number of input operants times
 */

#include <iostream>
#include <vector>
#include <string>
#include <utility>
#include <algorithm>  // std::merge

using namespace std;

class Solution{

public:
	Solution(void);
	~Solution(void);
	void parser(string input);
	vector< int > operation(vector< int >&, vector< int >&, char&);
	vector< int > diffWaysToCompute(string input);
	void dump();

private:
	/* The list of operators */
	vector< char > operators; 

	/* Results */
	vector< vector< vector< int > > > results;
};/*End of class Solution */

Solution::Solution(void){
}

Solution::~Solution(void){

	vector< vector< vector< int > > > tmp;

	/* Release vector */
	results.swap(tmp);
}

void Solution::parser(string input){
	
	vector< int > operant;
	vector< vector< int > > operants;
	unsigned int idx;
	int digit;
	int total;
	
	total = 0;

	for(idx = 0; idx < input.length(); idx++){
		digit = input[idx] - '0';

		/* Accumulate operant */
		if((digit < 10) && (digit >= 0)){
			total = total*10 + digit;
		}
		/* Parse operator and operant */
		else{
			operant.push_back(total);
			operants.push_back(operant);
			operant.pop_back();
			operators.push_back(input[idx]);
			total = 0;
		}
	}
	operant.push_back(total);
	operants.push_back(operant);
	results.resize(operators.size() + 1);
	results[0] = operants;
}

vector<int> Solution::diffWaysToCompute(string input){
	
	unsigned int operatorNum;
	unsigned int operatorsCount;
	unsigned int idx;
	unsigned int offset;
	vector< int > result;
	vector< int > subresult;
	vector< int > left;
	vector< int > right;
	
	parser(input);

	operatorsCount = operators.size();
	
	for(operatorNum = 1; operatorNum <= operatorsCount; operatorNum++){
		for(idx = 0; idx <= (operatorsCount - operatorNum); idx++){
			
			result.clear();

			for(offset = 0; offset < operatorNum; offset++){
				left = results[offset][idx]; 
				right = results[operatorNum - offset - 1][idx + offset + 1];
				subresult = operation(left, right, operators[idx + offset]);
				
				/* Merge results */
				result.reserve(result.size() + subresult.size());
				result.insert(result.end(), subresult.begin(), subresult.end());
			}

			results[operatorNum].push_back(result);
		}
	}

	return results.back().back();
}


vector< int > Solution::operation(vector< int >& left, vector< int >& right, char& optr){
	
	unsigned int idx;
	unsigned int jdx;
	unsigned int leftsSize;
	unsigned int rightsSize;
	vector< int > results;
	int result; 

	leftsSize = left.size();
	rightsSize = right.size();

	switch(optr){
		case '+':
			for(idx = 0; idx < leftsSize; idx++){
				for(jdx = 0; jdx < rightsSize; jdx++){
					result = left[idx] + right[jdx];
					results.push_back(result);
				}
			}
			break;
		
		case '-' :
			for(idx = 0; idx < leftsSize; idx++){
				for(jdx = 0; jdx < rightsSize; jdx++){
					result = left[idx] - right[jdx];
					results.push_back(result);
				}
			}
			break;
				
		case '*' :
			for(idx = 0; idx < leftsSize; idx++){
				for(jdx = 0; jdx < rightsSize; jdx++){
					result = left[idx] * right[jdx];
					results.push_back(result);
				}
			}
			break;
	}

	return results;
}

void Solution::dump(void){
	
	unsigned int idx;
	unsigned int jdx;
	unsigned int kdx;

	cout << "roperators: ";
	for(idx = 0; idx < operators.size(); idx++){
		cout << operators[idx] << ",";
	}
	cout << endl;

	cout << "results: ";
	for(idx = 0; idx < results.size(); idx++){
		cout << idx << " operants:" << endl;
		for(jdx = 0; jdx < results[idx].size(); jdx++){
			for(kdx = 0; kdx < results[idx][jdx].size(); kdx++){
				cout << results[idx][jdx][kdx] << ",";
			}
			cout << endl;
		}
		cout << endl;
	}
	cout << endl;
}

int main(){

	string s = "21*3-4*5+1";
	Solution sol;
	
	cout << "string: " << s << endl;

	sol.diffWaysToCompute(s);

	sol.dump();
}
