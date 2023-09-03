/*Leetcode 486. Predict the Winner

You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.

Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0. 

At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) 
which reduces the size of the array by 1. The player adds the chosen number to their score. 
The game ends when there are no more elements in the array.

Return true if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner, 
and you should also return true. You may assume that both players are playing optimally.

Example 1:

Input: nums = [1,5,2]
Output: false
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return false.

Example 2:

Input: nums = [1,5,233,7]
Output: true
Explanation: Player 1 first chooses 1. Then player 2 has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 
Constraints:
1 <= nums.length <= 20
0 <= nums[i] <= 10^7
*/

class Solution {
    //recursion 递归 + Two pointer
    //[start, end]表示nums[]中 剩余还存在的数字 区域
    public boolean predictTheWinner(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        //从玩家1开始拿，turn来表示是玩家1还是玩家2
        //玩家1拿是记为正分，玩家2拿记为负分
        int resPoints = totalPoints(nums, start, end, 1);

        if(resPoints >= 0){ //最后检查可能存在的最大分数值，也就是玩家1能拿大的最大粉丝情况是否为正
            return true; //如果为正数，玩家1获胜
        }

        return false; //为负数，玩家2获胜
    }

    //递归function => turn来表示是玩家1还是玩家2
    public int totalPoints(int[] nums, int left, int right, int turn){
        //exit condition => 只剩最后一个数值，直接拿掉
        if(left == right){
            return nums[left] * turn;
        }
        int maxScore = 0;//最大值为当前玩家最多可以得到的分数。

        //两种选择，可以从两端拿分
        int scoreLeft = nums[left] * turn + totalPoints(nums, left + 1, right, -turn); //从左边拿
        int scoreRight = nums[right] * turn + totalPoints(nums, left, right - 1, -turn); //从左边拿
         
        //当数组中剩下的数字多于 1 个时，当前玩家会选择最优的方案
        maxScore = Math.max(scoreLeft * turn, scoreRight * turn) * turn; //取可能存在的最大分值

        return maxScore;
    }
}
