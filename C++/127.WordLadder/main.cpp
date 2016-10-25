
#include <iostream>
#include <vector>
#include <unordered_set>
#include <queue>

using namespace std;

class Solution {

public:
	Solution();
	~Solution();
	void addNextWord(string word, unordered_set<string>& wordList, queue<string>& toVisit);
	int ladderLength(string beginWord, string endWord, unordered_set<string>& wordList);

private:
    queue<string> toVisit;
};/*End of class Solution */

Solution::Solution(){
}

Solution::~Solution(){
}

void Solution::addNextWord(string word, unordered_set<string>& wordList, queue<string>& toVisit){
    int i;
    int j;
    char c;
    
    for(i = 0; i < word.size(); ++i){
        c = word[i];
        for(j = 0; j < 26; ++j){
            word[i] = 'a' + j;
            if(wordList.find(word) != wordList.end()){
                toVisit.push(word);
                wordList.erase(word);
            }
        }
        word[i] = c;
    }
}

int Solution::ladderLength(string beginWord, string endWord, unordered_set<string>& wordList) {
    int i;
    int size;
    int dist;
    string visitWord;
    
    dist = 2;
    toVisit.push(beginWord);
    wordList.insert(endWord);
    
    while(!toVisit.empty()){
        size = toVisit.size();
        for(i = 0; i < size; ++i){
            visitWord = toVisit.front();
            toVisit.pop();
            if(visitWord.compare(endWord) == 0){
                return (dist - 1);
            }
            addNextWord(visitWord, wordList, toVisit);
        }
        dist++;
    }
    
    return 0;
}

int main(){
    Solution sol;
    //string beginWord = "hit";
	//string endWord = "cog";
	//unordered_set<string> wordList = {"hot", "dot", "dog", "lot", "log"};
    string beginWord = "a";
	string endWord = "c";
	unordered_set<string> wordList = {"a", "b", "c"};

	cout << "lenght: " << sol.ladderLength(beginWord, endWord, wordList) << endl;

	return 0;
}
