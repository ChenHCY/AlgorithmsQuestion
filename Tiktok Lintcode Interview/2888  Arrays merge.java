/*Lintcode 2888 · Arrays merge

Given two arrays arr1 and arr2, you are asked to merge the two arrays into an ordered array and remove the duplicate numbers from the array.

Write your code in the mergeArray() method of the Solution class.

The reviewer will compile the entire project's code into an executable Main program and execute your code in this way Main.

Sample 1

When arr1 = [6, 2, 1] and arr2 = [9, 4, 5], the return array results in

[1, 2, 4, 5, 6, 9]

Sample 2

When arr1 = [2, 1]` and arr2 = [4, 1]`, return the array result as

[1, 2, 4]

*/

import java.util.*;

public class Solution {

    public int[] mergeArray(int arr1[], int arr2[]) {
        TreeSet<Integer> set = new TreeSet<>();
        //把所有数都加入到treeSet中
        //TreeSet可以自动排序和去重
        for(int num : arr1){
            set.add(num);
        }
        for(int num : arr2){
            set.add(num);
        }

        //TreeSet储存的是integer形式的值，所以只能转化为Integer[]
        Integer[] arr = new Integer[set.size()];
        set.toArray(arr);
        //需要把Integer[]的数值 存入最后的int[] array
        int[] res = new int[set.size()];
        for(int i = 0; i < arr.length; i++){
            res[i] = arr[i];
        }

        return res;
    }
}
