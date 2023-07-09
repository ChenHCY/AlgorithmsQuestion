/* Leetcode 826. Most Profit Assigning Work

You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:

  · difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
  · worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).

Every worker can be assigned at most one job, but one job can be completed multiple times.

  · For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker cannot complete any job, their profit is $0.
  
Return the maximum profit we can achieve after assigning the workers to the jobs.

Example 1:

Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
Output: 100
Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.

Example 2:

Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
Output: 0
 

Constraints:

n == difficulty.length
n == profit.length
m == worker.length
1 <= n, m <= 10^4
1 <= difficulty[i], profit[i], worker[i] <= 10^5
*/

class Solution {
    //建立一个work class, 来储存工作难度diffculty 和 收益profit之间的关系
    class Work{
        int diffcult;
        int profit;
        public Work(int diffcult, int profit){
            this.diffcult = diffcult;
            this.profit = profit;
        }
    }
    
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int res = 0;
        int maxProfit = 0;
        int n = difficulty.length;
        
        Work[] jobs = new Work[n]; //储存每个工作的难度 和 收益
        for(int i = 0; i < n; i++){
            jobs[i] = new Work(difficulty[i], profit[i]); //储存每个工作的难度 和 收益
        } 

        Arrays.sort(jobs, (a, b) -> a.diffcult - b.diffcult); //把jobs[] 按照工作的难度进行升序排列
        Arrays.sort(worker); //worker[]储存的每个工人能完成的难度，按照升序排列。

        //two pointer
        int i1 = 0; //i1 在jobs[]上滑动，里面储存的每个工作 的难度 和 收益
        int i2 = 0; //i2 在woker[]上滑动，里面储存的每个工人能完成的难度标准
        while(i1 < jobs.length && i2 < worker.length){
            if(jobs[i1].diffcult <= worker[i2]){
                maxProfit = Math.max(maxProfit, jobs[i1].profit); //记录可以得到的最大收益Profit
                i1++;
            } else{
                res += maxProfit;
                i2++;
            }
        }

        //如果还有剩余的worker工人 没有工作，所有都按照最大收益的工作统计
        res += (worker.length - i2) * maxProfit;

        return res; //输出最后统计的最大收益总和
    }
}
