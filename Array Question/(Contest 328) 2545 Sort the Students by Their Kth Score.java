/* (Contest 328)  Leetcode 2545. Sort the Students by Their Kth Score

There is a class with m students and n exams. You are given a 0-indexed m x n integer matrix score, 
where each row represents one student and score[i][j] denotes the score the ith student got in the jth exam. 

The matrix score contains distinct integers only.

You are also given an integer k. Sort the students (i.e., the rows of the matrix) by their scores in the kth (0-indexed) exam from the highest to the lowest.

Return the matrix after sorting it.

Example 1:
Input: score = [[10,6,9,1],[7,5,11,2],[4,8,3,15]], k = 2
Output: [[7,5,11,2],[10,6,9,1],[4,8,3,15]]
Explanation: In the above diagram, S denotes the student, while E denotes the exam.
- The student with index 1 scored 11 in exam 2, which is the highest score, so they got first place.
- The student with index 0 scored 9 in exam 2, which is the second highest score, so they got second place.
- The student with index 2 scored 3 in exam 2, which is the lowest score, so they got third place.


Example 2:
Input: score = [[3,4],[5,6]], k = 0
Output: [[5,6],[3,4]]
Explanation: In the above diagram, S denotes the student, while E denotes the exam.
- The student with index 1 scored 5 in exam 0, which is the highest score, so they got first place.
- The student with index 0 scored 3 in exam 0, which is the lowest score, so they got second place.

Constraints:
m == score.length
n == score[i].length
1 <= m, n <= 250
1 <= score[i][j] <= 105
score consists of distinct integers.
0 <= k < n
*/

/*
思路：

使用 PriorityQueue对于int[][] score 进行排序，规则就是每一个int[]中的第k个数的值

PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[k] - a[k]); ==》b[k] - a[k] 表示按分数的降序排列 即 分高的排在前面 符合题意

所以，第一个for-loop: 把int[][] score所有的int[] array 加入到PriorityQueue 进行排序

然后再用一个while-loop 和 pq.pooll() 把排序之后的每个int[]元素提取到int[][] res ==》 得到最后的正确答案，按照第K次的成绩 给学生进行降序排序

*/

class Solution {
    public int[][] sortTheStudents(int[][] score, int k) {
        int[][] res = new int[score.length][score[0].length];

        //used PriorityQueue to sort the kth value as the decreasing order
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[k] - a[k]);

        //add all the element from score and use used PriorityQueue to sort
        for(int[] i : score){
            pq.add(i);
        }

        int index = 0;

        //get the the correct students grades int[][] after sort as kth value
        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            res[index] = temp;
            index++;
        }
        
        return res;
    }
}
