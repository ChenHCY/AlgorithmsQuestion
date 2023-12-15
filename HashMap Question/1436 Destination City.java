/*Leetcode 1436. Destination City

You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi. 
Return the destination city, that is, the city without any path outgoing to another city.

It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.

Example 1:
Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
Output: "Sao Paulo" 
Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".

Example 2:
Input: paths = [["B","C"],["D","B"],["C","A"]]
Output: "A"
Explanation: All possible trips are: 
"D" -> "B" -> "C" -> "A". 
"B" -> "C" -> "A". 
"C" -> "A". 
"A". 
Clearly the destination city is "A".

Example 3:
Input: paths = [["A","Z"]]
Output: "Z"
 

Constraints:
1 <= paths.length <= 100
paths[i].length == 2
1 <= cityAi.length, cityBi.length <= 10
cityAi != cityBi
All strings consist of lowercase and uppercase English letters and the space character.
*/
//Time: O(n)  Space: O(n)
class Solution {
    public String destCity(List<List<String>> paths) {
        String res = "";
        HashMap<String, String> map = new HashMap<>();

        //把起始点和终点对应的全部存入hashmap中
        for(List<String> list : paths){
            String start = list.get(0);
            String end = list.get(1);
            //存入hashmap中
            map.put(start, end);
        }

        //遍历检查终点所有城市有哪一个城市不在起点list中
        for(List<String> list : paths){
            String end = list.get(1);
            if(!map.containsKey(end)){
                res = end;
                break;
            }
        }

        return res;
    }
}
