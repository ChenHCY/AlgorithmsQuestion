/* Citadel OA Question 1
We have an array of n integers, point, and an integer k. We can perform either of the following operations 
once for each point[i] in point

Increment point[i] by k
Decrement point[i] by k.

We want to perform an operation on each point[i] such that the difference between the maximum and minimum values 
in the array after performing all operations is minimal.

Input: point = [0,1,2,3] and k=2,
Output: 3
*/

import java.util.*;

class Question1 {
    public static void main(String[] args) {
        List<Integer> point = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        int k = 2;
        System.out.println(question1(point, k));
    }
    
    public static int question1(List<Integer> point, int k){
        int[] arr = new int[point.size()];
        int size = point.size();
        for(int i = 0; i < size; i++){
            arr[i] = point.get(i); 
        }
        Arrays.sort(arr);
        int difference = 0;
        int result = arr[arr.length - 1] - arr[0]; 
        for(int i = 0; i < arr.length - 1; i++){
            int firstNumber = arr[0] + k;
            int currentNumber = arr[i] + k;
            int nextNumber = arr[i+1] - k;
            int lastNumber = arr[arr.length - 1] - k;
            
            difference = Math.max(lastNumber, currentNumber) - Math.min(nextNumber, firstNumber);
            result = Math.min(result, difference);
        }
        return result;
    }
}
