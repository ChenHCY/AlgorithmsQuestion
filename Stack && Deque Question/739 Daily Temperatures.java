/* Leetcode 739. Daily Temperatures

Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you 

have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]

Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]

Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]
 

Constraints:

1 <= temperatures.length <= 10^5
30 <= temperatures[i] <= 100
*/
//Time: O(n)  Space: O(n）
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];

        //使用单调栈来储存每个温度的index天，然后计算这个差值
        //得到每个温度多少天之后能获得更高的温度
        Deque<Integer> stack = new ArrayDeque<>();

        //因为可能存在最高温度的值，所以如果找不到的更高的温度，放入0
        Arrays.fill(res, 0);

        for(int i = 0; i < n; i++){
            //使用while, 存在间隔很多天才获得更高温度的情况。所以进行更新时，要更新当前stack里面的所有index值
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int previous = stack.peek();
                res[stack.pop()] = i - previous;
            }
            stack.push(i);
        }

        return res;
    }
}
