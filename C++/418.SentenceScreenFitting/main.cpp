/* O(max{cols*sentence.size(), rows))
 * 1. caculate the number of words a single row can contains based on the word index
 * 2. accumulate the number of words with the incremental of rows
 */

#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
    int wordsTyping(vector<string>& sentence, int rows, int cols);
private:
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

int Solution::wordsTyping(vector<string>& sentence, int rows, int cols) {
    int colIdx;
    int rowIdx;
    int wordIdx;
    int idx;
    int wordNum;
    vector<int> count;
    
    count.assign(sentence.size(), 0);
    /* caculate the number of words a single row can contain */
    for(idx = 0; idx < count.size(); ++idx){
        colIdx = 0;
        wordIdx = idx;
        while(colIdx < cols){
            if((colIdx + sentence[wordIdx].size() - 1) < cols){
                colIdx = colIdx + sentence[wordIdx].size() + 1;
                wordIdx = (wordIdx + 1) % sentence.size();
                count[idx] = count[idx] + 1;
            }
            else{
                break;
            }
        }
    }
    
    wordIdx = 0;
    wordNum = 0;
    for(rowIdx = 0; rowIdx < rows; ++rowIdx){
        wordNum = wordNum + count[wordIdx];
        wordIdx = wordNum % sentence.size();
    }
    
    return wordNum / sentence.size();
}

int main(){
    Solution sol;
    int rows;
    int cols;
	vector<string> sentence;
	int sentenceNum;
	int i;
   	
    rows = 4;
    cols = 5;
	sentence.push_back("I");
	sentence.push_back("had");
	sentence.push_back("apple");
	sentence.push_back("pie"); 
   	
	sentenceNum = sol.wordsTyping(sentence, rows, cols);
	
	cout << "sentence: " << endl;
	for(i = 0; i < sentence.size(); ++i){
		cout << sentence[i] << endl;
	}

	cout << "senetenceNum: " << sentenceNum << endl;
	
	return 0;
}
