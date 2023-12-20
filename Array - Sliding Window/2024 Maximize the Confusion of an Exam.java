/*Leetcode 2024. Maximize the Confusion of an Exam

A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false. He wants to confuse the students by maximizing the number 
of consecutive questions with the same answer (multiple trues or multiple falses in a row).

You are given a string answerKey, where answerKey[i] is the original answer to the ith question. In addition, you are given an integer k, the 
maximum number of times you may perform the following operation:

    · Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').

Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.

Example 1:
Input: answerKey = "TTFF", k = 2
Output: 4
Explanation: We can replace both the 'F's with 'T's to make answerKey = "TTTT".
There are four consecutive 'T's.

Example 2:
Input: answerKey = "TFFT", k = 1
Output: 3
Explanation: We can replace the first 'T' with an 'F' to make answerKey = "FFFT".
Alternatively, we can replace the second 'T' with an 'F' to make answerKey = "TFFF".
In both cases, there are three consecutive 'F's.

Example 3:
Input: answerKey = "TTFTTFTT", k = 1
Output: 5
Explanation: We can replace the first 'F' to make answerKey = "TTTTTFTT"
Alternatively, we can replace the second 'F' to make answerKey = "TTFTTTTT". 
In both cases, there are five consecutive 'T's.
 

Constraints:
n == answerKey.length
1 <= n <= 5 * 10^4
answerKey[i] is either 'T' or 'F'
1 <= k <= n
*/

//Time: O(n)  n 为字符串answerkey的长度，只需要遍历两次
//Space: O(1)
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        //比较以T为基础，或者 以F为基础 ==> 哪一种情况能得到更长的相同字母长度
        return Math.max(countMaxconsecutive(answerKey, k, 'T'), countMaxconsecutive(answerKey, k, 'F'));
    }

    // str：answerKey /  k: 能改变的数量 /  ch: 'T' or 'F'
    // 保证窗口内最多只能有k个ch
    public static int countMaxconsecutive(String str, int k, char ch){
        int n = str.length();
        int res = 0;
        int left = 0;
        int countDiff = 0;

        for(int right = 0; right < n; right++){
            if(str.charAt(right) == ch){
                countDiff += 1;
            }

            while(countDiff > k){
                if(str.charAt(left) == ch){
                    countDiff -= 1;
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;  
    }
}
