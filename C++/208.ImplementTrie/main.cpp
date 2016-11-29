/* O(#letters)
 * 1. Use TrieNode* letters[26] to store the root of sub-tree rooted from letter[i]
 * 2. Use isWord to record the current is the end of a word 
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

class Trie
{
public:
	Trie();
	~Trie();
	void insertHelper(string& word, int idx, TrieNode* root);
	void insert(string word);
    bool search(string word);
    bool startsWith(string prefix);
private:
	TrieNode* root;
};/*End of class Solution */

Trie::Trie(){
    char c = '!';
    root = new TrieNode(c);
}

Trie::~Trie(){
}

void Trie::insertHelper(string& word, int idx, TrieNode* root){
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

    insertHelper(word, idx + 1, node);
}

// Inserts a word into the trie.
void Trie::insert(string word) {
    insertHelper(word, 0, root);
}

// Returns if the word is in the trie.
bool Trie::search(string word) {
    int i;
    TrieNode* node;
    char c;
   	
    node = this->root;
    for(i = 0; i < word.size(); ++i){
        c = word[i];
        
        if(node->letters[c - 'a'] != NULL){
            node = node->letters[c - 'a'];
        }
        else{
            return false;
        }
    }
    
    if(node->isWord == true){
        return true;
    }
    else{
        return false;
    }
}

// Returns if there is any word in the trie
// that starts with the given prefix.
bool Trie::startsWith(string prefix) {
    int i;
    TrieNode* node;
    char c;
    
    node = this->root;
    for(i = 0; i < prefix.size(); ++i){
        c = prefix[i];
        
        if(node->letters[c - 'a'] != NULL){
            node = node->letters[c - 'a'];
        }
        else{
            return false;
        }
    }
    
    return true;
}

int main()
{
    Trie trie;
    string s;
    
    s = "namo";
    cout << "insert: " << s << endl;;
	trie.insert(s);
	s = "elsie";
	cout << "insert: " << s << endl;;
	trie.insert(s);
    
	cout << "search namo: " << trie.search("namo") << endl;
	cout << "search elsie: " << trie.search("elsie") << endl;
	cout << "search ken: " << trie.search("ken") << endl;
	cout << "search elsi-: " << trie.startsWith("elsi") << endl;
	cout << "search ne-: " << trie.startsWith("ne") << endl;
	
	return 0;
}
