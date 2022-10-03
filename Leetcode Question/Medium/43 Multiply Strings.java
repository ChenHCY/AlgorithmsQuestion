/* Leetcode 43. Multiply Strings

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

Example 1:
Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:
Input: num1 = "123", num2 = "456"
Output: "56088"
 
Constraints:
1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
*/
//Time: O(n * m)
//Space: O(n + m)

class Solution {
    public String multiply(String num1, String num2) {
        //remove zero and null
        if(num1 == null || num2 == null){
            return "0";
        }
        
        int[] digits = new int[num1.length() + num2.length()];
        
        //index:  0  1  2
        /*num1:   1  2  3*  i-pointer = 2
          num2:      4  5*  j-pointer = 1
          ******************
                     1* 5*  ==> i = 2, j = 1 ==> [i+j, i+j+1]
                   1 0
                 0 5
                   1 2
                 0 8
              0  4
          *******************
       index: 0  1 2 3  4  ==> res = num1.length + num2.length
        */
        
        //So get the result function: p1 = i + j && p2 = i + j +1
        
        //travser all the element from nums and nums2
        for(int i = num1.length() - 1; i >=0; i--){ //i-pointer
            for(int j = num2.length() - 1; j >= 0; j--){ //j-pointer
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); //change string to number integer
                int p1 = i + j, p2 = i + j + 1;
                int sum = product + digits[p2];
                digits[p1] += sum / 10; //get the value of p1 i + j
                digits[p2] = sum % 10; //get the value of p2 i + j + 1
            }
        }
        
        //Put the digits[] number into a Stringbuilder res
        StringBuilder res = new StringBuilder();
        for(int num : digits){ //example: [0 ,0, 1, 0, 3] ==> need remove the first two zero, and keep third zero
            if( !(num == 0 && res.length() == 0)){
                res.append(num);
            }
        }
        
        return res.length() == 0 ? "0" : res.toString(); //if res length is not zero, output the res with string type
    }
}
