/* Leetcode 649. Dota2 Senate
In the world of Dota2, there are two parties: the Radiant and the Dire.

The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the Dota2 game. 
The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:

    · Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
    · Announce the victory: If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and decide on the change in the game.

Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party. Then if there are n senators, the size of the given string will be n.

The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. 
All the senators who have lost their rights will be skipped during the procedure.

Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the 
victory and change the Dota2 game. The output should be "Radiant" or "Dire".

Example 1:

Input: senate = "RD"
Output: "Radiant"
Explanation: 
The first senator comes from Radiant and he can just ban the next senator's right in round 1. 
And the second senator can't exercise any rights anymore since his right has been banned. 
And in round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.

Example 2:

Input: senate = "RDD"
Output: "Dire"
Explanation: 
The first senator comes from Radiant and he can just ban the next senator's right in round 1. 
And the second senator can't exercise any rights anymore since his right has been banned. 
And the third senator comes from Dire and he can ban the first senator's right in round 1. 
And in round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.
 

Constraints:

n == senate.length
1 <= n <= 104
senate[i] is either 'R' or 'D'.

*/

//Time: O(n^2)  Space: O(n)
class Solution {
    // 贪心 + 环绕队列
    // 假设后面还是存在其他派系的
    public String predictPartyVictory(String senate) {
        //senate表示的 Radiant 和 Dire的投票顺序
        //使用index来储存 Radiant 和 Dire的投票顺序index位置
        Queue<Integer> rq = new LinkedList<>(); //Radiant的index位置
        Queue<Integer> dq = new LinkedList<>(); //Dire的index位置

        //1. 遍历senate，分别储存 Radiant 和 Dire的投票顺序 index
        for(int i = 0; i < senate.length(); i++){
            if(senate.charAt(i) == 'R'){
                rq.add(i);
            } else{
                dq.add(i);
            }
        }

        //2. 遍历 Radiant 和 Dire 队列，每次提取一个index值 然后进行比较
        //因为是环绕队列，所以比较完之后，把index值小的重新加入到相应的Queue队列中
        while(!rq.isEmpty() && !dq.isEmpty()){
            //每次提取Radiant 和 Dire 中的 顺序index值 然后进行比较
            int currRindex = rq.poll();
            int currDindex = dq.poll();

            //所以比较完之后，把index值小的重新加入到相应的Queue队列中
            if(currRindex < currDindex){
                rq.add(currDindex + senate.length());
            } else{
                dq.add(currRindex + senate.length());
            }
        }

        //最后检查Radiant 和 Dire 中剩余哪一派的议员，输出对应的派系获胜
        return rq.isEmpty() ? "Dire" : "Radiant";
    }
}
