/* 557. Reverse Words in a String III

Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:

Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

Example 2:

Input: s = "God Ding"
Output: "doG gniD"
*/

class Solution {
    public String reverseWords(String s) {
        char[] res = s.toCharArray();
        for(int i = 0; i < res.length; i++){
            if(res[i] != ' '){
                int j = i;
                while(j + 1 < res.length && res[j+1] != ' '){
                    j++; // find the last character of every word
                }
                reverse(res, i, j);
                i = j;
            }
        }
        return new String(res);
    }
    
    public void reverse(char[] res, int i, int j){
        char temp = ' ';
        while(i < j){
            temp = res[i];
            res[i] = res[j];
            res[j] = temp;
            i++;
            j--;
        }
    }
}
