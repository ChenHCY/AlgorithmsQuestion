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

/*Use StringBuilder method*/

class Solution {
    public String reverseWords(String s) {
        String[] str = s.split(" ");
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < str.length; i++){
            str[i] = new StringBuilder(str[i]).reverse().toString();
        }
        for (String a : str){
            res.append(a + " ");
        }
        return res.toString().trim();
    }
}
/* 1. Java中的split函数. split()是一个用来切分字符串的函数
   2. Java中的trim() 方法 是去除字符串两端的空白
   3. Java的StringBuilder，是一个可变对象，可以预分配缓冲区，这样，往StringBuilder中新增字符时，不会创建新的临时对象*/
