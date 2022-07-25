/* Leetcode 415. Add Strings

Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

You must solve the problem without using any built-in library for handling large integers (such as BigInteger). 
You must also not convert the inputs to integers directly.

Example 1:
Input: num1 = "11", num2 = "123"
Output: "134"

Example 2:
Input: num1 = "456", num2 = "77"
Output: "533"

Example 3:
Input: num1 = "0", num2 = "0"
Output: "0"
 

Constraints:

1 <= num1.length, num2.length <= 104
num1 and num2 consist of only digits.
num1 and num2 don't have any leading zeros except for the zero itself.
*/

class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int len1 = num1.length() - 1; // start postion in num1
        int len2 = num2.length() - 1; // start postion in num2
        int number = 0; // When greater than ten, carry +1
        int result = 0; 
        
        // Traverse all element from back to front
        while(len1 >= 0 || len2 >= 0 || number > 0){
            //int dight = 0;
            int cur = 0; // sum of num1.val + num2.val
            int v1 = 0; // current num1 element value
            int v2 = 0; // current num2 element value
            
            if(len1 >= 0){
                v1 = num1.charAt(len1)-'0'; //get num1 value as integer
            }
            
            if(len2 >= 0){
                v2 = num2.charAt(len2)-'0';//get num2 value as integer
            }
            
            cur = v1 + v2 + number; // sum of num1.val + num2.val
            result = (cur % 10); // Each bit cannot be greater than 10
            res.append(result); // Add and save it into StringBuilder
            number = cur / 10;//Check whether the numbers need to carry one bit up
            len1--; //move to next element
            len2--; // move to next element
        }
        
        //Because it is traversed and added from back to front, it needs to be reversed and then output
        return res.reverse().toString();
        
    }
}
