/* Amazon OA 1 Question 2
There is a new product launched and its customer ratings are being recorded in an array. 
The ratings are being monitored and analyzed if there is any decrease in the ratings.
Find the number of periods in which the rating is consecutively decreasing.

Example - Ratings = [4,3,5,4,3]
Periods (in other words sub arrays in which ratings are decreasing):
One day periods = [4],[3],[5],[4],[3] (count of subarrays is 5)
Two day periods = [4,3],[5,4],[4,3] (count of subarrays is 3)
3 day periods = [5,4,3] (count of subarrays is 1)
So, the output for this example will be 9 (5 + 3 + 1)
*/

public class MyClass {
    public static void main(String args[]) {
      int[] array = {4, 3, 5, 4, 3};
      int res = countGroups(array);
      System.out.println(res);
    }
    
    public static int countGroups(int[] array){
      //Used two pointer to check decreasing area
        int groups = 0;
        int start = 0; //start-pointer
        int end = 1; // end-pointer
        
        while(end < array.length){
          //if find the consecutively decreasing area, count the number of groups
            if(array[end-1] - array[end] == 1){
                groups += end - start;
            }else{ //if not is consecutively decreasing, start a new group 
                start = end; //move start-pointer into end-pointer ==> start to check next area
            }
            
            end += 1; //move end-pointer into next element
        }
        
        return groups += array.length; //all the single elements should be think consecutively decreasing
        
    }
}
