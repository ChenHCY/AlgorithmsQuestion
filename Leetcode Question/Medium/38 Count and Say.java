/* Leetcode 38. Count and Say

The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

   1. countAndSay(1) = "1"
   2. countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), 
      which is then converted into a different digit string.

To determine how you "say" a digit string, split it into the minimal number of substrings 
such that each substring contains exactly one unique digit. Then for each substring, say 
the number of digits, then say the digit. Finally, concatenate every said digit.

For example, the saying and conversion for digit string "3322251":
Given a positive integer n, return the nth term of the count-and-say sequence.

Example 1:
Input: n = 1
Output: "1"
Explanation: This is the base case.

Example 2:
Input: n = 4
Output: "1211"
Explanation:
countAndSay(1) = "1"
countAndSay(2) = say "1" = one 1 = "11"
countAndSay(3) = say "11" = two 1's = "21"
countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 
Constraints:
1 <= n <= 30
*/

class Solution {
    public String countAndSay(int n) {
        // Did not have some easy way, just brute force solution
        String res = "1"; //use stringbuilder to add in every time
        int i = 1; //use i-pointer to move
        while(i < n){
            int count = 0;
            char c = res.charAt(0);
            StringBuilder str = new StringBuilder();
            
            //use for-loop to add in every step
            for(int j = 0; j <= res.length(); j++){
                if(j != res.length() && res.charAt(j) == c){
                    count++;
                } else{
                    str.append(count);
                    str.append(c);
                    if(j != res.length()){
                        count = 1;
                        c = res.charAt(j);
                    }
                }
            }
            
            res = str.toString();
            i++;
        }
        return res;
    }
}
