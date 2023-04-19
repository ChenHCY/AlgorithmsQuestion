/* Leetcode 378. Kth Smallest Element in a Sorted Matrix

Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n^2).

Example 1:

Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

Example 2:

Input: matrix = [[-5]], k = 1
Output: -5
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 10^9
All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
1 <= k <= n2
*/

//Solution 1: Force Method, 暴击解法，把matrix中所有的number值存入一个array中
//然后使用 Arrays.sort() 进行排序，输出第K个值 就是 matrix在第Kth小的的值
//时间复杂度：O(n^2 * logn)，对 n^2 个数排序。
//空间复杂度：O(n^2) 一维数组需要存储这 n^2 个数。
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
       int n = matrix.length;
       int[] list = new int[n * n];
       int index = 0;
       for(int i = 0; i < n; i++){
           for(int j = 0; j < n; j++){
               list[index] = matrix[i][j];
               index++;
           }
       }

       Arrays.sort(list);
       return list[k - 1];//array is start at 0, and need find the kth smallest element
    }
}


//Solution 2: 归并排序 Used PriorityQueue 优先队列 来对于 matrix中的值进行排序
//每行都拿出来一个，然后依次进行比较 ==> 每次K值减1 /当k==0的时候，表示这个数字就是matrix中第kth小的数字
// 时间复杂度：O(klogn)，归并 k 次，每次堆中插入和弹出的操作时间复杂度均为 logn。
// 空间复杂度：O(n)，堆的大小始终为 n。
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        //Used PriorityQueue to sort the matrix number element in increasing order
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        //因为每一行都是升序排列，所以首先把每一行的第一个数字(最小的数字)加入到优先队列中
        //matrix[i][0](pq[0]) 这个数字的值，i(pq[1]) 这个数字的row值, 0(pq[2]) 这个数字的column值
        for(int i = 0; i < n; i++){
            pq.add(new int[]{matrix[i][0], i, 0});
        }

        while(k != 0){
            int[] currNumber = pq.poll(); //首先拿到当前能拿到的最小值
            k--; //每次poll()移除一个 k-- 

            int currValue = currNumber[0]; // matrix[i][0](pq[0]) 这个数字的值
            if(k == 0){ //当k==0的时候，表示这个数字就是matrix中第kth小的数字
                return currValue;
            }

            //如果当前数字的行row 还有数字，则继续把当前行的下一个数字 加入到优先队列中
            if(currNumber[2] < n - 1){
                int nextNumberX = currNumber[1]; //row是相同的
                int nextNumberY = currNumber[2] + 1; //列需要 + 1
                pq.add(new int[]{matrix[nextNumberX][nextNumberY], nextNumberX, nextNumberY});
            }
        }
        return -1;
    }
}
