/* Leetcode 2101. Detonate the Maximum Bombs

You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and 
Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.

Example 1:

Input: bombs = [[2,1,3],[6,1,4]]
Output: 2
Explanation:
The above figure shows the positions and ranges of the 2 bombs.
If we detonate the left bomb, the right bomb will not be affected.
But if we detonate the right bomb, both bombs will be detonated.
So the maximum bombs that can be detonated is max(1, 2) = 2.

Example 2:

Input: bombs = [[1,1,5],[10,10,5]]
Output: 1
Explanation:
Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.

Example 3:
Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
Output: 5
Explanation:
The best bomb to detonate is bomb 0 because:
- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
Thus all 5 bombs are detonated.
 

Constraints:

1 <= bombs.length <= 100
bombs[i].length == 3
1 <= xi, yi, ri <= 10^5
*/

/* 题意： 此题是给定一个二维的数组，里面储存每个炸弹的 X坐标 Y坐标，和炸弹引爆的半径
==》 然后找到引爆其中一个炸弹 能引爆的最大的炸弹数量

思路： BFS宽度优先搜索 + List<Integer>[] 邻接表

1. 计算和统计，每个炸弹引爆后，涉及到的范围内的全部炸弹

long disX = Math.abs(nextX - currX);
long disY = Math.abs(nextY - currY);
if(disX * disX + disY * disY <= (long) currR * currR){
   DetonBombs[i].add(j);
}
==》直角三角形，两个直角边之和的平方 = 斜边的平方，然后和半径的平方进行比较，小于半径平方，则这个炸弹处于引爆炸弹的半径范围内，会被引爆

2. 然后把每个炸弹对应的index,储存在邻接表中 =>  DetonBombs[i].add(j);

3. 再使用BFS，遍历和提取 统计每个炸弹引爆的数量，最后找到可以引爆的最大炸弹数量
   通过Queue队列，把每个炸弹引爆能涉及到的炸弹都储存起来， 同时统计数量
   
4. 最后使用Math.max() 进行比较，得到能引爆炸弹的最多数量

*/

class Solution {
    //BFS, 邻接表里面储存的是每个炸弹能引爆的炸弹List
    public int maximumDetonation(int[][] bombs) {
        int res = 0; //会被引爆的最大炸弹数量
        int len = bombs.length;
        
        // 邻接表里面储存的是每个炸弹能引爆的炸弹List
        List<Integer>[] DetonBombs = new ArrayList[len];
        //遍历检查每个炸弹的涉及范围, 统计储存每个炸弹引爆 会 涉及到哪些炸弹
        for(int i = 0; i < len; i++){
            int[] currBomb = bombs[i]; //提取每个炸弹的坐标和半径
            int currX = currBomb[0];
            int currY = currBomb[1];
            int currR = currBomb[2];
            //首先给每一个炸弹创建一个空的arraylist，储存每个炸弹能引爆的炸弹
            DetonBombs[i] = new ArrayList<>();
            //遍历检查出当前炸弹以外的所有炸弹是否在当前炸弹的覆盖范围里面
            for(int j = 0; j < len; j++){
                if(i != j){
                    int[] nextBomb = bombs[j];
                    int nextX = nextBomb[0];
                    int nextY = nextBomb[1];
                    int nextR = nextBomb[2];

                    //然后比较两个炸弹坐标点的距离 和 当前炸弹的半径r 关系，
                    //如果比半径r小，则会被涉及到引爆
                    long disX = Math.abs(nextX - currX);
                    long disY = Math.abs(nextY - currY);

                    //直角三角形的三条边关系：两个直角边的平方相加 = 斜边平方
                    if(disX * disX + disY * disY <= (long) currR * currR){
                        DetonBombs[i].add(j);
                    }
                }
            }
        }

        //BFS: 遍历和提取 统计每个炸弹引爆的数量，最后找到可以引爆的最大炸弹数量
        //通过Queue队列，把每个炸弹引爆能涉及到的炸弹都储存起来
        for(int k = 0; k < len; k++){
            boolean[] visBomb = new boolean[len]; //记录炸弹是否已经被引爆
            visBomb[k] = true;
            //BFS Queue队列, 里面储存的是引爆炸弹的index
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(k); //相当于BFS：queue.offer(root); 首先放入第一个要引爆的炸弹
            
            int bombsNumber = 0; //引爆当前炸弹，能同时引爆的最多的炸弹数量
            while(!queue.isEmpty()){
                int detonate = queue.poll(); //提取引爆的炸弹
                bombsNumber += 1; //统计引爆炸弹数量
                //提取这个炸弹引爆涉及到的范围, 里面储存的是炸弹的index
                List<Integer> influnceBombs = DetonBombs[detonate];
                //然后把还没有引爆的炸弹储存到Queue队列中
                for(int bom : influnceBombs){
                    if(visBomb[bom] != true){ //还没有引爆的炸弹
                        queue.offer(bom);
                        visBomb[bom] = true; //这些炸弹都会被引爆
                    }
                }
            }
            res = Math.max(res, bombsNumber); //然后进行比较，得到最多的数量
        }

        return res;
    }
}
