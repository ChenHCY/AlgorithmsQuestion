/* Leetcode  (Biweekly Contest 100)  2591 Distribute Money to Maximum Children

You are given an integer money denoting the amount of money (in dollars) that you have and another integer children denoting the 
number of children that you must distribute the money to.

You have to distribute the money according to the following rules:

All money must be distributed.
Everyone must receive at least 1 dollar.
Nobody receives 4 dollars.
Return the maximum number of children who may receive exactly 8 dollars if you distribute the money according to the aforementioned rules. If there is no way to distribute the money, return -1.

Example 1:

Input: money = 20, children = 3
Output: 1
Explanation: 
The maximum number of children with 8 dollars will be 1. One of the ways to distribute the money is:
- 8 dollars to the first child.
- 9 dollars to the second child. 
- 3 dollars to the third child.
It can be proven that no distribution exists such that number of children getting 8 dollars is greater than 1.

Example 2:

Input: money = 16, children = 2
Output: 2
Explanation: Each child can be given 8 dollars.
 

Constraints:

1 <= money <= 200
2 <= children <= 304
*/

class Solution {
    public int distMoney(int money, int children) {
        // 违反条件： Everyone must receive at least 1 dollar.
        if(children > money){
            return -1;
        } 

        //如果money小于8，表示不会有任何有一个人能收到8 dollars
        if (money < 8){
            return 0;
        } 

        //Binary Search
        int left = 0;
        int right = children + 1;

        while(left < right - 1){
            int mid = left + (right - left) / 2;
            if(checkRules(money, children, mid)){
                left = mid;
                System.out.println("left: " + left);
            } else{
                right = mid;
                //System.out.println("right: " + right);
            }
        }

        return left;
    }

    public static boolean checkRules(int money, int children, int people){
        int left_Money = money - (people * 8); //当前人数分8dollors 后剩余的钱
        int left_Children = children - people; //除开这些人后 剩余的人数

        //check rules
        //1. Everyone must receive at least 1 dollar.
        if(left_Money < left_Children){
            return false;
        }

        //2. All money must be distributed.
        if(left_Money != 0 && (left_Children == 0)){
            return false;
        }

        //3. Nobody receives 4 dollars.
        if(left_Money == 4 && (left_Children == 1)){
            return false;
        }

        return true;
    }
}
