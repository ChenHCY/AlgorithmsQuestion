# Dynamic Programming 动态规划

如果某一问题有很多重叠子问题，使用动态规划是最有效的。

**状态的定义 && 状态转移方程**

动态规划中每一个状态一定是由上一个状态推导出来的

**动态规划的五个步骤**
1. 确定dp数组（dp table）以及下标的含义

2. 确定递推公式

3. dp数组如何初始化

4. 确定遍历顺序

5. 举例推导dp数组

**动态规划的两个思路**
1. 枚举选哪个：适用于需要完全知道子序列相邻两数的信息 =》比如：找到数组的最长的递增子序列 O(n^2)

2. 选或不选：适用于不需要完全知道子序列相邻数字的信息：
   ==》1. 需要得到的子序列和相邻数字无关
   ==》2. 子序列相邻数字的弱关联 （比如：奇偶性）

**动态规划应该如何debug**
找问题的最好方式就是把dp数组打印出来，看看究竟是不是按照自己思路推导的！

做动规的题目，写代码之前一定要把状态转移在dp数组的上具体情况模拟一遍，来确定最后推出的是想要的结果。

检查步骤：

1. 这道题目我举例推导状态转移公式了么？

2. 我打印dp数组的日志了么？

3. 打印出来了dp数组和我想的一样么？
   
