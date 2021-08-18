/* 491 · Palindrome Number
Description
Check a positive number is a palindrome or not.
A palindrome number is that if you reverse the whole number you will get exactly the same number.

Example 1:
Input:11
Output:true

Example 2:
Input:1232
Output:false
Explanation:
1232!=2321
*/

public class Solution {
    public boolean isPalindrome(int num) {
        // write your code here
        StringBuilder list = new StringBuilder();
        list.append(num);
        int left = 0;
        int right = list.length() - 1;

        while(left < right){
            if(list.charAt(left) != list.charAt(right)){
                return false;
            } else{
                left++;
                right--;
            }
        }
        return true;
    }
}
