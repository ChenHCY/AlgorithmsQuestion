/*Leetcode 1854. Maximum Population Year

You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the ith person.

The population of some year x is the number of people alive during that year. The ith person is counted in year x's population 
if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.

Return the earliest year with the maximum population.

Example 1:

Input: logs = [[1993,1999],[2000,2010]]
Output: 1993
Explanation: The maximum population is 1, and 1993 is the earliest year with this population.

Example 2:

Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
Output: 1960
Explanation: 
The maximum population is 2, and it had happened in years 1960 and 1970.
The earlier year between them is 1960.
 

Constraints:

1 <= logs.length <= 100
1950 <= birthi < deathi <= 2050
*/

class Solution {
    public int maximumPopulation(int[][] logs) {
        int n = logs.length;
        //diff array
        int[] diff = new int[110]; //2050 - 1950 = 100, diff前后各自多开一个空间
        
        for(int[] people : logs){
          
            int birth = people[0];
            int death = people[1];
          
            diff[birth - 1950] += 1; //增加人口
            diff[death - 1950] -= 1; //减少人口
            //x is in the inclusive range [birthi, deathi - 1], 
            //the person is not counted in the year that they die.
        }

        int maxPopuYear = 0; //总的最大人口的年份
        int maxPopu = Integer.MIN_VALUE; //总的最大人口
        int yearPopu = 0; //每个年份存在的人口

        //复原差分diff数组 =》记录每一年的人口
        for(int i = 0; i < diff.length; i++){
            yearPopu += diff[i];
            if(yearPopu > maxPopu){
                maxPopu = yearPopu;
                maxPopuYear = i;
            }
        }

        return maxPopuYear + 1950;
    }
}
