/* Leetcode 1209. Remove All Adjacent Duplicates in String II
You are given a string s and an integer k, a k duplicate removal consists of choosing k 
adjacent and equal letters from s and removing them, causing the left and the right side 
of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made. 
It is guaranteed that the answer is unique.

Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"
 
Constraints:
1 <= s.length <= 105
2 <= k <= 104
s only contains lowercase English letters.
*/

//TIME: O(n)  ==> where n is a string length. We used for-loop process each character in the string once.
//Space: O(n) ==> store the count for each character.

class Solution {
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s); //StringBuilder used to do the remove and change string
        int count[] = new int[sb.length()]; //used to count and store the numbers of Adjacent Duplicates
        
        //travser all the element from String s
        for(int i = 0; i < sb.length(); i++){
            //if not is duplicates
            if(i == 0 || sb.charAt(i) != sb.charAt(i - 1)){
                count[i] = 1; 
            }else{ //meet the duplicates character
                count[i] = count[i-1] + 1;
                if(count[i] == k){ //when the numbers duplicates equals k
                    //stringbuilder delete function: Characters are removed from start to end-1 index.
                    sb.delete(i - k + 1, i + 1); 
                    i = i - k; //change the i-pointer back to previous character of the deleted range
                }
            }
        }
        
        return sb.toString(); //change stringbuilder to be string format
    }
}
