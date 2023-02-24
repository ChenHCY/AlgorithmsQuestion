/* Leetcode 989. Add to Array-Form of Integer

The array-form of an integer num is an array representing its digits in left to right order.

For example, for num = 1321, the array form is [1,3,2,1].
Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.

Example 1:

Input: num = [1,2,0,0], k = 34
Output: [1,2,3,4]
Explanation: 1200 + 34 = 1234

Example 2:

Input: num = [2,7,4], k = 181
Output: [4,5,5]
Explanation: 274 + 181 = 455


Example 3:

Input: num = [2,1,5], k = 806
Output: [1,0,2,1]
Explanation: 215 + 806 = 1021
 

Constraints:

1 <= num.length <= 104
0 <= num[i] <= 9
num does not contain any leading zeros except for the zero itself.
1 <= k <= 104
*/

//My Soulution:

/* 思路： 类似与415题

用一个指针在num中移动，然后通过 k%10 得到k中每个小数的值， 和 k /= 10 来移动k中每个位置

重点：要考虑到是否存在需要进位的问题 ==》 需要一个integer值 carry来计算和检查每个位置是不是大于10，需不需要进位

*/

class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        int sum = 0; //the total sum of every digiht position
        int carry = 0; // When greater than 10, carry +1
        List<Integer> res = new ArrayList<>();
        int p1 = num.length - 1; // start postion in num

        //while-loop to travser all the elemnt from num
        while(p1 >= 0 || k > 0 || carry > 0){
            int v1 = 0; // current num element value
            int currSum = 0; // sum of num.val + k.val

            if(p1 >= 0){
                v1 = num[p1];//get num value as integer
            }

            currSum = (k % 10) + v1 + carry; // sum of num[i].val + k[i].val
            sum = currSum % 10;  // Each bit cannot be greater than 10
            res.add(0, sum); // Add and save it into res list
            carry = currSum / 10; //Check whether the numbers need to carry one bit up
            p1--; //move to next element
            k /= 10;  //move to next element
        }

        return res;
    }
}

//The best time solution:

/* 思路

因为k 是个integer值，所以可以好好利用 来帮助进位和计算 ==》 从而节省时间

*/
class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> res = new ArrayList<>();

        //for-loop to travser all the number element from num
        for(int i = num.length - 1; i >= 0 || k > 0; i--){
            if(i >= 0){
                res.add((num[i] + k) % 10);
                k = (num[i] + k) / 10;
            } else{
                res.add(k % 10);
                k = k / 10;
            }
        }

        Collections.reverse(res);
        return res;
    }
}
