/* Use stack, Complexity = O(n)
 * 1. Traverse all element, if encounter operator stop, and caculate 
 * 2. If encounter operand, push it into stack
 */

#include <iostream>
#include <vector>
#include <string>


using namespace std;


class Solution {

public:
	Solution();
	~Solution();
    int evalRPN(vector<string>& tokens);

private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::evalRPN(vector<string>& tokens) {
    vector<int> stack;
    int i;
    int rightOperand;
    int leftOperand;
    
    for(i = 0; i < tokens.size(); ++i){
        if((tokens[i] == "+") || (tokens[i] == "-") || (tokens[i] == "*") || (tokens[i] == "/")){
            rightOperand = stack.back();
            stack.pop_back();
            leftOperand = stack.back();
            stack.pop_back();
            
            if(tokens[i] == "+"){
                stack.push_back(leftOperand + rightOperand);
            }
            else if(tokens[i] == "-"){
                stack.push_back(leftOperand - rightOperand);
            }
            else if(tokens[i] == "*"){
                stack.push_back(leftOperand * rightOperand);
            }
            else{
                stack.push_back(leftOperand / rightOperand);
            }
        }
        else{
            stack.push_back(stoi(tokens[i]));
        }
    }
    
    return stack.back();
}

int main(){
	vector<string> tokens;
    Solution sol;

	tokens.push_back("2");
	tokens.push_back("1");
	tokens.push_back("+");
	tokens.push_back("3");
	tokens.push_back("*");

	cout << "answer: " << sol.evalRPN(tokens) << endl;
	
	return 0;
}
