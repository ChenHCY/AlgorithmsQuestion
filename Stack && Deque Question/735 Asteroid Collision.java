/* Leetcode 735. Asteroid Collision
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). 

Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. 

If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.


Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.

Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.

Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 

Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
*/
//
/*
   思路： 
        通过stack来进行加入，和比较
        
        然后判断是否加入和删除
*/

//Time: O(n)  Space: O(1)
//解释 Gitbook url：https://app.gitbook.com/s/rTQIJTAsVUJ1zJcmHRVG/735.-asteroid-collision-stack-and-and-deque

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        //LinkedList和ArrayDeque 都能作为stack的 声明
        //区别：LinkedList可以让null作为元素， ArrayDeque不可以允许元素为非空
        Deque<Integer> stack = new ArrayDeque<>();
        int index = 0;

        while(index < asteroids.length){
            if(stack.isEmpty() || stack.peek() < 0 || asteroids[index] > 0){
                stack.push(asteroids[index]);
            } else if(stack.peek() == -asteroids[index]){
                stack.pop();
            } else if(stack.peek() < -asteroids[index]){
                stack.pop();
                //continue语句用于循环语句中，作用是不执行循环体剩余部分，直接进行下次循环。
                continue; //Example: [-2,-2,1,-2]
            }
            index++;
        }

        int[] res = new int[stack.size()];
        for(int i = stack.size() - 1; i >= 0; i--){
            res[i] = stack.pop();
        }

        return res;
    }
}
