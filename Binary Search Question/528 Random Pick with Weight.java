/* Leetcode 528. Random Pick with Weight

You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.

You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and 
returns it. The probability of picking an index i is w[i] / sum(w).

重要条件：
***For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%). ****
 
Example 1:
Input
["Solution","pickIndex"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.

Example 2:

Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.

Since this is a randomization problem, multiple answers are allowed.
All of the following outputs can be considered correct:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.
 

Constraints:

1 <= w.length <= 10^4
1 <= w[i] <= 10^5
pickIndex will be called at most 10^4 times.
*/

// 此题的给定一个 w[] array, 里面的index对应w[index]的权重
// 权重的计算： w[i] / sum(w).
// 然后随机生成一个数，按照给定的权重 输出 index
class Solution {
    // w[index] 表示 每个index能被选择的权重
    // ==> 所以我们可以看成在 sum(w)个数字 进行抽取任意一个index，然后 每个index 分别有 w[i]个
    // 这样可以实现 每个index有对应的权重，然后我们是基于这个权重去随机生成的数字的

    int[] sum; //储存每个index的范围右标
    public Solution(int[] w) {
        // 然后我们使用前缀和来找到每个index可能存在的最大位置
        // For example: [1, 3, 2]  ==> 我们有 1个0 3个1 2个2 ==>  0 1 1 1 2 2 
        // sum = [1, 4, 6] ==> 0的范围为[0, 1]  1的范围[1，4], 2的范围[5, 6]
        // ==> 然后random一个数字，二分法找到距离哪个右标更近
        sum = new int[w.length];
        sum[0] = w[0];

        for(int i = 1; i < w.length; i++){
            sum[i] = sum[i - 1] + w[i];                    
        }
    }
    
    public int pickIndex() {
        // Math.random()是随机生成一个数，范围[0, sum(w)]
        double target = Math.random() * sum[sum.length - 1];

        //二分法 找到target 距离哪个右标更近
        int left = 0;
        int right = sum.length - 1;

        while(left + 1 < right){
            int mid = (right - left) / 2 + left;

            if(target == sum[mid]){
                return mid;
            } else if (target < sum[mid]){
                right = mid;
            } else{
                left = mid;
            }
        }

        //检查 距离哪个右标更近
        if(sum[left] >= target){
            return left;
        } else {
            return right;
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
