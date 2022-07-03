/* Leetcode 168. Excel Sheet Column Title
Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
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
Input: columnNumber = 1
Output: "A"

Example 2:
Input: columnNumber = 28
Output: "AB"

Example 3:
Input: columnNumber = 701
Output: "ZY"
*/

class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder(); // use the StringBuilder to save the dates
        
        while(columnNumber != 0){
            columnNumber--; // Because the alphabet start at 1 = 'A'
            result.append((char)('A' + columnNumber % 26)); // 1-26 = A-Z
            columnNumber /= 26; // 26 numbers mean 26 alphabet
        }
        
         return result.reverse().toString(); 
        // StringBuilder is "First in and first move right", so need reverse it

    }
}
