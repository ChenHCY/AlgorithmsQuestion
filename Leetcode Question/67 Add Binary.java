/*67. Add Binary
Given two binary strings a and b, return their sum as a binary string.

Example 1:
Input: a = "11", b = "1"
Output: "100"

Example 2:
Input: a = "1010", b = "1011"
Output: "10101"
*/

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1; // define the length of a
        int n = b.length() - 1; // define the length of a 
        int carry = 0;
        
        while(i >= 0 || n >= 0){
            int sum = carry;
            if(i >= 0){
                sum += a.charAt(i) - '0'; // get the integer kinds number
            }
            if(n >= 0){
                sum += b.charAt(n) - '0'; //get the integer kinds number
            }
            
            sb.append(sum % 2); // add the number into stringBuilder
            carry = sum / 2; // addition carry
            
            i--;
            n--;
        }
        
        if(carry != 0){
            sb.append(carry);
        }
        
        return sb.reverse().toString(); 
        // because the StringBuilder appends the characters of the String argument in order. so should reverse the result here.
    }
}
