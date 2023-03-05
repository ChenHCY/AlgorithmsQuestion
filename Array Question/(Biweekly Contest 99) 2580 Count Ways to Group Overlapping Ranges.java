/* Leetcode (Biweekly Contest 99) 2580 Count Ways to Group Overlapping Ranges

You are given a 2D integer array ranges where ranges[i] = [starti, endi] denotes that all integers between starti and endi (both inclusive) 

are contained in the ith range.

You are to split ranges into two (possibly empty) groups such that:

Each range belongs to exactly one group.
Any two overlapping ranges must belong to the same group.
Two ranges are said to be overlapping if there exists at least one integer that is present in both ranges.

For example, [1, 3] and [2, 5] are overlapping because 2 and 3 occur in both ranges.
Return the total number of ways to split ranges into two groups. Since the answer may be very large, return it modulo 10^9 + 7.


Example 1:

Input: ranges = [[6,10],[5,15]]
Output: 2
Explanation: 
The two ranges are overlapping, so they must be in the same group.
Thus, there are two possible ways:
- Put both the ranges together in group 1.
- Put both the ranges together in group 2.

Example 2:

Input: ranges = [[1,3],[10,20],[2,5],[4,8]]
Output: 4
Explanation: 
Ranges [1,3], and [2,5] are overlapping. So, they must be in the same group.
Again, ranges [2,5] and [4,8] are also overlapping. So, they must also be in the same group. 
Thus, there are four possible ways to group them:
- All the ranges in group 1.
- All the ranges in group 2.
- Ranges [1,3], [2,5], and [4,8] in group 1 and [10,20] in group 2.
- Ranges [1,3], [2,5], and [4,8] in group 2 and [10,20] in group 1.
 

Constraints:

1 <= ranges.length <= 105
ranges[i].length == 2
0 <= starti <= endi <= 109
*/

class Solution {
    public int countWays(int[][] ranges) {
        int MOD = (int)1e9 + 7; // modulo 109 + 7.

        //First sort the ranges in increasing order based on the first element
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        //一个储存int[]数组的Arraylist
        List<int[]> list = new ArrayList<>();

        //声明range的两个端点 ==》用来合并重叠的区间
        int left = ranges[0][0];
        int right = ranges[0][1];

        //for-loop travser ==》用来合并区间
        for(int[] curr : ranges){
            if(curr[0] > right){ //当前区间不和前面的区间有重叠
                list.add(new int[]{left, right}); //把之前的区间加入到list
                left = curr[0]; //移动两个端点指针到当前区间的位置
                right = curr[1]; //移动两个端点指针到当前区间的位置
            } else{ //如果有重叠部分
                right = Math.max(right, curr[1]); //合并当前区间和之前的区间
            }
        }

        list.add(new int[]{left, right}); //add the last range into list
        int res = 1; //数学公式的推到
        //最后能形成的组合数量 = 2 ^（重叠数组 + 剩余数组） % MOD (10^9 + 7)
        // Since the answer may be very large, return it modulo 10^9 + 7.
        for(int i = 0; i < list.size(); i++){
            res = (res * 2) % MOD;
        }

        return res;
    }
}
