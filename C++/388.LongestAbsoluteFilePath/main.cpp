/* Use stack: O(n), where n = string length
 * 1. Use stack to record the path
 */

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
    int lengthLongestPath(string input);
private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::lengthLongestPath(string input) {
    int maxLen;
    int currLen;
    size_t head;
    size_t tail;
    string fileName;
    int nameSize;
    int prevDepth;
    int currDepth;
    vector<int> stack;
    
    maxLen = 0;
    currLen = 0;
    input.append("\n");
    prevDepth = -1;
    head = 0;
    
    do{
        tail = input.find('\n', head);
        
        if(tail == string::npos){
            break;
        }
        
        nameSize = tail- head;
        fileName = input.substr (head, nameSize);
        
        /* caculate current depth of this fileName */
        for(currDepth = 0; fileName[currDepth] == '\t'; ++currDepth){
            nameSize = nameSize - 1;
        }
        
        /* because of filename"/" */
        ++nameSize;
        
        if(currDepth == (prevDepth + 1)){
            currLen = currLen + nameSize;
            stack.push_back(nameSize);
        }
        else if (currDepth <= prevDepth){
            for(prevDepth = prevDepth; prevDepth >= currDepth; --prevDepth){
                currLen = currLen - stack.back();
                stack.pop_back();
            }
            currLen = currLen + nameSize;
            stack.push_back(nameSize);
        }
        else{
            cout << "error" << endl;
        }
        
        prevDepth = currDepth;
        
        if((maxLen < currLen) && (fileName.find('.') != string::npos)){
            maxLen = currLen;
        }
        
        head = tail + 1;
    }while(tail!=string::npos);
    
    return max((maxLen - 1), 0);
}

int main(){
    string file = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" ;
	int maxLen;
	Solution sol;
   	
	maxLen = sol.lengthLongestPath(file);

	cout << "file: " << file << endl;
	cout << "maximum length: " << maxLen << endl;
	
	return 0;
}
