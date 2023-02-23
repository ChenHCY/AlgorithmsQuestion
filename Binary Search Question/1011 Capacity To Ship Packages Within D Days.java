/* Leetcode 1011. Capacity To Ship Packages Within D Days

A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). 
We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

Example 2:

Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

Example 3:

Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1
 

Constraints:

1 <= days <= weights.length <= 5 * 104
1 <= weights[i] <= 500
*/

/*
时间复杂度： O(nlog1e8)。

空间复杂度：O(1)O(1)

*/
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        //二分搜索，来确定船只的最小满足要求的运量
        int left = 0;
        int right = 0;

        for(int i = 0; i < weights.length; i++){
            left = Math.max(weights[i], left); //因为包裹无法拆分，所以最小的船只容量一定是最大的包裹重量
            right += weights[i]; //最大的船只容量一定是所有包裹重量的总和
        }

        //如果要求的运输天数和包裹的数量相同，则船只的容量为最大的包裹总量
        if(weights.length == days){
            return left;
        }

        //Binary Search Method
        while(left < right){
            //得到一个mid值 ==》 也就是船只的运量
            int mid = left + (right - left) / 2;
            //如果当前的船只运量可以满足天数需求，则缩小右边界
            if(shipPack(weights, days, mid)){
                right = mid; //因为我们无法确定mid是不是最小的满足要求的，所以不能移动到mid-1
            } else{
                left = mid + 1;//当mid不满足要求时，
            }
        }
        //返回当前l就是最小的满足条件的值，即最低运载能力
        return left;
    }

    //function to count whether can Ship all Packages Within D Days
    //用来检查当前船舶的容量能否满足运输天数的要求
    public static boolean shipPack(int[] weights, int day, int shipWeight){
        int countDays = 1; // 用来统计公司全部运走需要的天数
        int singleWeight = 0; //每一天可以运走的包裹总量
        //遍历weights[]每一个包裹
        for(int i = 0; i < weights.length; i++){
            singleWeight += weights[i];
            if(singleWeight > shipWeight){
                countDays++;
                //如果超重，当前的包裹是无法放入这天的运输，只能放入下一天的
                singleWeight = weights[i];  //所以再次更新singleWeight（每一天可以运走的包裹总量）
            }
        }

        //如果运输的天数超过了要求的天数，返回false ==> 代表这个船舶的容量无法满足要求
        if(countDays > day){
            return false;
        }

        return true; // 返回True ==> 代表这个船舶的容量是满足运输天数的要求
    }
}
