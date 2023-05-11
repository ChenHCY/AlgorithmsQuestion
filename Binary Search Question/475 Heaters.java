/* Leetcode 475. Heaters

Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.

Every house can be warmed, as long as the house is within the heater's warm radius range. 

Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.

Notice that all the heaters follow your radius standard, and the warm radius will the same.

Example 1:

Input: houses = [1,2,3], heaters = [2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.

Example 2:

Input: houses = [1,2,3,4], heaters = [1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.

Example 3:

Input: houses = [1,5], heaters = [2]
Output: 3
 
Constraints:

1 <= houses.length, heaters.length <= 3 * 10^4
1 <= houses[i], heaters[i] <= 10^9
*/

/* 思路： 此题是找到heater[] 能覆盖全部houses[]，的最短路径

所以使用二分法进行查找，左边界是可能需要的最短半径，右边界是可能需要的最长半径

然后每次取mid值，检查当前的mid值 也就是radius是否能让heater[]覆盖到所有的houses

==》可以，移动右指针，减少radius ==> 因为我们要找到最小的符合要求的radius

==》不可以，移动左指针，增大radius

然后继续二分取mid值，直到跳出循环，得到的就是符合要求的radius
*/
//Time: O(nlogn + mlogm) n is houses.length, m is heaters.length
//Space: O(log n + log m)  排序所需要消耗的空间
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        //sort the houses array and heaters array in increasing oreder
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int left = 0; // 左边界是可能需要的最短半径， heater的位置和houses的位置重叠
      //右边界是可能需要的最长半径 ==》因为我们已经对于houses[]和heater[]进行了升序排序
      //所以可能需要的最长半径radius 就是最后一个houses和第一个heater的距离，或者最后一个heater和第一个houses的距离
        int right = Math.max(houses[houses.length - 1] - heaters[0], heaters[heaters.length - 1] - houses[0]);
    
        while(left < right){
          //然后每次二分取mid值
            int mid = left + (right - left) / 2;
            //检查当前的mid值 也就是radius是否能让heater[]覆盖到所有的houses
            if(check(houses, heaters, mid)){
                right = mid; // 可以，移动右指针，减少radius ==> 因为我们要找到最小的符合要求的radius
            } else{
                left = mid + 1; // 不可以，移动左指针，增大radius
            }
        }
        
        return left;
    }

    //检查当前的radius是否能让heater[]覆盖到所有的houses
    public boolean check(int[] houses, int[] heaters, int radius){
        int j = 0;
        for(int i = 0; i < houses.length; i++){
            //当第i个houses 在 第j个heater + raius的范围外，表示第j个heater覆盖不到第i个houses
            //移动heater到下一个 第 j + 1 个 继续检查，直到能找到可以覆盖到的第i个houses的heater
            while(j < heaters.length && heaters[j] + radius < houses[i]){
                j++;
            }
            //当第i个houses 在第j个heater的右区间， 或者左区间，代表heater可以覆盖到。
            //Continue跳过剩余代码, 继续检查下一个 
            //这里的条件必须是and &&，因为存在houses全部都位于heater的左边。
            //For example: houses =  [1,5]  heaters = [10] ==> 能覆盖全部housese的半径为 9
            if(j < heaters.length && houses[i] <= heaters[j] + radius && heaters[j] - radius <= houses[i]){
                continue;
            }
            return false; //如果有覆盖不到的，return false, 这个半径无法覆盖全部的houses
        }
        return true; //如果都可以覆盖到，return true
    }
}
