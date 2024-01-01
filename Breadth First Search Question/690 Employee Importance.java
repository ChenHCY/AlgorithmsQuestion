/* 690. Employee Importance

You have a data structure of employee information, including the employee's unique ID, importance value, and direct subordinates' IDs.

You are given an array of employees employees where:

  · employees[i].id is the ID of the ith employee.
  · employees[i].importance is the importance value of the ith employee.
  · employees[i].subordinates is a list of the IDs of the direct subordinates of the ith employee.
Given an integer id that represents an employee's ID, return the total importance value of this employee and all their direct and indirect subordinates.

Example 1:
Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
Output: 11
Explanation: Employee 1 has an importance value of 5 and has two direct subordinates: employee 2 and employee 3.
They both have an importance value of 3.
Thus, the total importance value of employee 1 is 5 + 3 + 3 = 11.

Example 2:
Input: employees = [[1,2,[5]],[5,-3,[]]], id = 5
Output: -3
Explanation: Employee 5 has an importance value of -3 and has no direct subordinates.
Thus, the total importance value of employee 5 is -3.
 

Constraints:
1 <= employees.length <= 2000
1 <= employees[i].id <= 2000
All employees[i].id are unique.
-100 <= employees[i].importance <= 100
One employee has at most one direct leader and may have several subordinates.
The IDs in employees[i].subordinates are valid IDs.
*/

/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/
//Time: O(n)  Space: O(n)
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        HashMap<Integer, Employee> map = new HashMap<>();
        for(Employee employee : employees){
            map.put(employee.id, employee); //把每个employee的信息和对应的id存入hashmap
        }

        int res = 0; //总的重要程度
        Queue<Integer> queue = new LinkedList<Integer>(); 
        queue.offer(id); //起始的点：需要计算的重要性的id

        //main traverser
        while(!queue.isEmpty()){
            int currId = queue.poll(); //提取当前id
            Employee currEmployee = map.get(currId); //提取当前id的Employee信息
            res += currEmployee.importance; //统计重要性的结果值

            //如果还有下属，把下属加入到queue继续计算
            List<Integer> subordinates = currEmployee.subordinates;
            for(int i : subordinates){
                queue.offer(i);
            }
        }

        return res;
    }
}
