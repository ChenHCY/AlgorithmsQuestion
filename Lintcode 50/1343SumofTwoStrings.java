/* 1343 · Sum of Two Strings
Description
Given you two strings which are only contain digit character. You need to return a string spliced by the sum of the bits.

Example1:
Input:
A = "99"
B = "111"
Output: "11010"
Explanation: because 9 + 1 = 10, 9 + 1 = 10, 0 + 1 = 1,connect them，so answer is "11010"

Example2:
Input:
A = "2"
B = "321"
Output: "323"
Explanation: because 2 + 1 = 3, 2 + 0 = 2, 3 + 0 = 3, connect them，so answer is "323"
*/
public class Solution {
    /**
     * @param A: a string
     * @param B: a string
     * @return: return the sum of two strings
     */
    public String SumofTwoStrings(String A, String B) {
        // write your code here
        StringBuffer result = new StringBuffer();

        //first deal with the A B length difference part
        if(A.length() > B.length()){
            result.append(A.substring(0, A.length() - B.length()));
            A = A.substring(A.length() - B.length());
        } else {
            result.append(B.substring(0, B.length() - A.length()));
            B = B.substring(B.length() - A.length());
        }

      //second deal with the A B length same part
        for(int i = 0; i < A.length(); i++){
            char NumberA = A.charAt(i);
            char NumberB = (i < B.length()) ? B.charAt(i) : '0'; // judge whether is still in B.length
            result.append(getSum(NumberA, NumberB)); // use small functuin add them together
        }
        return result.toString();
    }

    public String getSum(char A, char B){
        int sum = (A - '0') + (B - '0');
        return String.valueOf(sum);
    }
}
