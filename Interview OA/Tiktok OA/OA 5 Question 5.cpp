/* Tiktok OA 5 Question 5

Given an integer sequence (a) of length N, you are to cut the sequence into several parts such 
that every one of which is a consecutive subsequence of the original sequence. Every part must satisfy 
this-the sum of the integers in the part is not greater than a given integer M. You are to find a cut 
that minimizes the sum of the maximum integer of each part.

Input

Given N(0 <N’s 100 000), M, and N integers describes the integer sequence. 
Every Integer in the sequence is between 0 and 1 000 000 inclusively.

Output

Output one integer which is the minimum sum of the maximum integer of each part. If no such cutting exist output-1.

sample TC1:
N = 8
M = 17
sequence = [2,2,2,8,1,8,2,1]
expected answer: 12

sample TC2:
N = 28
M = 20
sequence = [1,21,4,9,20,6,11,27,12,21,22,16,13,9,1,8,13,10,9,4,11,7,28,9,13,26,17,18]
don’t know expected answer

*/
//the small function
int helper(vector<vector<int>>& vec, vector<int>& vec2, int num){
    //basic case
    if(num < 0){
        return 0;
    }
    
    if(vec2[num] != INT_MAX){
        return vec2[num];
    }
    
    int minValue = INT_MAX;
    
    for(int i = num; i >= 0; --i) {
        if (vec[i][num] == -1){
            break;
        }
        
        int tempNumber = helper(vec, vec2, i - 1);
        minValue = min(minValue, tempNumber + vec[i][num]); 
    }
    
    vec2[num] = minValue;
    
    return vec2[num]; 
}

int cutSequence(int n, int m, vector<int> sequence) {
    int currSum = 0;
    vector<vector<int>> vect(n, vector<int>(n, -1));
    vector<int> vect2(n, INT_MAX);
    
    //for loop to travser all the element from sequence and cut it
    for(int i = 0; i < n; ++i){
        if(sequence[i] > m){
            return -1;
        }
        
        currSum = sequence[i];
        vect[i][i] = sequence[i];
        
        for(int j = i + 1; j < n; j++){
            currSum += sequence[j];
            if(currSum > m){ // the sum of the integers in the part is not greater than M
                break;
            }
            vect[i][j] = max(vect[i][j-1], sequence[j]);
        }
    }
    
    vect2[0] = sequence[0];
    return helper(vect, vect2, n - 1);
}
