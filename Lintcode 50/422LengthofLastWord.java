/* Description
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Example 1:
Input: "Hello World"
Output: 5

Example 2:
Input: "Hello LintCode"
Output: 8

*/

public class Solution {
    public int lengthOfLastWord(String s) {
        // write your code here
        //find the lize size, need return a integer

        List result = new ArrayList<>();
        char[] a = s.toCharArray();

        for(int i = a.length - 1; i >= 0; i--){
            if(a[i] == ' '){
                continue; //if find ' ', just contiue to next chactpter.
            } else {
                result.add(a[i]);
                if(i > 0 && a[i-1] == ' '){
                    break;
                }
            }
        }
        return result.size(); //find the lize size, need return a integer
    }
}
