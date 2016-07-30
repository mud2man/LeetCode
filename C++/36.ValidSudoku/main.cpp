#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:

    void char2num(vector<vector<char> >& board, vector<vector<int> >& numboard)
    {
        int i;
	int j;
	int num;
	char str[2];
        
	str[1] = '\0';

	for(i =0; i < 9; i++)
	{
	    for(j =0; j < 9; j++)
	    {
	        str[0] = board[i][j] ;
	        num = atoi((const char*)str); 

	        if((num < 10) && (num > 0))
		{
	            numboard[i][j] = num;
		}
		else
		{
	            numboard[i][j] = 0;
		}
	    }
	}
    }

    bool isValidNineSeq(vector<int> NineSeq)
    {
        int i;
	int j;
        int count[10] = {0};
        
	for(i = 0; i < 9; i++)
	{
	    j = NineSeq.at(i);
	    count[j]++;
	    
	    if((count[j] > 1)&& (j > 0))
	    {
	        cout<<"count["<<j<<"] = "<<count[j]<<endl;
	        return 0;
            }
	}
/*	
	for(i = 1; i < 10; i++)
	{
	    if(count[i] > 1)
	    {
	        return 0;
            }
	}
*/
        return 1;     
    }

    bool isValidSudoku(vector<vector<char> >& board) 
    {
        int i;
	int j;
	int k;
	int l;
	bool ret;
	int index;

	vector<vector<int> > numboard;
	vector<int> col;
	vector<int> sqr;
         
	/* Set up the size */
	numboard.resize(9);
	for(i = 0; i < 9; i++)
	{
	    numboard[i].resize(9);
	}
        sqr.resize(9);
        col.resize(9);

	this->char2num(board, numboard);
        
	/* Check if each row meet the rule */
        for(i = 0; i < 9; i++)
	{
            ret = this->isValidNineSeq(numboard[i]);
	    
	    if(ret == 0)
	    {
	        return 0;
	    }
	}

	/* Check if each colum meet the rule */
	for(i = 0; i < 9; i++)
	{
	    for(j = 0; j < 9; j++)
	    {
                col.at(j) = numboard[j][i];
	    }
            
	    ret = this->isValidNineSeq(col);
	    
	    if(ret == 0)
	    {
	        return 0;
	    }
	} 
	
	/* Check if each square meet the rule */
	for(i = 0; i < 9; i+=3)
	{
	    for(j = 0; j < 9; j+=3)
            {
		index = 0 ;

	        for(k = 0; k < 3; k++)
		{
		   for(l = 0; l < 3; l++ )
		   {
		       sqr.at(index) = numboard[i+k][j+l];
		       index++ ;
		   }
		}
               
		ret = this->isValidNineSeq(sqr);
		        
		if(ret == 0)
		{
		    return 0;
		}
            }
	}

	return 1;
    }
};

int main()
{
    int i;
    int j;
    bool isValid;
    Solution Sol;

    vector<vector<char> > vboard;
    vector<char> *row;
    char board[9][9]={{'.','.','.','.','.','.','.','.','.'},
                      {'.','.','.','3','.','.','5','.','.'},
                      {'.','.','.','.','.','.','.','.','.'},
                      {'.','.','.','8','.','.','.','.','3'},
                      {'.','.','.','.','1','1','6','.','.'},
                      {'.','.','.','.','.','.','.','.','.'},
                      {'.','.','.','.','.','.','1','.','.'},
                      {'.','.','.','.','.','.','.','.','7'},
                      {'.','.','.','.','.','.','.','4','.'}};
     
    for(i = 0; i < 9; i++)
    {
        row = new vector<char>;
	row->assign(&(board[i][0]), &(board[i][9]));
	vboard.push_back(*row);
    }
    
    for(i = 0; i < 9; i++)
    {
        for(j = 0; j < 9; j++)
        {
            cout<<vboard[i][j]<<",";
        }
	cout<<endl;
    }
    
    isValid = Sol.isValidSudoku(vboard); 
    
    if(isValid == 1)
    {
        cout << "It is valid" << endl;
    }
    else
    {
        cout << "It is not valid" << endl;
    }

    return 0;
}
