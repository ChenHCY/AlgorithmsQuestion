/*Leetcode (Contest 335) 2582 Pass the Pillow

There are n people standing in a line labeled from 1 to n. The first person in the line is holding a pillow initially. 

Every second, the person holding the pillow passes it to the next person standing in the line. 

Once the pillow reaches the end of the line, the direction changes, and people continue passing the pillow in the opposite direction.

For example, once the pillow reaches the nth person they pass it to the n - 1th person, then to the n - 2th person and so on.
Given the two positive integers n and time, return the index of the person holding the pillow after time seconds.

 
Example 1:

Input: n = 4, time = 5
Output: 2
Explanation: People pass the pillow in the following way: 1 -> 2 -> 3 -> 4 -> 3 -> 2.
Afer five seconds, the pillow is given to the 2nd person.


Example 2:

Input: n = 3, time = 2
Output: 3
Explanation: People pass the pillow in the following way: 1 -> 2 -> 3.
Afer two seconds, the pillow is given to the 3rd person.
 

Constraints:

2 <= n <= 1000
1 <= time <= 1000
*/

/*
思路： 

/ 运算可以得到这是在去的路上，还是返回的路上
% 运算可以得到还剩几个数字 


*/

class Solution {
    public int passThePillow(int n, int time) {
        int res = 0;
        
        if(time % (n - 1) != 0){
            int temp = time / (n - 1);
            if(temp % 2 != 0){
                res = n - (time % (n - 1));
            } else{
                res = 1 + (time % (n - 1));
            }
        } else{
            int temp2 = time / (n - 1);
            if(temp2 % 2 != 0){
                res = n;
            } else{
                res = 1;
            }
        }
        
        return res;
    }
}
