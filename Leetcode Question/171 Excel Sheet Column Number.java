/* Leetcode 171. Excel Sheet Column Number
Given a string columnTitle that represents the column title as appears in an Excel sheet, return its corresponding column number.

For example:

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...
 

Example 1:
Input: columnTitle = "A"
Output: 1

Example 2:
Input: columnTitle = "AB"
Output: 28

Example 3:
Input: columnTitle = "ZY"
Output: 701
 
 
Constraints:
1 <= columnTitle.length <= 7
columnTitle consists only of uppercase English letters.
columnTitle is in the range ["A", "FXSHRXW"].
*/

class Solution {
    public int titleToNumber(String columnTitle) {
        if(columnTitle==null){
            return 0;
        }
        int res = 0;
        int temp = 0;
        
        for(int i = 0; i < columnTitle.length(); i++){
            temp =  columnTitle.charAt(i) - 'A' + 1; // calculate every character as number
            res *= 26;  // use for more than one column, the first character in the column should times 26
            res += temp; // get the total number of column title
        }
         
        return res;
    }
}
