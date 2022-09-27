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
        
        //While-loop
        while(i < n){ //i can not large than n
            int count = 0;
            char c = res.charAt(0);
            StringBuilder str = new StringBuilder();
            
            /* n = 1 res = "1"
            
            n = 2 i = 1: res = "1"  c = '1' count = 0 
            j = 0 and res.length = 1 ==> count = 0 + 1 = 1 
            j = 1 and res.length = 1 ==> count = 1 ==> str = "count c" = "11" n = 2
            
            **********************************************************************************************
            n = 3 i = 2： res = "11"  c = '1' count = 0 
            j = 0 and res.length = 2 ==> count = 0 + 1 = 1
            j = 1 and res.length = 2 ==> count = 1 + 1 = 2
            j = 2 and res.length = 2 ==> count = 2 ==> str = "count c" ==> "21" n = 3
            
            **********************************************************************************************
            n = 4 i = 3： res = "21"  c = '2' count = 0
            j = 0 and res.length = 2 ==> count = 0 + 1 = 1
            j = 1 and res.length = 2 ==> count = 1 (res.charAt(j) != c) so count will not add
            ==> str = "count c" ==> "12"
            ==>  if(j != res.length()) ==> count = 1, c = '1'
            j = 2 and res.length = 2 ==>  count = 1, c = '1' ==> str = str + "count c" ==> "1211"  n = 4
            
            *************************************************************************************************
            n = 5 i = 4： res = "1211"  c = '1' count = 0
            
            j = 0 and res.length = 4 ==> count = 0 + 1 = 1 (res.charAt(j) = 1 == c = 1)
            
            j = 1 and res.length = 4 ==> count = 1 (res.charAt(j) = 2 != c = 1) so count will not add
            ==> str = "11" ==> if(j != res.length()) ==> count = 1 c = res.charAt(j) = 2
            
            j = 2 and res.length = 4 ==> count = 1 (res.charAt(j) = 1 != c = 2) so count will not add
            ==> str = str + "count c" = "1112" ==> if(j != res.length()) ==> count = 1 c = res.charAt(j) = 1
            
            j = 3 and res.length = 4 ==> count = 1 + 1 = 2 (res.charAt(j) = 1 == c = 1)
            
            j = 4 and res.length = 4 ==> count = 2 (j == res.length()) so count will not add  c = 1
            ==> str = str + "coun c" = "111221" (Final Answer) n = 5
            */
            
            //use for-loop to add in every step
            for(int j = 0; j <= res.length(); j++){
                //judge whether j is last one element
                if(j != res.length() && res.charAt(j) == c){
                    count++; //count add one 
                } else{
                    str.append(count); // add the count value into StringBuilder str
                    str.append(c); // add the c value into StringBuilder str
                    if(j != res.length()){ //if j is not arrived last one element
                        count = 1; //change count back to 1
                        c = res.charAt(j); //change c back to j-position value
                    }
                }
            }
            
            res = str.toString(); //finally use toString() output the value
            i++;
        }
        return res;
    }
}
