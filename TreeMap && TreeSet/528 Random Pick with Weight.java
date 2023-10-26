/* 528. Random Pick with Weight

You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.

You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).

***重要条件：*** *** 
*** For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).*** 
 
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
    // ==> 所以我们可以看成在 sum(w) 个数字 进行抽取任意一个index，然后 每个index 分别有 w[i]个
    // 这样可以实现 每个index有对应的权重，然后我们是基于这个权重去随机生成的数字的

    int sum; //sum 表示 sum(w[])的总和
    TreeMap<Double, Integer> map;

    public Solution(int[] w) {
        // 然后我们使用TreeMap 储存 每个index 存在的最大的位置右标
        map = new TreeMap<>();
        
        // sum就是对应的每个index在sum(w)里面的划分右标
        for(int i = 0; i < w.length; i++){
            sum += w[i];
            map.put((double)sum, i); //TreeMap ceilingKey() 用于查找大于等于给定键的最小键。
            //所以sum的代表的index右标只能储存在key中
        }
    }
    
    public int pickIndex() {
        // Math.random() 生成的一定是[0, 1] double类型小数
        // *sum, 可以形成随机生成[0, sum(w)] 区域的double类型小数
        double targetIndex = Math.random() * sum;

        // TreeMap ceilingKey() 用于查找大于等于 给定键 的 最小key值。
        // ==> 这样我们就能 直接 找到最靠近 随机生成数字 的 key值，也就是每个index对应的右标
        // 然后使用map.get(key) 输出这个右标对应的 w[]里面的 index值
        return map.get(map.ceilingKey(targetIndex));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
