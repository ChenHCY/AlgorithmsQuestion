/* 1141 · The month's days
Description
Given a year and month, return the days of that month.

Example 1:
Input: 
2020 
2
Output: 
29

Example 2:
Input: 
2020 
3
Output: 
31
*/

public class Solution {
    /**
     * @param year: a number year
     * @param month: a number month
     * @return: Given the year and the month, return the number of days of the month.
     */
    public int getTheMonthDays(int year, int month) {
        // write your code here
         //the days for every month
        int days[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        
        int result = 0; //Define the reslut to output
        //juge which month
        for(int i = 0; i < month; i++){
            result = days[i];
        }

         if((year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)){
             if(month == 2){
                result += 1;
             } 
         } else {
            return result;
         }
        return result;
    }
}
