/* Leetcode 365. Water and Jug Problem

You are given two jugs with capacities jug1Capacity and jug2Capacity liters. There is an infinite amount of water supply available. 

Determine whether it is possible to measure exactly targetCapacity liters using these two jugs.

If targetCapacity liters of water are measurable, you must have targetCapacity liters of water contained within one or both buckets by the end.

Operations allowed:

  · Fill any of the jugs with water.
  · Empty any of the jugs.
  · Pour water from one jug into another till the other jug is completely full, or the first jug itself is empty.

Example 1:

Input: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
Output: true
Explanation: The famous Die Hard example 

Example 2:

Input: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
Output: false

Example 3:

Input: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
Output: true
 

Constraints:

1 <= jug1Capacity, jug2Capacity, targetCapacity <= 10^6
*/
//时间复杂度：O(log⁡(min⁡(x,y))) 取决于计算最大公约数所使用的辗转相除法。
//空间复杂度： O（1）
class Solution {
    // 我们可以认为每次操作只会给水的总量带来 jug1Capacity 或者 jug2Capacity 的变化量。
    // 因此我们的目标可以改写成：找到一对整数 a,b， ==》 使得 ax + by = z 
    // ==> 贝祖定理: 对于任何整数 a,b,m 关于未知数x和y的线性方程 ax + by = z，只存在整数解 m 是 a,b最大公约数
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if(jug1Capacity + jug2Capacity < targetCapacity){
            return false;
        }

        if(jug1Capacity == targetCapacity || jug2Capacity == targetCapacity || jug1Capacity + jug2Capacity == targetCapacity){
            return true;
        }

        return targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0;
    }

    private int gcd(int x, int y){
        int temp = x % y;
        while(temp != 0){
            x = y;
            y = temp;
            temp = x % y;
        }
        return y;
    }
}
