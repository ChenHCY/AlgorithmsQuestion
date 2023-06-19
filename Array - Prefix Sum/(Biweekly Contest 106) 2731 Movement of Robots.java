/* Leetcode (Biweekly Contest 106) 2731. Movement of Robots

Some robots are standing on an infinite number line with their initial coordinates given by a 0-indexed integer array nums and will start moving once given the command to move. 
The robots will move a unit distance each second.

You are given a string s denoting the direction in which robots will move on command. 'L' means the robot will move towards the left side or negative side of the number line, 
whereas 'R' means the robot will move towards the right side or positive side of the number line.

If two robots collide, they will start moving in opposite directions.

Return the sum of distances between all the pairs of robots d seconds after the command. Since the sum can be very large, return it modulo 109 + 7.

Note:

For two robots at the index i and j, pair (i,j) and pair (j,i) are considered the same pair.
When robots collide, they instantly change their directions without wasting any time.
Collision happens when two robots share the same place in a moment.
For example, if a robot is positioned in 0 going to the right and another is positioned in 2 going to the left, the next second they'll be both in 1 and they will change direction and the next second the first one will be in 0, heading left, and another will be in 2, heading right.
For example, if a robot is positioned in 0 going to the right and another is positioned in 1 going to the left, the next second the first one will be in 0, heading left, and another will be in 1, heading right.
 
Example 1:

Input: nums = [-2,0,2], s = "RLL", d = 3
Output: 8
Explanation: 
After 1 second, the positions are [-1,-1,1]. Now, the robot at index 0 will move left, and the robot at index 1 will move right.
After 2 seconds, the positions are [-2,0,0]. Now, the robot at index 1 will move left, and the robot at index 2 will move right.
After 3 seconds, the positions are [-3,-1,1].
The distance between the robot at index 0 and 1 is abs(-3 - (-1)) = 2.
The distance between the robot at index 0 and 2 is abs(-3 - 1) = 4.
The distance between the robot at index 1 and 2 is abs(-1 - 1) = 2.
The sum of the pairs of all distances = 2 + 4 + 2 = 8.

Example 2:

Input: nums = [1,0], s = "RL", d = 2
Output: 5
Explanation: 
After 1 second, the positions are [2,-1].
After 2 seconds, the positions are [3,-2].
The distance between the two robots is abs(-2 - 3) = 5.
 

Constraints:

2 <= nums.length <= 10^5
-2 * 109 <= nums[i] <= 2 * 10^9
0 <= d <= 10^9
nums.length == s.length 
s consists of 'L' and 'R' only
nums[i] will be unique.
*/

//Time: O(n + m); n is the nums[] length,  m is the string s length
//Space: O(1)
class Solution {
    //所有机器人在一条无线延伸的长轴上面，nums[]表示机器人的坐标位置
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;

        //根据String s(方向) 来得到每个机器人d秒之后的最终位置
        //s中每一个字母对应每一个机器人的方向
        for(int i = 0; i < s.length(); i++){
            char dir = s.charAt(i);
            if(dir == 'R'){
                nums[i] += d;
            }else if(dir == 'L'){
                nums[i] -= d;
            }
        }

        return countPair(nums, n); //计算有序数组 nums[]中 所有数字的两两位置之后。
    }

    //计算一个有序数组 nums[]中 所有数的两两位置之后。
    //公式：计算每个数组的前缀和，然后根据公式：index * nums[idex] - prefixSum(前缀和)
    private int countPair(int[] nums, int n){
        Arrays.sort(nums);
        long sum = 0;
        int mod = (int) 1e9 + 7; //取模
        long prefix = 0;
        //根据公式进行计算所有数的两两位置之后。
        for(int index = 0; index < n; index++){
            sum = (sum + index * (long) nums[index] - prefix) % mod; //计算整个数组里面每个数的两两相差之和
            prefix += nums[index] % mod; //计算每个index位置的前缀和
        }
        return (int) sum;
    }
}
