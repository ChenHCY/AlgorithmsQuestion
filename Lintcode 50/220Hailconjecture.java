/* 220 · Hail conjecture
Description
Mathematicians have put forward a famous conjecture —— hail conjecture.
For any natural number n, if n is even, replace n with n / 2;
If n is odd, replace n with 3 * n + 1.
According to this rule, the final result must be 1.
How many times will the number change to 1?

1<=n<=1000

Example
Example 1:

Input: 
4
Output: 
2
Explanation: 
First round: 4 / 2 = 2
Second round: 2 / 2 = 1
The answer is 2
*/

public class Solution {
    public int getAnswer(int num) {
        // write your code here.
        int result = 0;
        while(num != 0) {
            if((num % 2) == 0){
                num = num / 2;
                result++;
                 if(num == 1){
                    break;
                }
            } else{
                if(num == 1){
                    break;
                }
                num = (3 * num) + 1;
                result++;
            }
        }
        return result;
    }
}
