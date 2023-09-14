/* Samsara OA Question 2

String[] str = {"Jack: 4", "Bob: 6", "Jack: 3", "Kevein: 2"}; ==> 学生成绩

输出: Bob => 平均成绩最好的名字

*/

import java.util.*;
 
class Question2 {
    public static String solution(String[] str) {
       HashMap<String, Integer> sum = new HashMap<>(); //储存每个学生所以的分数总和
       HashMap<String, Integer> count = new HashMap<>(); //储存每个学生的成绩数量

      //把每个学生的成绩总和 和 学科的数量 存入对应的HashMap
       for(String part : str){
           String[] arr = part.split(":");
           String name = arr[0].trim();
           int score = Integer.parseInt(arr[1].trim());
           
           sum.put(name, sum.getOrDefault(name, 0) + score);
           count.put(name, count.getOrDefault(name, 0) + 1);
       }
      
       double maxScore = Double.MIN_VALUE;
       String maxName = null;
       
       //然后根据名字，提取每个学生的学科数量 和 总和 =》 计算平均分
      // ==》从而找到最高平均分的名字
       for(String strName : sum.keySet()){
           int sumScore = sum.get(strName);
           int countNum = count.get(strName);
           //the average Score
           double averageScore = (double) sumScore / countNum; 
           
           if(averageScore > maxScore){
               maxScore = averageScore;
               maxName = strName;
           }
        }
        
        return maxName;
    }

    public static void main(String[] args) {
        String[] str = {"Jack: 4", "Bob: 6", "Jack: 3", "Kevein: 2"};
        String result = solution(str);
        System.out.println(result); 
    }
}
