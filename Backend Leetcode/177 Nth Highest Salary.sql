/* Leetcode  177. Nth Highest Salary

Table: Employee

+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
id is the primary key (column with unique values) for this table.
Each row of this table contains information about the salary of an employee.
 
Write a solution to find the nth highest salary from the Employee table. If there is no nth highest salary, return null.

The result format is in the following example.

Example 1:

Input: 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
n = 2

Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
Output: 
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+

+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+

Example 2:

Input: 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
n = 2
Output: 
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| null                   |
+------------------------+
*/

 /*    
      # 从 Employee 表中选择独特的（不重复的）薪水，按照薪水降序排列，然后从第 N+1 行开始，选择一行。
      # DISTINCT: 选择不重复的 ==> 'SELECT DISTINCT(salary) FROM your_table' 在一张表中选择不重复的薪水值
      # DESC：降序排列 ==> 因为要找到第nth高的薪水
      # LIMIT N, 1 ==> 从第n+1一行开始选择一行，也就是选择的是 第 n+1 高的薪水值
*/
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
SET N = N-1; # 因为 后面 SELECT 的选择是从 N+1行开始，所以要首先减去1
  RETURN (
      SELECT DISTINCT(Salary) from Employee order by salary DESC 
      LIMIT N, 1
  );
END
