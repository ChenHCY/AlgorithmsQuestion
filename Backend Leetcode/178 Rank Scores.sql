/* Leetcode  178. Rank Scores

Table: Scores
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| score       | decimal |
+-------------+---------+
id is the primary key (column with unique values) for this table.
Each row of this table contains the score of a game. Score is a floating point value with two decimal places.
 
Write a solution to find the rank of the scores. The ranking should be calculated according to the following rules:

· The scores should be ranked from the highest to the lowest.
· If there is a tie between two scores, both should have the same ranking.
· After a tie, the next ranking number should be the next consecutive integer value. In other words, there should be no holes between ranks.

Return the result table ordered by score in descending order.

The result format is in the following example.

Example 1:

Input: 
Scores table:
+----+-------+
| id | score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+
Output: 
+-------+------+
| score | rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+
*/

/* Write your MySQL query statement below
# dense_rank()： 是一个窗口函数，它计算基于指定列（这里是 "score"）的密集排名
# 该函数按照指定的列（在这里是 "score" 列）进行降序排列，并为每个分数分配一个密集排名，相同的分数将具有相同的排名。
# order by score des：按照 score 的降序排列
# as 'Rank'： 为计算出的密集排名结果取了一个别名，命名为 "Rank"。
*/
select score, dense_rank() over (order by score desc) as 'Rank' from Scores
