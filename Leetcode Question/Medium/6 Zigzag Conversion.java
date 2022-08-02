/* 6. Zigzag Conversion
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);
 

Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

Example 3:
Input: s = "A", numRows = 1
Output: "A"
 

Constraints:
1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000
*/

/*
[0]P                       [6]I                            [12]N
[1]A            [5]L       [7]s                [11]I       [13]G
[2]Y    [4]A               [8]H     [10]R
[3]P                       [9]I

s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"

We can see every space are 6  => step = 2 * numRows - 2; (It used for the first row and last row)

for the middle rows, we will run another loop which starts from the current row and jumps step size after each iteration 
==> j + step - 2 * i (i value is rows value, j is element postion value)
*/

class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1){ //if String is only have 1 character, just return itself
            return s;
        }
        
        if(s.isEmpty() || s == null || numRows <= 0){ //if String S is null or empty, or Numer Rows is 0, return wrong
            return "";
        }
        
        //StringBuilder() is use for add modify a string without creating a new object 
        StringBuilder res = new StringBuilder(); 
        int len = s.length(); 
        int step = 2 * numRows - 2; //
        
        for(int i = 0; i < numRows; i++){ // i is number Rows, every time start at first element of every rows
            for(int j = i; j < len; j += step){ // j is the pointer for every elment of every rows
                res.append(s.charAt(j));// add character element into StringBuilder (Rules of the first row and last row)
                if(i != 0 && i != numRows - 1 && (j + step - 2 * i) < s.length()){
                    //for the middle rows, we will run another loop which starts from the current row and jumps step size after each iteration 
                  //==> j + step - 2 * i (i value is rows value, j is element postion value)
                    res.append(s.charAt(j + step - 2 * i)); // add character element into StringBuilder(Rules of middler rows)
                }
            }
        }
        
        return res.toString();
        
    }
}
