/*
 * 1. Traverse every element in the given board first
 * 2. If the the element is equal to the fisrt element in the word
 * 3. Use depth-first traversal to check if any pattern matched
 */

#include <iostream>
#include <vector>
#include <new>

using namespace std;

typedef struct node
{
	unsigned int	depth;
    int	id;
	int	direct;
	char target;
}node_t;

#define RIGHT 0
#define DOWN  1
#define LEFT  2
#define UP    3
#define BACK  4

class Solution {
public:

/* Report the next ID without overlap */
int nextId(node_t * pnode)
{
    int nextid;
	int rowid;
	int colid;
	    
    rowid = pnode->id / bwidth;
	colid = pnode->id % bwidth;

    while( pnode->direct != BACK )
	{
	    switch(pnode->direct)
		{
			case RIGHT:
			    /* On the rightest colum */
				if(colid == (bwidth - 1))
				{
				    /*On the lowest row */
					if(rowid == (bdepth - 1))
					{
				        nextid = pnode->id - 1;
						pnode->direct = UP;
					}
					else
					{
				        nextid = pnode->id + bwidth;
						pnode->direct = LEFT;
					}
				}
			    /* On the lowest row */
				else if(rowid == (bdepth - 1))
				{
					/*On the leftest column */
					if(colid == 0)
					{
				        nextid = pnode->id + 1;
						pnode->direct = UP;
					}
					else
					{
				        nextid = pnode->id + 1;
						pnode->direct = LEFT;
					}
				}
				else
				{
				    nextid = pnode->id + 1;
					pnode->direct = DOWN;
				}

				break;
			case DOWN:
			    /* On the lowest row */
				if(rowid == (bdepth - 1))
				{
					/*On the leftest column */
					if(colid == 0)
					{
				        nextid = pnode->id - bwidth;
						pnode->direct = BACK;
					}
					else
					{
				        nextid = pnode->id - 1;
						pnode->direct = UP;
					}
				}
				/*On the leftest column */
				else if(colid == 0)
				{
					/*On the uppest row */
					if(rowid == 0)
					{
				        nextid = pnode->id + bwidth;
						pnode->direct = BACK;
					}
					else
					{
				        nextid = pnode->id + bwidth;
						pnode->direct = UP;
					}
				}
				else
				{
				    nextid = pnode->id + bwidth;
					pnode->direct = LEFT;
				}
				break;
			case LEFT:
			    /* On the leftest column */
				if(colid == 0)
				{
				    /*On the uppest row */
					if(rowid == 0)
					{
				        nextid = pnode->id;
						pnode->direct = BACK;
					}
					else
					{
				        nextid = pnode->id - bwidth;
						pnode->direct = BACK;
					}
				}
				/*On the uppest row */
				else if(rowid == 0)
				{
				    nextid = pnode->id - 1;
					pnode->direct = BACK;
				}
				else
				{
				    nextid = pnode->id - 1;
					pnode->direct = UP;
				}
				break;
			case UP:
			    /* On the uppest row */
				if(rowid == 0)
				{
				    nextid = pnode->id;
					pnode->direct = BACK;
				}
				else
				{
				    nextid = pnode->id - bwidth;
					pnode->direct = BACK;
				}
				break;
			default:
				nextid = pnode->id;
				return nextid;
				break;
		} /* switch(pnode->direct) */

		/* Check if overlap */
		if(checkque.at(nextid) == 0)
		{
			return nextid;
		}
	} /* while( pnode->direct != BACK ) */
	
	nextid = pnode->id;
	return nextid;
}

/* Push the node into tracking stack "track */
void pushNode(int id, unsigned int depth, string word)
{
	node_t *pnode;
	
	pnode = new node_t;
	pnode->id = id;
	pnode->depth = depth;
	
	if(depth < word.length())
	{
		pnode->target = word.at(pnode->depth);
	}
	pnode->direct = RIGHT;
    track.push_back(pnode);
	checkque.at(id)++ ;
}

bool exist(vector< vector<char> >& board, string word)
{
	int i;
	int rowid;
	int colid;
	char target;
	node_t * pnode;
	int nextid;

    bwidth = board.at(0).size();
    bdepth = board.size();
    bsize = bwidth * bdepth;
    checkque.resize(bsize);
    
	/* Check if the length ofword bigger than that ofboard */
    if((unsigned int)bsize < word.length())
	{
		return 0;
	}

	for(i = 0; i < bsize; i++)
	{
	    rowid = i / bwidth;
		colid = i % bwidth;
		target = word.at(0);
        
		/* Depth-first traversal search */
		if(board[rowid][colid] == target)
		{
		    /* Push the matched element into tracking stack */
            pushNode(i, 1, word);

			while((track.size() != word.length()) && (track.size() != 0))
			{
				pnode = track.back();

				/*Search if the neighbor match the target */
				nextid = nextId(pnode);
	            rowid = nextid / bwidth;
		        colid = nextid % bwidth;
                 
				/* There is still al least one un-visited node */
				if( pnode->id != nextid )
				{
				    /* Hit the target */
					if(board[rowid][colid] == pnode->target)
					{
                        pushNode(nextid, track.size()+1, word);
					}
				}
				/* No neighbor match the target on this "pnode"*/
				else
				{
				    /* Traverse back the tree*/
					checkque.at(pnode->id)--;
					delete pnode;
					track.pop_back();
				}
			} /* while((track.size() ... */

			if(track.size() == word.length())
			{
				return 1;
			}
		} /* if(board ... */
	} /* for(i = 0; ... */

	return 0;

}

void dumptrack(void)
{
	unsigned int i;

    cout << "answer: ";
	for(i = 0; i < track.size(); i++)
	{
		cout << track.at(i)->id << ",";
	}
	cout << endl;
}

private:
    /* Board width */
    int bwidth;
    /* Board depth */
	int bdepth;
    /* Board size */
	int bsize;
	/* Record all the element selected */
	vector<int> checkque;
	/* Record all the traversal in the form of stack */
	vector<node_t*> track;

};/*End of class Solution */

#define WIDTH 4
#define DEPTH 3
int main()
{
    int i;
    int j;
    bool isExist;
	Solution sol;
	
	vector< vector<char> > vboard;
	vector<char> *row;
    //string word = {"ABCCED"};
    string word = {"CCSEEDASFBA"};
	char board[DEPTH][WIDTH]={	
								{'A','B','C','E'},
								{'S','F','C','S'},
								{'A','D','E','E'}
							 };

	for(i = 0; i < DEPTH; i++)
    {
		row = new vector<char>;
		row->assign(&(board[i][0]), &(board[i][WIDTH]));
		vboard.push_back(*row);
    }
    
    for(i = 0; i < DEPTH; i++)
    {
		for(j = 0; j < WIDTH; j++)
		{
			cout<<vboard[i][j]<<",";
		}
		cout<<endl;
    }
    
	cout << "The width of board: "<< vboard.at(0).size() << endl;
	cout << "The depth of board: "<< vboard.size() << endl;
	cout << "word:" << word << endl;
	
	isExist = sol.exist(vboard, word);

	cout << "isExist = "<< isExist << endl;
	
	if(isExist == 1)
	{
    	sol.dumptrack();
	}

	return 0;
}
