/* Use prefix tree: O(#possible prefix)
 * 1. Create a prefix tree
 * 2. If encounter ',', search all the non-empty nodes
 * 3. If enounnter a specific character, search the sub-tree
 */

#include <iostream>
#include <new>

using namespace std;

class TrieNode {
public:
    // Initialize your data structure here.
    char letter;
    bool isWord;
    TrieNode* letters[26] = {NULL};
    
    TrieNode(char c) {
        letter = c;
        isWord = false;
    }
};

class WordDictionary{
public:
	WordDictionary();
    ~WordDictionary();
	void addWordHelper(string& word, int idx, TrieNode* root);
    void addWord(string word);
    bool searchHelper(string& word, int idx, TrieNode* root);
    bool search(string word);
	
private:
	TrieNode* root;
};/*End of class Solution */

WordDictionary::WordDictionary() {
    char c = '!';
    root = new TrieNode(c);
}

WordDictionary::~WordDictionary(){
}

void WordDictionary::addWordHelper(string& word, int idx, TrieNode* root){
    TrieNode* node;
    char letter;
    
    if(idx == word.size()){
        return;
    }
    
    letter = word[idx];
    
    if(root->letters[letter - 'a'] == NULL){
        node = new TrieNode(letter);
        root->letters[letter - 'a'] = node;
    }
    else{
        node = root->letters[letter - 'a'];
    }
    
    if(idx == word.size() - 1){
        node->isWord = true;
    }
    
    addWordHelper(word, idx + 1, node);
}

// Adds a word into the data structure.
void WordDictionary::addWord(string word) {
    addWordHelper(word, 0, root);
}

bool WordDictionary::searchHelper(string& word, int idx, TrieNode* root){
    int i;
    bool ret;
    
    if((idx == word.size()) && (root->isWord == true)){
        return true;
    }
    
    if(word[idx] == '.'){
        for(i = 0; i < 26; ++i){
            if(root->letters[i] != NULL){
                ret = searchHelper(word, idx + 1, root->letters[i]);
                if(ret == true){
                    return true;
                }
            }
        }
        ret = false;
    }
    else{
        if(root->letters[word[idx] - 'a'] != NULL){
            ret = searchHelper(word, idx + 1, root->letters[word[idx] - 'a']);
        }
        else{
            ret = false;
        }
    }
    
    return ret;
}

// Returns if the word is in the data structure. A word could
// contain the dot character '.' to represent any one letter.
bool WordDictionary::search(string word) {
    return searchHelper(word, 0, this->root);
}

int main()
{
    WordDictionary dic;
    string s;
    
    s = "namo";
    cout << "add word: " << s << endl;;
	dic.addWord(s);
	s = "elsie";
	cout << "add word: " << s << endl;;
	dic.addWord(s);
    
	cout << "search namo: " << dic.search("namo") << endl;
	cout << "search elsie: " << dic.search("elsie") << endl;
	cout << "search ken: " << dic.search("ken") << endl;
	cout << "search el.ie: " << dic.search("el.ie") << endl;
	cout << "search n.ma: " << dic.search("n.ma") << endl;
	
	return 0;
}
