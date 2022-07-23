/*Leetcode 405. Convert a Number to Hexadecimal
Given an integer num, return a string representing its hexadecimal representation. For negative integers, two’s complement method is used.
All the letters in the answer string should be lowercase characters, and there should not be any leading zeros in the answer except for the zero itself.
Note: You are not allowed to use any built-in library method to directly solve this problem.


Example 1:
Input: num = 26
Output: "1a"

Example 2:
Input: num = -1
Output: "ffffffff"
 

Constraints:
-231 <= num <= 231 - 1
*/

//My Wrong Solution: can not deal with when the nums is negative
class Solution {
    public String toHex(int num) {
        if(num == 0){
            return "0";
        }
        
        StringBuilder res = new StringBuilder();
        char hex[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        int temp = 0;
        
        while(num > 0){
            temp = num % 16;
            res.append(hex[temp]);
            num /= 4;
        }
        
        return res.reverse().toString();
    }
}

// Correct Solution: Then think about bit operations. ">>>" acts means an unsigned right shift
class Solution {
    public String toHex(int num) {
        if(num == 0){
            return "0";
        }
        
        StringBuilder res = new StringBuilder();
        char hex[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'}; //Hex number map
        
        while(num != 0){
            //the purpose of this is to make all numbers can be uniformly calculated in hexadecimal without using array overflow
            res.insert(0, hex[(16 + num % 16) % 16]); // use insert() can always keep making sure add element position. here is insert(0,..) means always add at the beginning of StringBuilder
            num = num >>> 4; // equals "nums / 16", but here need care about nums is negative
        }
        
        return res.toString(); // change StringBuilder to string style
    }
}
